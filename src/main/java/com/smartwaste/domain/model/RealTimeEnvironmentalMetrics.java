package com.smartwaste.domain.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 实时环保指标
 * Real-time Environmental Metrics
 */
public class RealTimeEnvironmentalMetrics {
    private String facilityId;
    private LocalDateTime timestamp;
    private EnvironmentalData currentData;
    private List<EnvironmentalAlert> activeAlerts;
    private boolean compliant;
    private double overallScore;
    private String status;

    // 构造函数
    public RealTimeEnvironmentalMetrics() {
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public EnvironmentalData getCurrentData() { return currentData; }
    public void setCurrentData(EnvironmentalData currentData) { this.currentData = currentData; }

    public List<EnvironmentalAlert> getActiveAlerts() { return activeAlerts; }
    public void setActiveAlerts(List<EnvironmentalAlert> activeAlerts) { this.activeAlerts = activeAlerts; }

    public boolean isCompliant() { return compliant; }
    public void setCompliant(boolean compliant) { this.compliant = compliant; }

    public double getOverallScore() { return overallScore; }
    public void setOverallScore(double overallScore) { this.overallScore = overallScore; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}