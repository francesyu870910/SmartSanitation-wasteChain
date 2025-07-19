package com.smartwaste.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 报表模板实体
 */
@Entity
@Table(name = "report_templates")
public class ReportTemplate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "template_id", unique = true, nullable = false)
    private String templateId; // 模板唯一标识
    
    @Column(name = "template_name", nullable = false)
    private String templateName; // 模板名称
    
    @Column(name = "template_type", nullable = false)
    private String templateType; // 模板类型：DAILY, WEEKLY, MONTHLY, QUARTERLY, YEARLY, CUSTOM
    
    @Column(name = "category", nullable = false)
    private String category; // 报表分类：OPERATIONAL, STATISTICAL, COMPLIANCE, PERFORMANCE
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // 模板描述
    
    @Column(name = "data_sources", columnDefinition = "TEXT")
    private String dataSources; // 数据源配置（JSON格式）
    
    @Column(name = "metrics", columnDefinition = "TEXT")
    private String metrics; // 指标配置（JSON格式）
    
    @Column(name = "filters", columnDefinition = "TEXT")
    private String filters; // 过滤条件（JSON格式）
    
    @Column(name = "grouping", columnDefinition = "TEXT")
    private String grouping; // 分组配置（JSON格式）
    
    @Column(name = "sorting", columnDefinition = "TEXT")
    private String sorting; // 排序配置（JSON格式）
    
    @Column(name = "chart_config", columnDefinition = "TEXT")
    private String chartConfig; // 图表配置（JSON格式）
    
    @Column(name = "layout_config", columnDefinition = "TEXT")
    private String layoutConfig; // 布局配置（JSON格式）
    
    @Column(name = "output_formats")
    private String outputFormats; // 输出格式：PDF,EXCEL,WORD,HTML
    
    @Column(name = "schedule_config", columnDefinition = "TEXT")
    private String scheduleConfig; // 调度配置（JSON格式）
    
    @Column(name = "recipients", columnDefinition = "TEXT")
    private String recipients; // 收件人配置（JSON格式）
    
    @Column(name = "is_active")
    private Boolean isActive = true; // 是否激活
    
    @Column(name = "is_system_template")
    private Boolean isSystemTemplate = false; // 是否系统模板
    
    @Column(name = "created_by", nullable = false)
    private String createdBy; // 创建人
    
    @Column(name = "updated_by")
    private String updatedBy; // 更新人
    
    @Column(name = "version")
    private Integer version = 1; // 版本号
    
    @Column(name = "usage_count")
    private Long usageCount = 0L; // 使用次数
    
    @Column(name = "last_used_at")
    private LocalDateTime lastUsedAt; // 最后使用时间
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // 构造函数
    public ReportTemplate() {
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
    
    public String getTemplateId() {
        return templateId;
    }
    
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
    
    public String getTemplateName() {
        return templateName;
    }
    
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    
    public String getTemplateType() {
        return templateType;
    }
    
    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDataSources() {
        return dataSources;
    }
    
    public void setDataSources(String dataSources) {
        this.dataSources = dataSources;
    }
    
    public String getMetrics() {
        return metrics;
    }
    
    public void setMetrics(String metrics) {
        this.metrics = metrics;
    }
    
    public String getFilters() {
        return filters;
    }
    
    public void setFilters(String filters) {
        this.filters = filters;
    }
    
    public String getGrouping() {
        return grouping;
    }
    
    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }
    
    public String getSorting() {
        return sorting;
    }
    
    public void setSorting(String sorting) {
        this.sorting = sorting;
    }
    
    public String getChartConfig() {
        return chartConfig;
    }
    
    public void setChartConfig(String chartConfig) {
        this.chartConfig = chartConfig;
    }
    
    public String getLayoutConfig() {
        return layoutConfig;
    }
    
    public void setLayoutConfig(String layoutConfig) {
        this.layoutConfig = layoutConfig;
    }
    
    public String getOutputFormats() {
        return outputFormats;
    }
    
    public void setOutputFormats(String outputFormats) {
        this.outputFormats = outputFormats;
    }
    
    public String getScheduleConfig() {
        return scheduleConfig;
    }
    
    public void setScheduleConfig(String scheduleConfig) {
        this.scheduleConfig = scheduleConfig;
    }
    
    public String getRecipients() {
        return recipients;
    }
    
    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public Boolean getIsSystemTemplate() {
        return isSystemTemplate;
    }
    
    public void setIsSystemTemplate(Boolean isSystemTemplate) {
        this.isSystemTemplate = isSystemTemplate;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public String getUpdatedBy() {
        return updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        this.updatedAt = LocalDateTime.now();
    }
    
    public Integer getVersion() {
        return version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public Long getUsageCount() {
        return usageCount;
    }
    
    public void setUsageCount(Long usageCount) {
        this.usageCount = usageCount;
    }
    
    public LocalDateTime getLastUsedAt() {
        return lastUsedAt;
    }
    
    public void setLastUsedAt(LocalDateTime lastUsedAt) {
        this.lastUsedAt = lastUsedAt;
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