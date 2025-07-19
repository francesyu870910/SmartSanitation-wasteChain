package com.smartwaste.domain.service;

import com.smartwaste.domain.model.WasteType;
import com.smartwaste.domain.model.WeighingRecord;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 称重服务接口
 * Weighing Service Interface
 */
public interface WeighingService {
    
    /**
     * 记录称重数据
     */
    WeighingRecord recordWeighing(String deviceId, String userId, String containerId, 
                                 WasteType wasteType, Double weight, String rawData);
    
    /**
     * 处理原始称重数据
     */
    Double processRawWeightData(String rawWeightData);
    
    /**
     * 验证称重准确性
     */
    boolean validateWeightAccuracy(Double weight, WasteType wasteType);
    
    /**
     * 获取设备称重记录
     */
    List<WeighingRecord> getDeviceWeighingRecords(String deviceId);
    
    /**
     * 获取用户称重记录
     */
    List<WeighingRecord> getUserWeighingRecords(String userId);
    
    /**
     * 根据时间范围查询称重记录
     */
    List<WeighingRecord> getWeighingRecordsByTimeRange(LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 计算称重准确率
     */
    double calculateWeighingAccuracy(String deviceId);
    
    /**
     * 校准称重设备
     */
    boolean calibrateWeighingDevice(String deviceId, Double standardWeight);
}