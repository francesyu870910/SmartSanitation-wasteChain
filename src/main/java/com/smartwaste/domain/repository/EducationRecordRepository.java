package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.EducationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 教育记录数据访问接口
 */
@Repository
public interface EducationRecordRepository extends JpaRepository<EducationRecord, Long> {
    
    /**
     * 根据记录ID查找
     */
    Optional<EducationRecord> findByRecordId(String recordId);
    
    /**
     * 根据用户ID查找教育记录
     */
    List<EducationRecord> findByUserIdOrderBySentTimeDesc(String userId);
    
    /**
     * 根据内容ID查找记录
     */
    List<EducationRecord> findByContentIdOrderBySentTimeDesc(String contentId);
    
    /**
     * 根据违规ID查找相关教育记录
     */
    List<EducationRecord> findByViolationIdOrderBySentTimeDesc(String violationId);
    
    /**
     * 根据推送状态查找记录
     */
    List<EducationRecord> findByDeliveryStatusOrderBySentTimeDesc(String deliveryStatus);
    
    /**
     * 查找需要跟进的教育记录
     */
    @Query("SELECT r FROM EducationRecord r WHERE r.followUpRequired = true " +
           "AND r.followUpDate <= :currentDate ORDER BY r.followUpDate ASC")
    List<EducationRecord> findRecordsNeedingFollowUp(@Param("currentDate") LocalDateTime currentDate);
    
    /**
     * 统计用户的教育记录数量
     */
    @Query("SELECT COUNT(r) FROM EducationRecord r WHERE r.userId = :userId")
    Long countByUserId(@Param("userId") String userId);
    
    /**
     * 统计内容的推送次数
     */
    @Query("SELECT COUNT(r) FROM EducationRecord r WHERE r.contentId = :contentId")
    Long countByContentId(@Param("contentId") String contentId);
    
    /**
     * 计算内容的平均效果评分
     */
    @Query("SELECT AVG(r.effectivenessScore) FROM EducationRecord r WHERE r.contentId = :contentId " +
           "AND r.effectivenessScore IS NOT NULL")
    Double calculateAverageEffectivenessScore(@Param("contentId") String contentId);
}