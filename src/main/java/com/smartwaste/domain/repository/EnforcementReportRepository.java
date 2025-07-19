package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.EnforcementReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 执法报告数据访问接口
 */
@Repository
public interface EnforcementReportRepository extends JpaRepository<EnforcementReport, Long> {
    
    /**
     * 根据报告ID查找
     */
    Optional<EnforcementReport> findByReportId(String reportId);
    
    /**
     * 根据案件ID查找
     */
    List<EnforcementReport> findByCaseIdOrderByGeneratedTimeDesc(String caseId);
    
    /**
     * 根据违规ID查找
     */
    Optional<EnforcementReport> findByViolationId(String violationId);
    
    /**
     * 根据用户ID查找所有执法报告
     */
    List<EnforcementReport> findByUserIdOrderByGeneratedTimeDesc(String userId);
    
    /**
     * 根据执法人员查找报告
     */
    List<EnforcementReport> findByEnforcementOfficerOrderByGeneratedTimeDesc(String enforcementOfficer);
    
    /**
     * 根据报告状态查找
     */
    List<EnforcementReport> findByReportStatusOrderByGeneratedTimeDesc(String reportStatus);
    
    /**
     * 根据报告类型查找
     */
    List<EnforcementReport> findByReportTypeOrderByGeneratedTimeDesc(String reportType);
    
    /**
     * 查找需要跟进的报告
     */
    @Query("SELECT r FROM EnforcementReport r WHERE r.followUpRequired = true " +
           "AND r.followUpDate <= :currentDate ORDER BY r.followUpDate ASC")
    List<EnforcementReport> findReportsNeedingFollowUp(@Param("currentDate") LocalDateTime currentDate);
    
    /**
     * 根据时间范围查找报告
     */
    @Query("SELECT r FROM EnforcementReport r WHERE r.generatedTime BETWEEN :startTime AND :endTime " +
           "ORDER BY r.generatedTime DESC")
    List<EnforcementReport> findByTimeRange(@Param("startTime") LocalDateTime startTime, 
                                          @Param("endTime") LocalDateTime endTime);
    
    /**
     * 统计执法人员的报告数量
     */
    @Query("SELECT COUNT(r) FROM EnforcementReport r WHERE r.enforcementOfficer = :officer")
    Long countByEnforcementOfficer(@Param("officer") String officer);
    
    /**
     * 统计报告状态数量
     */
    @Query("SELECT COUNT(r) FROM EnforcementReport r WHERE r.reportStatus = :status")
    Long countByReportStatus(@Param("status") String status);
}