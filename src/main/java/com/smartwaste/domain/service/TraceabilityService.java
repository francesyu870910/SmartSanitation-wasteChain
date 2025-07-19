package com.smartwaste.domain.service;

import com.smartwaste.domain.model.TraceabilityRecord;
import com.smartwaste.domain.model.TraceabilityResult;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 溯源服务接口
 */
public interface TraceabilityService {
    
    /**
     * 创建溯源记录（垃圾投放时调用）
     * @param userId 用户ID
     * @param wasteId 垃圾唯一标识
     * @param wasteType 垃圾类型
     * @param weight 重量
     * @param containerId 垃圾桶ID
     * @param disposalLocation 投放地点
     * @param qrCodeData 二维码数据（可选）
     * @param rfidTag RFID标签（可选）
     * @return 创建的溯源记录
     */
    TraceabilityRecord createTraceabilityRecord(String userId, String wasteId, String wasteType, 
                                              Double weight, String containerId, String disposalLocation,
                                              String qrCodeData, String rfidTag);
    
    /**
     * 更新收运信息
     * @param wasteId 垃圾ID
     * @param vehicleId 车辆ID
     * @param driverId 司机ID
     * @param collectionRoute 收运路线
     * @return 更新后的记录
     */
    TraceabilityRecord updateCollectionInfo(String wasteId, String vehicleId, String driverId, String collectionRoute);
    
    /**
     * 更新处置信息
     * @param wasteId 垃圾ID
     * @param disposalFacilityId 处置场所ID
     * @param disposalMethod 处置方式
     * @return 更新后的记录
     */
    TraceabilityRecord updateDisposalInfo(String wasteId, String disposalFacilityId, String disposalMethod);
    
    /**
     * 通过垃圾ID进行溯源查询
     * @param wasteId 垃圾唯一标识
     * @return 溯源结果
     */
    TraceabilityResult traceWasteToSource(String wasteId);
    
    /**
     * 通过二维码进行溯源查询
     * @param qrCodeData 二维码数据
     * @return 溯源结果
     */
    TraceabilityResult traceByQRCode(String qrCodeData);
    
    /**
     * 通过RFID标签进行溯源查询
     * @param rfidTag RFID标签
     * @return 溯源结果
     */
    TraceabilityResult traceByRFID(String rfidTag);
    
    /**
     * 查询用户的所有投放记录
     * @param userId 用户ID
     * @return 用户的溯源记录列表
     */
    List<TraceabilityRecord> getUserTraceabilityRecords(String userId);
    
    /**
     * 查询用户在指定时间范围内的投放记录
     * @param userId 用户ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 溯源记录列表
     */
    List<TraceabilityRecord> getUserTraceabilityRecords(String userId, LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 根据地址查找相关的投放记录（用于执法支持）
     * @param address 地址关键词
     * @return 相关的溯源记录列表
     */
    List<TraceabilityRecord> findRecordsByAddress(String address);
    
    /**
     * 查询指定垃圾类型的投放记录
     * @param wasteType 垃圾类型
     * @return 溯源记录列表
     */
    List<TraceabilityRecord> getRecordsByWasteType(String wasteType);
    
    /**
     * 查询指定车辆的收运记录
     * @param vehicleId 车辆ID
     * @return 溯源记录列表
     */
    List<TraceabilityRecord> getRecordsByVehicle(String vehicleId);
    
    /**
     * 查询指定处置场所的处理记录
     * @param facilityId 处置场所ID
     * @return 溯源记录列表
     */
    List<TraceabilityRecord> getRecordsByFacility(String facilityId);
    
    /**
     * 获取未完成处置的记录（用于监控和提醒）
     * @param cutoffHours 超时小时数
     * @return 未完成的溯源记录列表
     */
    List<TraceabilityRecord> getIncompleteRecords(int cutoffHours);
    
    /**
     * 统计用户的投放次数
     * @param userId 用户ID
     * @return 投放次数
     */
    Long getUserDisposalCount(String userId);
    
    /**
     * 统计指定垃圾类型的投放次数
     * @param wasteType 垃圾类型
     * @return 投放次数
     */
    Long getWasteTypeCount(String wasteType);
    
    /**
     * 生成垃圾唯一标识
     * @return 唯一的垃圾ID
     */
    String generateWasteId();
}