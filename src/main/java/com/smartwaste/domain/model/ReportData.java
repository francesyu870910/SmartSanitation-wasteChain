package com.smartwaste.domain.model;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 报表数据模型
 */
public class ReportData {
    private String reportId;
    private String reportType;
    private String title;
    private LocalDateTime generatedAt;
    private LocalDateTime reportPeriodStart;
    private LocalDateTime reportPeriodEnd;
    private Map<String, Object> data;
    private String status;
    private String generatedBy;

    public ReportData() {}

    public ReportData(String reportType, String title) {
        this.reportType = reportType;
        this.title = title;
        this.generatedAt = LocalDateTime.now();
        this.status = "GENERATED";
    }

    // Getters and Setters
    public String getReportId() { return reportId; }
    public void setReportId(String reportId) { this.reportId = reportId; }

    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }

    public LocalDateTime getReportPeriodStart() { return reportPeriodStart; }
    public void setReportPeriodStart(LocalDateTime reportPeriodStart) { this.reportPeriodStart = reportPeriodStart; }

    public LocalDateTime getReportPeriodEnd() { return reportPeriodEnd; }
    public void setReportPeriodEnd(LocalDateTime reportPeriodEnd) { this.reportPeriodEnd = reportPeriodEnd; }

    public Map<String, Object> getData() { return data; }
    public void setData(Map<String, Object> data) { this.data = data; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getGeneratedBy() { return generatedBy; }
    public void setGeneratedBy(String generatedBy) { this.generatedBy = generatedBy; }
}