package com.smartwaste.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 违规投放证据实体
 */
@Entity
@Table(name = "violation_evidence")
public class ViolationEvidence {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "violation_id", unique = true, nullable = false)
    private String violationId; // 违规事件唯一标识
    
    @Column(name = "user_id")
    private String userId; // 违规用户ID（如果能识别）
    
    @Column(name = "user_name")
    private String userName; // 用户姓名
    
    @Column(name = "user_address")
    private String userAddress; // 用户地址
    
    @Column(name = "device_id", nullable = false)
    private String deviceId; // 检测设备ID
    
    @Column(name = "container_id")
    private String containerId; // 垃圾桶ID
    
    @Column(name = "violation_time", nullable = false)
    private LocalDateTime violationTime; // 违规时间
    
    @Column(name = "location", nullable = false)
    private String location; // 违规地点
    
    @Column(name = "violation_type", nullable = false)
    private String violationType; // 违规类型：WRONG_CLASSIFICATION, MIXED_WASTE, OVERWEIGHT, UNAUTHORIZED_ACCESS
    
    @Column(name = "violation_description")
    private String violationDescription; // 违规描述
    
    @Column(name = "expected_waste_type")
    private String expectedWasteType; // 应投放的垃圾类型
    
    @Column(name = "actual_waste_type")
    private String actualWasteType; // 实际投放的垃圾类型
    
    @Column(name = "weight")
    private Double weight; // 投放重量
    
    @Column(name = "photo_urls", columnDefinition = "TEXT")
    private String photoUrls; // 照片URL列表（JSON格式）
    
    @Column(name = "video_url")
    private String videoUrl; // 视频URL
    
    @Column(name = "audio_url")
    private String audioUrl; // 音频URL（如有）
    
    @Column(name = "ai_confidence")
    private Double aiConfidence; // AI检测置信度
    
    @Column(name = "status")
    private String status; // 状态：DETECTED, REVIEWED, CONFIRMED, PROCESSED, CLOSED
    
    @Column(name = "severity")
    private String severity; // 严重程度：LOW, MEDIUM, HIGH, CRITICAL
    
    @Column(name = "reviewer_id")
    private String reviewerId; // 审核人员ID
    
    @Column(name = "review_time")
    private LocalDateTime reviewTime; // 审核时间
    
    @Column(name = "review_notes")
    private String reviewNotes; // 审核备注
    
    @Column(name = "enforcement_action")
    private String enforcementAction; // 执法措施
    
    @Column(name = "fine_amount")
    private Double fineAmount; // 罚款金额
    
    @Column(name = "education_sent")
    private Boolean educationSent = false; // 是否已发送教育内容
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // 构造函数
    public ViolationEvidence() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public String getDeviceId() {
        return deviceId;
    }
    
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
    public String getContainerId() {
        return containerId;
    }
    
    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }
    
    public LocalDateTime getViolationTime() {
        return violationTime;
    }
    
    public void setViolationTime(LocalDateTime violationTime) {
        this.violationTime = violationTime;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
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
    
    public String getExpectedWasteType() {
        return expectedWasteType;
    }
    
    public void setExpectedWasteType(String expectedWasteType) {
        this.expectedWasteType = expectedWasteType;
    }
    
    public String getActualWasteType() {
        return actualWasteType;
    }
    
    public void setActualWasteType(String actualWasteType) {
        this.actualWasteType = actualWasteType;
    }
    
    public Double getWeight() {
        return weight;
    }
    
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    
    public String getPhotoUrls() {
        return photoUrls;
    }
    
    public void setPhotoUrls(String photoUrls) {
        this.photoUrls = photoUrls;
    }
    
    public String getVideoUrl() {
        return videoUrl;
    }
    
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    
    public String getAudioUrl() {
        return audioUrl;
    }
    
    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
    
    public Double getAiConfidence() {
        return aiConfidence;
    }
    
    public void setAiConfidence(Double aiConfidence) {
        this.aiConfidence = aiConfidence;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getSeverity() {
        return severity;
    }
    
    public void setSeverity(String severity) {
        this.severity = severity;
    }
    
    public String getReviewerId() {
        return reviewerId;
    }
    
    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }
    
    public LocalDateTime getReviewTime() {
        return reviewTime;
    }
    
    public void setReviewTime(LocalDateTime reviewTime) {
        this.reviewTime = reviewTime;
    }
    
    public String getReviewNotes() {
        return reviewNotes;
    }
    
    public void setReviewNotes(String reviewNotes) {
        this.reviewNotes = reviewNotes;
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
    
    public Boolean getEducationSent() {
        return educationSent;
    }
    
    public void setEducationSent(Boolean educationSent) {
        this.educationSent = educationSent;
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