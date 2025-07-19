package com.smartwaste.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 收运报告
 * Collection Report
 */
@Entity
@Table(name = "collection_reports")
public class CollectionReport {
    @Id
    @Column(name = "report_id", length = 50)
    private String reportId;
    
    @Column(name = "facility_id", nullable = false, length = 50)
    private String facilityId;
    
    @Column(name = "report_type", nullable = false, length = 20)
    private String reportType; // DAILY, WEEKLY, MONTHLY, COMPREHENSIVE
    
    @Column(name = "report_period_start", nullable = false)
    private LocalDateTime reportPeriodStart;
    
    @Column(name = "report_period_end", nullable = false)
    private LocalDateTime reportPeriodEnd;
    
    @Column(name = "generated_time", nullable = false)
    private LocalDateTime generatedTime;
    
    @Column(name = "generated_by", length = 50)
    private String generatedBy;
    
    // 统计数据
    @Column(name = "total_vehicles_processed")
    private Integer totalVehiclesProcessed;
    
    @Column(name = "total_weight_processed")
    private Double totalWeightProcessed;
    
    @Column(name = "total_weight_recovered")
    private Double totalWeightRecovered;
    
    @Column(name = "average_processing_time")
    private Double averageProcessingTime;
    
    @Column(name = "average_efficiency")
    private Double averageEfficiency;
    
    @Column(name = "peak_processing_hour")
    private Integer peakProcessingHour;
    
    @Column(name = "compliance_rate")
    private Double complianceRate;
    
    // 按垃圾类型统计（JSON格式存储）
    @Column(name = "weight_by_waste_type", columnDefinition = "TEXT")
    private String weightByWasteType;
    
    // 按处置方式统计（JSON格式存储）
    @Column(name = "weight_by_disposal_method", columnDefinition = "TEXT")
    private String weightByDisposalMethod;
    
    // 效率分析数据（JSON格式存储）
    @Column(name = "efficiency_analysis", columnDefinition = "TEXT")
    private String efficiencyAnalysis;
    
    // 异常记录（JSON格式存储）
    @Column(name = "anomaly_records", columnDefinition = "TEXT")
    private String anomalyRecords;
    
    // 优化建议（JSON格式存储）
    @Column(name = "optimization_recommendations", columnDefinition = "TEXT")
    private String optimizationRecommendations;
    
    @Column(name = "report_status", length = 20)
    private String reportStatus; // DRAFT, COMPLETED, APPROVED, ARCHIVED
    
    @Column(name = "file_path", length = 500)
    private String filePath;
    
    @Column(name = "file_size")
    private Long fileSize;
    
    @Column(name = "export_formats", length = 100)
    private String exportFormats; // 支持的导出格式，逗号分隔
    
    @Column(name = "remarks", length = 1000)
    private String remarks;
    
    // 构造函数
    public CollectionReport() {
        this.reportId = generateReportId();
        this.generatedTime = LocalDateTime.now();
        this.reportStatus = "DRAFT";
    }
    
    public CollectionReport(String facilityId, String reportType, LocalDateTime startTime, LocalDateTime endTime) {
        this();
        this.facilityId = facilityId;
        this.reportType = reportType;
        this.reportPeriodStart = startTime;
        this.reportPeriodEnd = endTime;
    }
    
    private String generateReportId() {
        return "RPT-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
    }
    
    // Getters and Setters
    public String getReportId() { return reportId; }
    public void setReportId(String reportId) { this.reportId = reportId; }
    
    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }
    
    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }
    
    public LocalDateTime getReportPeriodStart() { return reportPeriodStart; }
    public void setReportPeriodStart(LocalDateTime reportPeriodStart) { this.reportPeriodStart = reportPeriodStart; }
    
    public LocalDateTime getReportPeriodEnd() { return reportPeriodEnd; }
    public void setReportPeriodEnd(LocalDateTime reportPeriodEnd) { this.reportPeriodEnd = reportPeriodEnd; }
    
    public LocalDateTime getGeneratedTime() { return generatedTime; }
    public void setGeneratedTime(LocalDateTime generatedTime) { this.generatedTime = generatedTime; }
    
    public String getGeneratedBy() { return generatedBy; }
    public void setGeneratedBy(String generatedBy) { this.generatedBy = generatedBy; }
    
    public Integer getTotalVehiclesProcessed() { return totalVehiclesProcessed; }
    public void setTotalVehiclesProcessed(Integer totalVehiclesProcessed) { this.totalVehiclesProcessed = totalVehiclesProcessed; }
    
    public Double getTotalWeightProcessed() { return totalWeightProcessed; }
    public void setTotalWeightProcessed(Double totalWeightProcessed) { this.totalWeightProcessed = totalWeightProcessed; }
    
    public Double getTotalWeightRecovered() { return totalWeightRecovered; }
    public void setTotalWeightRecovered(Double totalWeightRecovered) { this.totalWeightRecovered = totalWeightRecovered; }
    
    public Double getAverageProcessingTime() { return averageProcessingTime; }
    public void setAverageProcessingTime(Double averageProcessingTime) { this.averageProcessingTime = averageProcessingTime; }
    
    public Double getAverageEfficiency() { return averageEfficiency; }
    public void setAverageEfficiency(Double averageEfficiency) { this.averageEfficiency = averageEfficiency; }
    
    public Integer getPeakProcessingHour() { return peakProcessingHour; }
    public void setPeakProcessingHour(Integer peakProcessingHour) { this.peakProcessingHour = peakProcessingHour; }
    
    public Double getComplianceRate() { return complianceRate; }
    public void setComplianceRate(Double complianceRate) { this.complianceRate = complianceRate; }
    
    public String getWeightByWasteType() { return weightByWasteType; }
    public void setWeightByWasteType(String weightByWasteType) { this.weightByWasteType = weightByWasteType; }
    
    public String getWeightByDisposalMethod() { return weightByDisposalMethod; }
    public void setWeightByDisposalMethod(String weightByDisposalMethod) { this.weightByDisposalMethod = weightByDisposalMethod; }
    
    public String getEfficiencyAnalysis() { return efficiencyAnalysis; }
    public void setEfficiencyAnalysis(String efficiencyAnalysis) { this.efficiencyAnalysis = efficiencyAnalysis; }
    
    public String getAnomalyRecords() { return anomalyRecords; }
    public void setAnomalyRecords(String anomalyRecords) { this.anomalyRecords = anomalyRecords; }
    
    public String getOptimizationRecommendations() { return optimizationRecommendations; }
    public void setOptimizationRecommendations(String optimizationRecommendations) { this.optimizationRecommendations = optimizationRecommendations; }
    
    public String getReportStatus() { return reportStatus; }
    public void setReportStatus(String reportStatus) { this.reportStatus = reportStatus; }
    
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    
    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
    
    public String getExportFormats() { return exportFormats; }
    public void setExportFormats(String exportFormats) { this.exportFormats = exportFormats; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}