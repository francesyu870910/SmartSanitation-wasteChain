package com.smartwaste.domain.service.impl;

import com.smartwaste.domain.model.UserPoints;
import com.smartwaste.domain.model.WasteType;
import com.smartwaste.domain.repository.UserPointsRepository;
import com.smartwaste.domain.service.PointsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 积分服务实现类
 * Points Service Implementation
 */
@Service
@Transactional
public class PointsServiceImpl implements PointsService {

    private static final Logger logger = LoggerFactory.getLogger(PointsServiceImpl.class);
    
    // 积分计算基础参数
    private static final int BASE_POINTS_PER_KG = 10; // 每公斤基础积分
    private static final double CORRECT_CLASSIFICATION_BONUS = 1.2; // 正确分类奖励系数
    private static final double INCORRECT_CLASSIFICATION_PENALTY = 0.5; // 错误分类惩罚系数
    
    // 用户等级阈值
    private static final int[] LEVEL_THRESHOLDS = {0, 100, 500, 1000, 2000, 5000, 10000};
    private static final String[] LEVEL_NAMES = {"新手", "初级", "中级", "高级", "专家", "大师", "传奇"};
    
    private final UserPointsRepository userPointsRepository;

    @Autowired
    public PointsServiceImpl(UserPointsRepository userPointsRepository) {
        this.userPointsRepository = userPointsRepository;
    }

    @Override
    public Integer calculatePoints(WasteType wasteType, Double weight, Boolean isCorrectClassification) {
        logger.debug("计算积分 - 类型: {}, 重量: {}kg, 分类正确: {}", 
            wasteType.getDisplayName(), weight, isCorrectClassification);
        
        if (weight == null || weight <= 0) {
            return 0;
        }
        
        // 基础积分 = 重量 × 每公斤基础积分 × 垃圾类型系数
        double basePoints = weight * BASE_POINTS_PER_KG * wasteType.getPointsCoefficient();
        
        // 分类准确性调整
        double finalPoints;
        if (Boolean.TRUE.equals(isCorrectClassification)) {
            finalPoints = basePoints * CORRECT_CLASSIFICATION_BONUS;
        } else {
            finalPoints = basePoints * INCORRECT_CLASSIFICATION_PENALTY;
        }
        
        // 四舍五入到整数
        int points = (int) Math.round(finalPoints);
        
        logger.debug("积分计算结果: {} 分", points);
        return Math.max(0, points); // 确保积分不为负数
    }

    @Override
    public UserPoints addPoints(String userId, Integer points, String reason) {
        logger.info("为用户 {} 添加 {} 积分，原因: {}", userId, points, reason);
        
        UserPoints userPoints = getUserPoints(userId)
            .orElseGet(() -> createUserPointsAccount(userId));
        
        // 更新积分
        userPoints.setTotalPoints(userPoints.getTotalPoints() + points);
        userPoints.setAvailablePoints(userPoints.getAvailablePoints() + points);
        userPoints.setUpdatedAt(LocalDateTime.now());
        
        // 保存积分变更
        UserPoints savedPoints = userPointsRepository.save(userPoints);
        
        // 检查并更新用户等级
        savedPoints = updateUserLevel(userId);
        
        // 发送积分通知
        sendPointsNotification(userId, points, "恭喜您获得 " + points + " 积分！" + reason);
        
        logger.info("积分添加成功 - 用户: {}, 当前总积分: {}, 可用积分: {}", 
            userId, savedPoints.getTotalPoints(), savedPoints.getAvailablePoints());
        
        return savedPoints;
    }

    @Override
    public UserPoints deductPoints(String userId, Integer points, String reason) {
        logger.info("为用户 {} 扣除 {} 积分，原因: {}", userId, points, reason);
        
        Optional<UserPoints> userPointsOpt = getUserPoints(userId);
        if (userPointsOpt.isEmpty()) {
            throw new IllegalArgumentException("用户积分账户不存在: " + userId);
        }
        
        UserPoints userPoints = userPointsOpt.get();
        
        // 检查可用积分是否足够
        if (userPoints.getAvailablePoints() < points) {
            throw new IllegalArgumentException("可用积分不足，当前可用: " + userPoints.getAvailablePoints());
        }
        
        // 更新积分
        userPoints.setAvailablePoints(userPoints.getAvailablePoints() - points);
        userPoints.setUsedPoints(userPoints.getUsedPoints() + points);
        userPoints.setUpdatedAt(LocalDateTime.now());
        
        UserPoints savedPoints = userPointsRepository.save(userPoints);
        
        // 发送积分通知
        sendPointsNotification(userId, -points, "您使用了 " + points + " 积分。" + reason);
        
        logger.info("积分扣除成功 - 用户: {}, 剩余可用积分: {}", userId, savedPoints.getAvailablePoints());
        
        return savedPoints;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserPoints> getUserPoints(String userId) {
        return userPointsRepository.findById(userId);
    }

    @Override
    public UserPoints createUserPointsAccount(String userId) {
        logger.info("创建用户积分账户: {}", userId);
        
        // 检查账户是否已存在
        if (userPointsRepository.existsById(userId)) {
            throw new IllegalArgumentException("用户积分账户已存在: " + userId);
        }
        
        UserPoints userPoints = new UserPoints(userId);
        UserPoints savedPoints = userPointsRepository.save(userPoints);
        
        logger.info("用户积分账户创建成功: {}", userId);
        return savedPoints;
    }

    @Override
    public UserPoints updateUserLevel(String userId) {
        Optional<UserPoints> userPointsOpt = getUserPoints(userId);
        if (userPointsOpt.isEmpty()) {
            throw new IllegalArgumentException("用户积分账户不存在: " + userId);
        }
        
        UserPoints userPoints = userPointsOpt.get();
        int totalPoints = userPoints.getTotalPoints();
        
        // 计算新等级
        int newLevel = calculateUserLevel(totalPoints);
        String newLevelName = getLevelName(newLevel);
        
        // 检查是否需要升级
        if (newLevel > userPoints.getLevel()) {
            int oldLevel = userPoints.getLevel();
            userPoints.setLevel(newLevel);
            userPoints.setLevelName(newLevelName);
            userPoints.setUpdatedAt(LocalDateTime.now());
            
            UserPoints savedPoints = userPointsRepository.save(userPoints);
            
            // 发送升级通知
            sendPointsNotification(userId, 0, 
                String.format("恭喜您从 %s 升级到 %s！", getLevelName(oldLevel), newLevelName));
            
            logger.info("用户等级更新 - 用户: {}, 从 {} 升级到 {}", userId, oldLevel, newLevel);
            return savedPoints;
        }
        
        return userPoints;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserPoints> getPointsRanking(int limit) {
        return userPointsRepository.findTopByOrderByTotalPointsDesc(limit);
    }

    @Override
    public void sendPointsNotification(String userId, Integer points, String message) {
        logger.info("发送积分通知 - 用户: {}, 积分变化: {}, 消息: {}", userId, points, message);
        
        // 这里可以集成消息推送服务
        // 例如：短信、邮件、APP推送等
        
        // 模拟通知发送
        try {
            // 实际实现中会调用消息服务
            logger.debug("积分通知发送成功");
        } catch (Exception e) {
            logger.error("积分通知发送失败", e);
        }
    }

    @Override
    public boolean validatePointsCalculation(WasteType wasteType, Double weight, Integer calculatedPoints) {
        Integer expectedPoints = calculatePoints(wasteType, weight, true);
        Integer expectedPointsIncorrect = calculatePoints(wasteType, weight, false);
        
        // 验证计算结果是否在合理范围内
        return calculatedPoints.equals(expectedPoints) || calculatedPoints.equals(expectedPointsIncorrect);
    }

    // 私有辅助方法
    private int calculateUserLevel(int totalPoints) {
        for (int i = LEVEL_THRESHOLDS.length - 1; i >= 0; i--) {
            if (totalPoints >= LEVEL_THRESHOLDS[i]) {
                return i + 1;
            }
        }
        return 1; // 默认等级
    }

    private String getLevelName(int level) {
        if (level >= 1 && level <= LEVEL_NAMES.length) {
            return LEVEL_NAMES[level - 1];
        }
        return "未知等级";
    }
}