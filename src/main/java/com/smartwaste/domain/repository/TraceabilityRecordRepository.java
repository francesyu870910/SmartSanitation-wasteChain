package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.TraceabilityRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 溯源记录数据访问接口
 */
@Repository
public interface TraceabilityRecordRepository extends JpaRepository<TraceabilityRecord, Long> {
    
    /**
     * 根据垃圾ID查找溯源记录
     */
    Optional<TraceabilityRecord> findByWasteId(String wasteId);
    
    /**
     * 根据二维码数据查找溯源记录
     */
    Optional<TraceabilityRecord> findByQrCodeData(String qrCodeData);
    
    /**
     * 根据RFID标签查找溯源记录
     */
    Optional<TraceabilityRecord> findByRfidTag(String rfidTag);
    
    /**
     * 根据用户ID查找所有溯源记录
     */
    List<TraceabilityRecord> findByUserIdOrderByDisposalTimeDesc(String userId);
    
    /**
     * 根据用户ID和时间范围查找溯源记录
     */
    @Query("SELECT t FROM TraceabilityRecord t WHERE t.userId = :userId " +
           "AND t.disposalTime BETWEEN :startTime AND :endTime " +
           "ORDER BY t.disposalTime DESC")
    List<TraceabilityRecord> findByUserIdAndTimeRange(
            @Param("userId") String userId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);
    
    /**
     * 根据垃圾类型查找溯源记录
     */
    List<TraceabilityRecord> findByWasteTypeOrderByDisposalTimeDesc(String wasteType);
    
    /**
     * 根据状态查找溯源记录
     */
    List<TraceabilityRecord> findByStatusOrderByUpdatedAtDesc(String status);
    
    /**
     * 根据收运车辆ID查找溯源记录
     */
    List<TraceabilityRecord> findByVehicleIdOrderByCollectionTimeDesc(String vehicleId);
    
    /**
     * 根据处置场所ID查找溯源记录
     */
    List<TraceabilityRecord> findByDisposalFacilityIdOrderByFinalDisposalTimeDesc(String disposalFacilityId);
    
    /**
     * 查找指定时间范围内的所有溯源记录
     */
    @Query("SELECT t FROM TraceabilityRecord t WHERE t.disposalTime BETWEEN :startTime AND :endTime " +
           "ORDER BY t.disposalTime DESC")
    List<TraceabilityRecord> findByTimeRange(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);
    
    /**
     * 统计用户的投放次数
     */
    @Query("SELECT COUNT(t) FROM TraceabilityRecord t WHERE t.userId = :userId")
    Long countByUserId(@Param("userId") String userId);
    
    /**
     * 统计指定垃圾类型的投放次数
     */
    @Query("SELECT COUNT(t) FROM TraceabilityRecord t WHERE t.wasteType = :wasteType")
    Long countByWasteType(@Param("wasteType") String wasteType);
    
    /**
     * 统计指定状态的记录数量
     */
    @Query("SELECT COUNT(t) FROM TraceabilityRecord t WHERE t.status = :status")
    Long countByStatus(@Param("status") String status);
    
    /**
     * 查找未完成处置的记录（用于监控）
     */
    @Query("SELECT t FROM TraceabilityRecord t WHERE t.status != 'COMPLETED' " +
           "AND t.disposalTime < :cutoffTime ORDER BY t.disposalTime ASC")
    List<TraceabilityRecord> findIncompleteRecords(@Param("cutoffTime") LocalDateTime cutoffTime);
    
    /**
     * 根据用户地址模糊查询
     */
    @Query("SELECT t FROM TraceabilityRecord t WHERE t.userAddress LIKE %:address% " +
           "ORDER BY t.disposalTime DESC")
    List<TraceabilityRecord> findByUserAddressContaining(@Param("address") String address);
    
    /**
     * 查找指定用户在指定地点的投放记录
     */
    @Query("SELECT t FROM TraceabilityRecord t WHERE t.userId = :userId " +
           "AND t.disposalLocation = :location ORDER BY t.disposalTime DESC")
    List<TraceabilityRecord> findByUserIdAndDisposalLocation(
            @Param("userId") String userId,
            @Param("location") String location);
}