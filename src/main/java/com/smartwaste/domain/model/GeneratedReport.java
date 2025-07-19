package com.smartwaste.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 生成的报表实体
 */
@Entity
@Table(name = "generated_reports")
public class GeneratedReport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "report_id", unique = true, nullable = false)
    private String reportId; // 报表唯一标识
    
    @Column(name = "template_id", nullable = false)
    private String templateId; // 模板ID
    
    @Column(name = "report_name", nullable = false)
    private String reportName; // 报表名称
    
    @Column(name = "report_type", nullable = false)
    private String reportType; // 报表类型
    
    @Column(name = "report_period", nullable = false)
    private String reportPeriod; // 报表周期
    
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate; // 数据开始日期
    
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate; // 数据结束日期
    
    @Column(name = "generated_by", nullable = false)
    private String generatedBy; // 生成人
    
    @Column(name = "generation_type", nullable = false)
    private String generationType; // 生成类型：MANUAL, SCHEDULED, API
    
    @Column(name = "status", nullable = false)
    private String status; // 状态：GENERATING, COMPLETED, FAILED, CANCELLED
    
    @Column(name = "file_format")
    private String fileFormat; // 文件格式
    
    @Column(name = "file_path")
    private String filePath; // 文件路径
    
    @Column(name = "file_size")
    private Long fileSize; // 文件大小（字节）
    
    @Column(name = "download_url")
    private String downloadUrl; // 下载URL
    
    @Column(name = "view_url")
    private String viewUrl; // 在线查看URL
    
    @Column(name = "data_summary", columnDefinition = "TEXT")
    private String dataSummary; // 数据摘要（JSON格式）
    
    @Column(name = "generation_config", columnDefinition = "TEXT")
    private String generationConfig; // 生成配置（JSON格式）
    
    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage; // 错误信息
    
    @Column(name = "processing_time")
    private Long processingTime; // 处理时间（毫秒）
    
    @Column(name = "record_count")
    private Long recordCount; // 记录数量
    
    @Column(name = "page_count")
    private Integer pageCount; // 页数
    
    @Column(name = "chart_count")
    private Integer chartCount; // 图表数量
    
    @Column(name = "is_shared")
    private Boolean isShared = false; // 是否共享
    
    @Column(name = "share_token")
    private String shareToken; // 分享令牌
    
    @Column(name = "expiry_date")
    private LocalDateTime expiryDate; // 过期日期
    
    @Column(name = "download_count")
    private Long downloadCount = 0L; // 下载次数
    
    @Column(name = "view_count")
    private Long viewCount = 0L; // 查看次数
    
    @Column(name = "last_accessed_at")
    private LocalDateTime lastAccessedAt; // 最后访问时间
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "completed_at")
    private LocalDateTime completedAt;
    
    // 构造函数
    public GeneratedReport() {
        this.createdAt = LocalDateTime.now();
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
    
    public String getTemplateId() {
        return templateId;
    }
    
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
    
    public String getReportName() {
        return reportName;
    }
    
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
    
    public String getReportType() {
        return reportType;
    }
    
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
    
    public String getReportPeriod() {
        return reportPeriod;
    }
    
    public void setReportPeriod(String reportPeriod) {
        this.reportPeriod = reportPeriod;
    }
    
    public LocalDateTime getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    
    public LocalDateTime getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    
    public String getGeneratedBy() {
        return generatedBy;
    }
    
    public void setGeneratedBy(String generatedBy) {
        this.generatedBy = generatedBy;
    }
    
    public String getGenerationType() {
        return generationType;
    }
    
    public void setGenerationType(String generationType) {
        this.generationType = generationType;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
        if ("COMPLETED".equals(status)) {
            this.completedAt = LocalDateTime.now();
        }
    }
    
    public String getFileFormat() {
        return fileFormat;
    }
    
    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public Long getFileSize() {
        return fileSize;
    }
    
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    
    public String getDownloadUrl() {
        return downloadUrl;
    }
    
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
    
    public String getViewUrl() {
        return viewUrl;
    }
    
    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }
    
    public String getDataSummary() {
        return dataSummary;
    }
    
    public void setDataSummary(String dataSummary) {
        this.dataSummary = dataSummary;
    }
    
    public String getGenerationConfig() {
        return generationConfig;
    }
    
    public void setGenerationConfig(String generationConfig) {
        this.generationConfig = generationConfig;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public Long getProcessingTime() {
        return processingTime;
    }
    
    public void setProcessingTime(Long processingTime) {
        this.processingTime = processingTime;
    }
    
    public Long getRecordCount() {
        return recordCount;
    }
    
    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }
    
    public Integer getPageCount() {
        return pageCount;
    }
    
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
    
    public Integer getChartCount() {
        return chartCount;
    }
    
    public void setChartCount(Integer chartCount) {
        this.chartCount = chartCount;
    }
    
    public Boolean getIsShared() {
        return isShared;
    }
    
    public void setIsShared(Boolean isShared) {
        this.isShared = isShared;
    }
    
    public String getShareToken() {
        return shareToken;
    }
    
    public void setShareToken(String shareToken) {
        this.shareToken = shareToken;
    }
    
    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }
    
    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public Long getDownloadCount() {
        return downloadCount;
    }
    
    public void setDownloadCount(Long downloadCount) {
        this.downloadCount = downloadCount;
    }
    
    public Long getViewCount() {
        return viewCount;
    }
    
    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }
    
    public LocalDateTime getLastAccessedAt() {
        return lastAccessedAt;
    }
    
    public void setLastAccessedAt(LocalDateTime lastAccessedAt) {
        this.lastAccessedAt = lastAccessedAt;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
    
    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}