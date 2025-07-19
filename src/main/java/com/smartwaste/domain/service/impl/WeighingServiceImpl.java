package com.smartwaste.domain.service.impl;

import com.smartwaste.domain.model.WasteType;
import com.smartwaste.domain.model.WeighingRecord;
import com.smartwaste.domain.repository.WeighingRecordRepository;
import com.smartwaste.domain.service.WeighingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 称重服务实现类
 * Weighing Service Implementation
 */
@Service
@Transactional
public class WeighingServiceImpl implements WeighingService {

    private static final Logger logger = LoggerFactory.getLogger(WeighingServiceImpl.class);
    private static final double MIN_WEIGHT_THRESHOLD = 0.01; // 最小称重阈值（kg）
    private static final double MAX_WEIGHT_THRESHOLD = 50.0; // 最大称重阈值（kg）
    
    private final WeighingRecordRepository weighingRecordRepository;

    @Autowired
    public WeighingServiceImpl(WeighingRecordRepository weighingRecordRepository) {
        this.weighingRecordRepository = weighingRecordRepository;
    }

    @Override
    public WeighingRecord recordWeighing(String deviceId, String userId, String containerId, 
                                       WasteType wasteType, Double weight, String rawData) {
        logger.info("记录称重数据 - 设备: {}, 用户: {}, 重量: {}kg, 类型: {}", 
            deviceId, userId, weight, wasteType.getDisplayName());
        
        WeighingRecord record = new WeighingRecord();
        record.setRecordId(UUID.randomUUID().toString());
        record.setDeviceId(deviceId);
        record.setUserId(userId);
        record.setContainerId(containerId);
        record.setWasteType(wasteType);
        record.setWeight(weight);
        record.setRawWeightData(rawData);
        
        // 验证称重准确性
        boolean isAccurate = validateWeightAccuracy(weight, wasteType);
        record.setIsAccurate(isAccurate);
        
        // 计算准确性评分
        double accuracyScore = calculateAccuracyScore(weight, wasteType);
        record.setAccuracyScore(accuracyScore);
        
        WeighingRecord savedRecord = weighingRecordRepository.save(record);
        logger.info("称重记录保存成功 - ID: {}, 准确性: {}, 评分: {}", 
            savedRecord.getRecordId(), isAccurate, accuracyScore);
        
        return savedRecord;
    }

    @Override
    public Double processRawWeightData(String rawWeightData) {
        logger.debug("处理原始称重数据: {}", rawWeightData);
        
        try {
            // 解析原始称重数据
            // 假设原始数据格式为: "WEIGHT:1.25,TEMP:25.5,HUMIDITY:60.2"
            String[] parts = rawWeightData.split(",");
            for (String part : parts) {
                if (part.startsWith("WEIGHT:")) {
                    String weightStr = part.substring(7);
                    Double weight = Double.parseDouble(weightStr);
                    
                    // 数据过滤和校正
                    weight = filterAndCorrectWeight(weight);
                    
                    logger.debug("处理后的重量: {}kg", weight);
                    return weight;
                }
            }
            
            throw new IllegalArgumentException("无法从原始数据中提取重量信息");
            
        } catch (Exception e) {
            logger.error("处理原始称重数据失败: {}", rawWeightData, e);
            throw new RuntimeException("称重数据处理失败", e);
        }
    }

    @Override
    public boolean validateWeightAccuracy(Double weight, WasteType wasteType) {
        if (weight == null || weight < MIN_WEIGHT_THRESHOLD || weight > MAX_WEIGHT_THRESHOLD) {
            logger.warn("重量超出有效范围: {}kg", weight);
            return false;
        }
        
        // 根据垃圾类型验证重量合理性
        switch (wasteType) {
            case KITCHEN:
                return weight >= 0.1 && weight <= 10.0; // 厨余垃圾通常0.1-10kg
            case RECYCLABLE:
                return weight >= 0.05 && weight <= 20.0; // 可回收垃圾0.05-20kg
            case HAZARDOUS:
                return weight >= 0.01 && weight <= 5.0; // 有害垃圾0.01-5kg
            case OTHER:
                return weight >= 0.05 && weight <= 15.0; // 其他垃圾0.05-15kg
            default:
                return true;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<WeighingRecord> getDeviceWeighingRecords(String deviceId) {
        return weighingRecordRepository.findByDeviceIdOrderByWeighingTimeDesc(deviceId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WeighingRecord> getUserWeighingRecords(String userId) {
        return weighingRecordRepository.findByUserIdOrderByWeighingTimeDesc(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WeighingRecord> getWeighingRecordsByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return weighingRecordRepository.findByWeighingTimeBetween(startTime, endTime);
    }

    @Override
    @Transactional(readOnly = true)
    public double calculateWeighingAccuracy(String deviceId) {
        List<WeighingRecord> records = weighingRecordRepository.findByDeviceId(deviceId);
        
        if (records.isEmpty()) {
            return 0.0;
        }
        
        long accurateCount = records.stream()
            .mapToLong(record -> record.getIsAccurate() ? 1 : 0)
            .sum();
        
        double accuracy = (double) accurateCount / records.size();
        logger.info("设备 {} 称重准确率: {:.2%}", deviceId, accuracy);
        
        return accuracy;
    }

    @Override
    public boolean calibrateWeighingDevice(String deviceId, Double standardWeight) {
        logger.info("校准称重设备: {}, 标准重量: {}kg", deviceId, standardWeight);
        
        try {
            // 模拟设备校准过程
            // 实际实现中会向设备发送校准命令
            
            // 记录校准操作
            WeighingRecord calibrationRecord = new WeighingRecord();
            calibrationRecord.setRecordId(UUID.randomUUID().toString());
            calibrationRecord.setDeviceId(deviceId);
            calibrationRecord.setWasteType(WasteType.OTHER);
            calibrationRecord.setWeight(standardWeight);
            calibrationRecord.setRawWeightData("CALIBRATION:" + standardWeight);
            calibrationRecord.setIsAccurate(true);
            calibrationRecord.setAccuracyScore(1.0);
            
            weighingRecordRepository.save(calibrationRecord);
            
            logger.info("设备校准完成: {}", deviceId);
            return true;
            
        } catch (Exception e) {
            logger.error("设备校准失败: {}", deviceId, e);
            return false;
        }
    }

    // 私有辅助方法
    private Double filterAndCorrectWeight(Double rawWeight) {
        // 数据滤波和校正
        if (rawWeight < 0) {
            return 0.0;
        }
        
        // 去除异常值
        if (rawWeight > MAX_WEIGHT_THRESHOLD) {
            logger.warn("检测到异常重量值: {}kg, 已限制为最大值", rawWeight);
            return MAX_WEIGHT_THRESHOLD;
        }
        
        // 精度处理，保留3位小数
        return Math.round(rawWeight * 1000.0) / 1000.0;
    }

    private double calculateAccuracyScore(Double weight, WasteType wasteType) {
        // 基于重量和垃圾类型计算准确性评分
        if (!validateWeightAccuracy(weight, wasteType)) {
            return 0.0;
        }
        
        // 根据重量稳定性和合理性计算评分
        double score = 1.0;
        
        // 重量过小或过大会降低评分
        if (weight < 0.1) {
            score *= 0.8;
        } else if (weight > 20.0) {
            score *= 0.9;
        }
        
        return Math.max(0.0, Math.min(1.0, score));
    }
}