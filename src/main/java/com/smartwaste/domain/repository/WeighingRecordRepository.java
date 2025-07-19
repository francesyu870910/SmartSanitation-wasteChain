package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.WasteType;
import com.smartwaste.domain.model.WeighingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 称重记录数据访问接口
 * Weighing Record Repository Interface
 */
@Repository
public interface WeighingRecordRepository extends JpaRepository<WeighingRecord, String> {
    
    /**
     * 根据设备ID查找称重记录，按时间倒序
     */
    List<WeighingRecord> findByDeviceIdOrderByWeighingTimeDesc(String deviceId);
    
    /**
     * 根据用户ID查找称重记录，按时间倒序
     */
    List<WeighingRecord> findByUserIdOrderByWeighingTimeDesc(String userId);
    
    /**
     * 根据设备ID查找称重记录
     */
    List<WeighingRecord> findByDeviceId(String deviceId);
    
    /**
     * 根据垃圾桶ID查找称重记录
     */
    List<WeighingRecord> findByContainerId(String containerId);
    
    /**
     * 根据垃圾类型查找称重记录
     */
    List<WeighingRecord> findByWasteType(WasteType wasteType);
    
    /**
     * 根据时间范围查找称重记录
     */
    List<WeighingRecord> findByWeighingTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 查找准确的称重记录
     */
    List<WeighingRecord> findByIsAccurateTrue();
    
    /**
     * 查找不准确的称重记录
     */
    List<WeighingRecord> findByIsAccurateFalse();
    
    /**
     * 根据重量范围查找记录
     */
    @Query("SELECT wr FROM WeighingRecord wr WHERE wr.weight BETWEEN :minWeight AND :maxWeight")
    List<WeighingRecord> findByWeightBetween(@Param("minWeight") Double minWeight, 
                                           @Param("maxWeight") Double maxWeight);
    
    /**
     * 统计设备称重总次数
     */
    @Query("SELECT COUNT(wr) FROM WeighingRecord wr WHERE wr.deviceId = :deviceId")
    Long countByDeviceId(@Param("deviceId") String deviceId);
    
    /**
     * 统计设备准确称重次数
     */
    @Query("SELECT COUNT(wr) FROM WeighingRecord wr WHERE wr.deviceId = :deviceId AND wr.isAccurate = true")
    Long countAccurateByDeviceId(@Param("deviceId") String deviceId);
    
    /**
     * 统计用户总称重重量
     */
    @Query("SELECT COALESCE(SUM(wr.weight), 0.0) FROM WeighingRecord wr WHERE wr.userId = :userId")
    Double sumWeightByUserId(@Param("userId") String userId);
    
    /**
     * 根据垃圾类型统计重量
     */
    @Query("SELECT COALESCE(SUM(wr.weight), 0.0) FROM WeighingRecord wr WHERE wr.wasteType = :wasteType AND wr.weighingTime BETWEEN :startTime AND :endTime")
    Double sumWeightByTypeAndTimeBetween(@Param("wasteType") WasteType wasteType,
                                        @Param("startTime") LocalDateTime startTime,
                                        @Param("endTime") LocalDateTime endTime);
    
    /**
     * 查找最近的称重记录
     */
    @Query("SELECT wr FROM WeighingRecord wr WHERE wr.deviceId = :deviceId ORDER BY wr.weighingTime DESC LIMIT 1")
    WeighingRecord findLatestByDeviceId(@Param("deviceId") String deviceId);
}