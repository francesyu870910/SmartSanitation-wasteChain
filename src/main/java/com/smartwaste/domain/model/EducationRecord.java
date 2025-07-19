package com.smartwaste.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 教育记录实体
 */
@Entity
@Table(name = "education_records")
public class EducationRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "record_id", unique = true, nullable = false)
    private String recordId; // 记录唯一标识
    
    @Column(name = "user_id", nullable = false)
    private String userId; // 用户ID
    
    @Column(name = "content_id", nullable = false)
    private String contentId; // 教育内容ID
    
    @Column(name = "violation_id")
    private String violationId; // 关联的违规ID（如果有）
    
    @Column(name = "delivery_method", nullable = false)
    private String deliveryMethod; // 推送方式：SMS, EMAIL, APP_PUSH, WECHAT
    
    @Column(name = "delivery_status", nullable = false)
    private String deliveryStatus; // 推送状态：SENT, DELIVERED, READ, FAILED
    
    @Column(name = "sent_time", nullable = false)
    private LocalDateTime sentTime; // 发送时间
    
    @Column(name = "delivered_time")
    private LocalDateTime deliveredTime; // 送达时间
    
    @Column(name = "read_time")
    private LocalDateTime readTime; // 阅读时间
    
    @Column(name = "interaction_type")
    private String interactionType; // 交互类型：VIEW, LIKE, SHARE, COMMENT, QUIZ_COMPLETE
    
    @Column(name = "interaction_time")
    private LocalDateTime interactionTime; // 交互时间
    
    @Column(name = "view_duration")
    private Integer viewDuration; // 查看时长（秒）
    
    @Column(name = "completion_rate")
    private Double completionRate; // 完成率
    
    @Column(name = "quiz_score")
    private Integer quizScore; // 测验得分
    
    @Column(name = "feedback_rating")
    private Integer feedbackRating; // 反馈评分（1-5）
    
    @Column(name = "feedback_comment")
    private String feedbackComment; // 反馈评论
    
    @Column(name = "effectiveness_score")
    private Double effectivenessScore; // 教育效果评分
    
    @Column(name = "follow_up_required")
    private Boolean followUpRequired = false; // 是否需要后续跟进
    
    @Column(name = "follow_up_date")
    private LocalDateTime followUpDate; // 跟进日期
    
    @Column(name = "improvement_observed")
    private Boolean improvementObserved = false; // 是否观察到改进
    
    @Column(name = "subsequent_violations")
    private Integer subsequentViolations = 0; // 后续违规次数
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // 构造函数
    public EducationRecord() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.sentTime = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getRecordId() {
        return recordId;
    }
    
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getContentId() {
        return contentId;
    }
    
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
    
    public String getViolationId() {
        return violationId;
    }
    
    public void setViolationId(String violationId) {
        this.violationId = violationId;
    }
    
    public String getDeliveryMethod() {
        return deliveryMethod;
    }
    
    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
    
    public String getDeliveryStatus() {
        return deliveryStatus;
    }
    
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        this.updatedAt = LocalDateTime.now();
    }
    
    public LocalDateTime getSentTime() {
        return sentTime;
    }
    
    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }
    
    public LocalDateTime getDeliveredTime() {
        return deliveredTime;
    }
    
    public void setDeliveredTime(LocalDateTime deliveredTime) {
        this.deliveredTime = deliveredTime;
    }
    
    public LocalDateTime getReadTime() {
        return readTime;
    }
    
    public void setReadTime(LocalDateTime readTime) {
        this.readTime = readTime;
    }
    
    public String getInteractionType() {
        return interactionType;
    }
    
    public void setInteractionType(String interactionType) {
        this.interactionType = interactionType;
    }
    
    public LocalDateTime getInteractionTime() {
        return interactionTime;
    }
    
    public void setInteractionTime(LocalDateTime interactionTime) {
        this.interactionTime = interactionTime;
    }
    
    public Integer getViewDuration() {
        return viewDuration;
    }
    
    public void setViewDuration(Integer viewDuration) {
        this.viewDuration = viewDuration;
    }
    
    public Double getCompletionRate() {
        return completionRate;
    }
    
    public void setCompletionRate(Double completionRate) {
        this.completionRate = completionRate;
    }
    
    public Integer getQuizScore() {
        return quizScore;
    }
    
    public void setQuizScore(Integer quizScore) {
        this.quizScore = quizScore;
    }
    
    public Integer getFeedbackRating() {
        return feedbackRating;
    }
    
    public void setFeedbackRating(Integer feedbackRating) {
        this.feedbackRating = feedbackRating;
    }
    
    public String getFeedbackComment() {
        return feedbackComment;
    }
    
    public void setFeedbackComment(String feedbackComment) {
        this.feedbackComment = feedbackComment;
    }
    
    public Double getEffectivenessScore() {
        return effectivenessScore;
    }
    
    public void setEffectivenessScore(Double effectivenessScore) {
        this.effectivenessScore = effectivenessScore;
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
    
    public Boolean getImprovementObserved() {
        return improvementObserved;
    }
    
    public void setImprovementObserved(Boolean improvementObserved) {
        this.improvementObserved = improvementObserved;
    }
    
    public Integer getSubsequentViolations() {
        return subsequentViolations;
    }
    
    public void setSubsequentViolations(Integer subsequentViolations) {
        this.subsequentViolations = subsequentViolations;
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