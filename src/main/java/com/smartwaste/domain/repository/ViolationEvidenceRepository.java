package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.ViolationEvidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 违规证据数据访问接口
 */
@Repository
public interface ViolationEvidenceRepository extends JpaRepository<ViolationEvidence, Long> {
    
    /**
     * 根据违规ID查找证据
     */
    Optional<ViolationEvidence> findByViolationId(String violationId);
    
    /**
     * 根据用户ID查找所有违规记录
     */
    List<ViolationEvidence> findByUserIdOrderByViolationTimeDesc(String userId);
    
    /**
     * 根据设备ID查找违规记录
     */
    List<ViolationEvidence> findByDeviceIdOrderByViolationTimeDesc(String deviceId);
    
    /**
     * 根据违规类型查找记录
     */
    List<ViolationEvidence> findByViolationTypeOrderByViolationTimeDesc(String violationType);
    
    /**
     * 根据状态查找记录
     */
    List<ViolationEvidence> findByStatusOrderByViolationTimeDesc(String status);
    
    /**
     * 根据严重程度查找记录
     */
    List<ViolationEvidence> findBySeverityOrderByViolationTimeDesc(String severity);
    
    /**
     * 查找指定时间范围内的违规记录
     */
    @Query("SELECT v FROM ViolationEvidence v WHERE v.violationTime BETWEEN :startTime AND :endTime " +
           "ORDER BY v.violationTime DESC")
    List<ViolationEvidence> findByTimeRange(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);
    
    /**
     * 根据地点查找违规记录
     */
    @Query("SELECT v FROM ViolationEvidence v WHERE v.location LIKE %:location% " +
           "ORDER BY v.violationTime DESC")
    List<ViolationEvidence> findByLocationContaining(@Param("location") String location);
    
    /**
     * 查找待审核的违规记录
     */
    @Query("SELECT v FROM ViolationEvidence v WHERE v.status = 'DETECTED' " +
           "ORDER BY v.violationTime ASC")
    List<ViolationEvidence> findPendingReview();
    
    /**
     * 查找高置信度的违规记录
     */
    @Query("SELECT v FROM ViolationEvidence v WHERE v.aiConfidence >= :minConfidence " +
           "ORDER BY v.aiConfidence DESC, v.violationTime DESC")
    List<ViolationEvidence> findHighConfidenceViolations(@Param("minConfidence") Double minConfidence);
    
    /**
     * 统计用户的违规次数
     */
    @Query("SELECT COUNT(v) FROM ViolationEvidence v WHERE v.userId = :userId")
    Long countByUserId(@Param("userId") String userId);
    
    /**
     * 统计指定违规类型的次数
     */
    @Query("SELECT COUNT(v) FROM ViolationEvidence v WHERE v.violationType = :violationType")
    Long countByViolationType(@Param("violationType") String violationType);
    
    /**
     * 统计指定状态的记录数量
     */
    @Query("SELECT COUNT(v) FROM ViolationEvidence v WHERE v.status = :status")
    Long countByStatus(@Param("status") String status);
    
    /**
     * 查找用户在指定时间范围内的违规记录
     */
    @Query("SELECT v FROM ViolationEvidence v WHERE v.userId = :userId " +
           "AND v.violationTime BETWEEN :startTime AND :endTime " +
           "ORDER BY v.violationTime DESC")
    List<ViolationEvidence> findByUserIdAndTimeRange(
            @Param("userId") String userId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);
    
    /**
     * 查找需要发送教育内容的违规记录
     */
    @Query("SELECT v FROM ViolationEvidence v WHERE v.educationSent = false " +
           "AND v.status IN ('CONFIRMED', 'PROCESSED') " +
           "AND v.userId IS NOT NULL " +
           "ORDER BY v.violationTime ASC")
    List<ViolationEvidence> findRecordsNeedingEducation();
    
    /**
     * 根据审核人员查找记录
     */
    List<ViolationEvidence> findByReviewerIdOrderByReviewTimeDesc(String reviewerId);
    
    /**
     * 查找指定设备在指定时间范围内的违规记录
     */
    @Query("SELECT v FROM ViolationEvidence v WHERE v.deviceId = :deviceId " +
           "AND v.violationTime BETWEEN :startTime AND :endTime " +
           "ORDER BY v.violationTime DESC")
    List<ViolationEvidence> findByDeviceIdAndTimeRange(
            @Param("deviceId") String deviceId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);
    
    /**
     * 查找超过指定时间未处理的违规记录
     */
    @Query("SELECT v FROM ViolationEvidence v WHERE v.status = 'DETECTED' " +
           "AND v.violationTime < :cutoffTime ORDER BY v.violationTime ASC")
    List<ViolationEvidence> findOverdueViolations(@Param("cutoffTime") LocalDateTime cutoffTime);
    
    /**
     * 根据用户地址模糊查询违规记录
     */
    @Query("SELECT v FROM ViolationEvidence v WHERE v.userAddress LIKE %:address% " +
           "ORDER BY v.violationTime DESC")
    List<ViolationEvidence> findByUserAddressContaining(@Param("address") String address);
    
    /**
     * 查找有罚款的违规记录
     */
    @Query("SELECT v FROM ViolationEvidence v WHERE v.fineAmount > 0 " +
           "ORDER BY v.violationTime DESC")
    List<ViolationEvidence> findViolationsWithFines();
}