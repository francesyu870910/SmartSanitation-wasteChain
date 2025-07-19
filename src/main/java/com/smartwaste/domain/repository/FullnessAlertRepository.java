package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.FullnessAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 满溢报警数据访问接口
 * Fullness Alert Repository Interface
 */
@Repository
public interface FullnessAlertRepository extends JpaRepository<FullnessAlert, String> {
    
    /**
     * 获取未解决的报警
     */
    List<FullnessAlert> findByIsResolvedFalseOrderByAlertTimeDesc();
    
    /**
     * 根据报警级别查找报警
     */
    List<FullnessAlert> findByAlertLevelOrderByAlertTimeDesc(FullnessAlert.AlertLevel alertLevel);
    
    /**
     * 根据垃圾桶ID查找报警历史
     */
    List<FullnessAlert> findByContainerIdOrderByAlertTimeDesc(String containerId);
    
    /**
     * 根据时间范围查找报警
     */
    @Query("SELECT fa FROM FullnessAlert fa WHERE fa.alertTime BETWEEN :startTime AND :endTime ORDER BY fa.alertTime DESC")
    List<FullnessAlert> findByAlertTimeBetween(@Param("startTime") LocalDateTime startTime,
                                              @Param("endTime") LocalDateTime endTime);
    
    /**
     * 查找紧急报警
     */
    List<FullnessAlert> findByAlertLevelAndIsResolvedFalse(FullnessAlert.AlertLevel alertLevel);
    
    /**
     * 统计未解决报警数量
     */
    @Query("SELECT COUNT(fa) FROM FullnessAlert fa WHERE fa.isResolved = false")
    Long countUnresolvedAlerts();
    
    /**
     * 根据报警级别统计数量
     */
    @Query("SELECT COUNT(fa) FROM FullnessAlert fa WHERE fa.alertLevel = :alertLevel AND fa.isResolved = false")
    Long countUnresolvedAlertsByLevel(@Param("alertLevel") FullnessAlert.AlertLevel alertLevel);
    
    /**
     * 统计垃圾桶报警次数
     */
    @Query("SELECT COUNT(fa) FROM FullnessAlert fa WHERE fa.containerId = :containerId")
    Long countAlertsByContainer(@Param("containerId") String containerId);
    
    /**
     * 查找最近的报警
     */
    @Query("SELECT fa FROM FullnessAlert fa WHERE fa.containerId = :containerId ORDER BY fa.alertTime DESC LIMIT 1")
    FullnessAlert findLatestAlertByContainer(@Param("containerId") String containerId);
    
    /**
     * 根据解决人查找已解决的报警
     */
    List<FullnessAlert> findByResolvedByOrderByResolvedTimeDesc(String resolvedBy);
}