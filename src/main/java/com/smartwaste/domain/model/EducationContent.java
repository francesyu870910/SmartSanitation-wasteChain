package com.smartwaste.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 教育内容实体
 */
@Entity
@Table(name = "education_contents")
public class EducationContent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "content_id", unique = true, nullable = false)
    private String contentId; // 内容唯一标识
    
    @Column(name = "title", nullable = false)
    private String title; // 标题
    
    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content; // 内容
    
    @Column(name = "content_type", nullable = false)
    private String contentType; // 内容类型：TEXT, IMAGE, VIDEO, AUDIO, INTERACTIVE
    
    @Column(name = "category", nullable = false)
    private String category; // 分类：CLASSIFICATION_GUIDE, VIOLATION_EDUCATION, TIPS, FAQ
    
    @Column(name = "waste_type")
    private String wasteType; // 针对的垃圾类型
    
    @Column(name = "violation_type")
    private String violationType; // 针对的违规类型
    
    @Column(name = "target_audience")
    private String targetAudience; // 目标受众：GENERAL, FREQUENT_VIOLATORS, NEW_USERS
    
    @Column(name = "difficulty_level")
    private String difficultyLevel; // 难度级别：BASIC, INTERMEDIATE, ADVANCED
    
    @Column(name = "media_url")
    private String mediaUrl; // 媒体文件URL
    
    @Column(name = "thumbnail_url")
    private String thumbnailUrl; // 缩略图URL
    
    @Column(name = "duration")
    private Integer duration; // 持续时间（秒）
    
    @Column(name = "language")
    private String language = "zh-CN"; // 语言
    
    @Column(name = "tags")
    private String tags; // 标签（逗号分隔）
    
    @Column(name = "priority")
    private Integer priority = 0; // 优先级
    
    @Column(name = "is_active")
    private Boolean isActive = true; // 是否激活
    
    @Column(name = "view_count")
    private Long viewCount = 0L; // 查看次数
    
    @Column(name = "like_count")
    private Long likeCount = 0L; // 点赞次数
    
    @Column(name = "share_count")
    private Long shareCount = 0L; // 分享次数
    
    @Column(name = "effectiveness_score")
    private Double effectivenessScore = 0.0; // 效果评分
    
    @Column(name = "author")
    private String author; // 作者
    
    @Column(name = "reviewer")
    private String reviewer; // 审核人
    
    @Column(name = "review_status")
    private String reviewStatus = "PENDING"; // 审核状态：PENDING, APPROVED, REJECTED
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "published_at")
    private LocalDateTime publishedAt;
    
    // 构造函数
    public EducationContent() {
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
    
    public String getContentId() {
        return contentId;
    }
    
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getContentType() {
        return contentType;
    }
    
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getWasteType() {
        return wasteType;
    }
    
    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
    }
    
    public String getViolationType() {
        return violationType;
    }
    
    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }
    
    public String getTargetAudience() {
        return targetAudience;
    }
    
    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }
    
    public String getDifficultyLevel() {
        return difficultyLevel;
    }
    
    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
    
    public String getMediaUrl() {
        return mediaUrl;
    }
    
    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
    
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    public String getTags() {
        return tags;
    }
    
    public void setTags(String tags) {
        this.tags = tags;
    }
    
    public Integer getPriority() {
        return priority;
    }
    
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public Long getViewCount() {
        return viewCount;
    }
    
    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }
    
    public Long getLikeCount() {
        return likeCount;
    }
    
    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }
    
    public Long getShareCount() {
        return shareCount;
    }
    
    public void setShareCount(Long shareCount) {
        this.shareCount = shareCount;
    }
    
    public Double getEffectivenessScore() {
        return effectivenessScore;
    }
    
    public void setEffectivenessScore(Double effectivenessScore) {
        this.effectivenessScore = effectivenessScore;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getReviewer() {
        return reviewer;
    }
    
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
    
    public String getReviewStatus() {
        return reviewStatus;
    }
    
    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
        this.updatedAt = LocalDateTime.now();
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
    
    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }
    
    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }
}