package com.smartwaste.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 执法报告实体
 */
@Entity
@Table(name = "enforcement_reports")
public class EnforcementReport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "report_id", unique = true, nullable = false)
    private String reportId; // 报告唯一标识
    
    @Column(name = "case_id")
    private String caseId; // 案件ID
    
    @Column(name = "violation_id", nullable = false)
    private String violationId; // 违规ID
    
    @Column(name = "user_id")
    private String userId; // 违规用户ID
    
    @Column(name = "user_name")
    private String userName; // 用户姓名
    
    @Column(name = "user_address")
    private String userAddress; // 用户地址
    
    @Column(name = "user_phone")
    private String userPhone; // 用户电话
    
    @Column(name = "violation_type", nullable = false)
    private String violationType; // 违规类型
    
    @Column(name = "violation_description", columnDefinition = "TEXT")
    private String violationDescription; // 违规描述
    
    @Column(name = "violation_time", nullable = false)
    private LocalDateTime violationTime; // 违规时间
    
    @Column(name = "violation_location", nullable = false)
    private String violationLocation; // 违规地点
    
    @Column(name = "evidence_summary", columnDefinition = "TEXT")
    private String evidenceSummary; // 证据摘要
    
    @Column(name = "photo_evidence", columnDefinition = "TEXT")
    private String photoEvidence; // 照片证据URL列表
    
    @Column(name = "video_evidence")
    private String videoEvidence; // 视频证据URL
    
    @Column(name = "witness_info", columnDefinition = "TEXT")
    private String witnessInfo; // 证人信息
    
    @Column(name = "enforcement_officer", nullable = false)
    private String enforcementOfficer; // 执法人员
    
    @Column(name = "officer_badge")
    private String officerBadge; // 执法人员警号
    
    @Column(name = "enforcement_action", nullable = false)
    private String enforcementAction; // 执法措施
    
    @Column(name = "fine_amount")
    private Double fineAmount; // 罚款金额
    
    @Column(name = "legal_basis", columnDefinition = "TEXT")
    private String legalBasis; // 法律依据
    
    @Column(name = "report_status")
    private String reportStatus; // 报告状态：DRAFT, SUBMITTED, APPROVED, CLOSED
    
    @Column(name = "report_type")
    private String reportType; // 报告类型：VIOLATION, EDUCATION, WARNING
    
    @Column(name = "generated_time", nullable = false)
    private LocalDateTime generatedTime; // 生成时间
    
    @Column(name = "submitted_time")
    private LocalDateTime submittedTime; // 提交时间
    
    @Column(name = "approved_time")
    private LocalDateTime approvedTime; // 批准时间
    
    @Column(name = "approver_id")
    private String approverId; // 批准人ID
    
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes; // 备注
    
    @Column(name = "follow_up_required")
    private Boolean followUpRequired = false; // 是否需要后续跟进
    
    @Column(name = "follow_up_date")
    private LocalDateTime followUpDate; // 跟进日期
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // 构造函数
    public EnforcementReport() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.generatedTime = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getReportId() {
        return reportId;
    }
    
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
    
    public String getCaseId() {
        return caseId;
    }
    
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
    
    public String getViolationId() {
        return violationId;
    }
    
    public void setViolationId(String violationId) {
        this.violationId = violationId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserAddress() {
        return userAddress;
    }
    
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
    
    public String getUserPhone() {
        return userPhone;
    }
    
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    
    public String getViolationType() {
        return violationType;
    }
    
    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }
    
    public String getViolationDescription() {
        return violationDescription;
    }
    
    public void setViolationDescription(String violationDescription) {
        this.violationDescription = violationDescription;
    }
    
    public LocalDateTime getViolationTime() {
        return violationTime;
    }
    
    public void setViolationTime(LocalDateTime violationTime) {
        this.violationTime = violationTime;
    }
    
    public String getViolationLocation() {
        return violationLocation;
    }
    
    public void setViolationLocation(String violationLocation) {
        this.violationLocation = violationLocation;
    }
    
    public String getEvidenceSummary() {
        return evidenceSummary;
    }
    
    public void setEvidenceSummary(String evidenceSummary) {
        this.evidenceSummary = evidenceSummary;
    }
    
    public String getPhotoEvidence() {
        return photoEvidence;
    }
    
    public void setPhotoEvidence(String photoEvidence) {
        this.photoEvidence = photoEvidence;
    }
    
    public String getVideoEvidence() {
        return videoEvidence;
    }
    
    public void setVideoEvidence(String videoEvidence) {
        this.videoEvidence = videoEvidence;
    }
    
    public String getWitnessInfo() {
        return witnessInfo;
    }
    
    public void setWitnessInfo(String witnessInfo) {
        this.witnessInfo = witnessInfo;
    }
    
    public String getEnforcementOfficer() {
        return enforcementOfficer;
    }
    
    public void setEnforcementOfficer(String enforcementOfficer) {
        this.enforcementOfficer = enforcementOfficer;
    }
    
    public String getOfficerBadge() {
        return officerBadge;
    }
    
    public void setOfficerBadge(String officerBadge) {
        this.officerBadge = officerBadge;
    }
    
    public String getEnforcementAction() {
        return enforcementAction;
    }
    
    public void setEnforcementAction(String enforcementAction) {
        this.enforcementAction = enforcementAction;
    }
    
    public Double getFineAmount() {
        return fineAmount;
    }
    
    public void setFineAmount(Double fineAmount) {
        this.fineAmount = fineAmount;
    }
    
    public String getLegalBasis() {
        return legalBasis;
    }
    
    public void setLegalBasis(String legalBasis) {
        this.legalBasis = legalBasis;
    }
    
    public String getReportStatus() {
        return reportStatus;
    }
    
    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getReportType() {
        return reportType;
    }
    
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
    
    public LocalDateTime getGeneratedTime() {
        return generatedTime;
    }
    
    public void setGeneratedTime(LocalDateTime generatedTime) {
        this.generatedTime = generatedTime;
    }
    
    public LocalDateTime getSubmittedTime() {
        return submittedTime;
    }
    
    public void setSubmittedTime(LocalDateTime submittedTime) {
        this.submittedTime = submittedTime;
    }
    
    public LocalDateTime getApprovedTime() {
        return approvedTime;
    }
    
    public void setApprovedTime(LocalDateTime approvedTime) {
        this.approvedTime = approvedTime;
    }
    
    public String getApproverId() {
        return approverId;
    }
    
    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public Boolean getFollowUpRequired() {
        return followUpRequired;
    }
    
    public void setFollowUpRequired(Boolean followUpRequired) {
        this.followUpRequired = followUpRequired;
    }
    
    public LocalDateTime getFollowUpDate() {
        return followUpDate;
    }
    
    public void setFollowUpDate(LocalDateTime followUpDate) {
        this.followUpDate = followUpDate;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}