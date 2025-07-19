package com.smartwaste.domain.service;

import com.smartwaste.domain.model.EnforcementReport;
import com.smartwaste.domain.model.ViolationEvidence;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 执法服务接口
 */
public interface EnforcementService {
    
    /**
     * 生成标准化执法报告
     * @param violationEvidence 违规证据
     * @param enforcementOfficer 执法人员
     * @param officerBadge 执法人员警号
     * @param enforcementAction 执法措施
     * @param fineAmount 罚款金额
     * @param legalBasis 法律依据
     * @return 生成的执法报告
     */
    EnforcementReport generateEnforcementReport(ViolationEvidence violationEvidence, 
                                              String enforcementOfficer, String officerBadge,
                                              String enforcementAction, Double fineAmount, String legalBasis);
    
    /**
     * 提交执法报告
     * @param reportId 报告ID
     * @return 更新后的报告
     */
    EnforcementReport submitReport(String reportId);
    
    /**
     * 批准执法报告
     * @param reportId 报告ID
     * @param approverId 批准人ID
     * @param notes 批准备注
     * @return 更新后的报告
     */
    EnforcementReport approveReport(String reportId, String approverId, String notes);
    
    /**
     * 获取执法报告详情
     * @param reportId 报告ID
     * @return 执法报告
     */
    EnforcementReport getEnforcementReport(String reportId);
    
    /**
     * 根据违规ID获取执法报告
     * @param violationId 违规ID
     * @return 执法报告
     */
    EnforcementReport getReportByViolationId(String violationId);
    
    /**
     * 查询用户的执法报告
     * @param userId 用户ID
     * @return 执法报告列表
     */
    List<EnforcementReport> getUserEnforcementReports(String userId);
    
    /**
     * 查询执法人员的报告
     * @param enforcementOfficer 执法人员
     * @return 执法报告列表
     */
    List<EnforcementReport> getOfficerReports(String enforcementOfficer);
    
    /**
     * 根据状态查询报告
     * @param status 报告状态
     * @return 执法报告列表
     */
    List<EnforcementReport> getReportsByStatus(String status);
    
    /**
     * 查询需要跟进的报告
     * @return 需要跟进的报告列表
     */
    List<EnforcementReport> getReportsNeedingFollowUp();
    
    /**
     * 设置报告跟进
     * @param reportId 报告ID
     * @param followUpDate 跟进日期
     * @param notes 跟进备注
     */
    void setReportFollowUp(String reportId, LocalDateTime followUpDate, String notes);
    
    /**
     * 关闭执法报告
     * @param reportId 报告ID
     * @param notes 关闭备注
     * @return 更新后的报告
     */
    EnforcementReport closeReport(String reportId, String notes);
    
    /**
     * 统计执法人员的报告数量
     * @param enforcementOfficer 执法人员
     * @return 报告数量
     */
    Long getOfficerReportCount(String enforcementOfficer);
    
    /**
     * 统计报告状态数量
     * @param status 报告状态
     * @return 报告数量
     */
    Long getReportCountByStatus(String status);
    
    /**
     * 生成报告ID
     * @return 唯一的报告ID
     */
    String generateReportId();
    
    /**
     * 生成案件ID
     * @return 唯一的案件ID
     */
    String generateCaseId();
}