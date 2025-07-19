package com.smartwaste.domain.service;

import com.smartwaste.domain.model.UserPoints;
import com.smartwaste.domain.model.WasteType;
import java.util.List;
import java.util.Optional;

/**
 * 积分服务接口
 * Points Service Interface
 */
public interface PointsService {
    
    /**
     * 计算积分
     */
    Integer calculatePoints(WasteType wasteType, Double weight, Boolean isCorrectClassification);
    
    /**
     * 添加积分
     */
    UserPoints addPoints(String userId, Integer points, String reason);
    
    /**
     * 扣除积分
     */
    UserPoints deductPoints(String userId, Integer points, String reason);
    
    /**
     * 获取用户积分账户
     */
    Optional<UserPoints> getUserPoints(String userId);
    
    /**
     * 创建用户积分账户
     */
    UserPoints createUserPointsAccount(String userId);
    
    /**
     * 更新用户等级
     */
    UserPoints updateUserLevel(String userId);
    
    /**
     * 获取积分排行榜
     */
    List<UserPoints> getPointsRanking(int limit);
    
    /**
     * 发送积分通知
     */
    void sendPointsNotification(String userId, Integer points, String message);
    
    /**
     * 验证积分计算准确性
     */
    boolean validatePointsCalculation(WasteType wasteType, Double weight, Integer calculatedPoints);
}