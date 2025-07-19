package com.smartwaste.domain.model;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 环保统计信息
 * Environmental Statistics
 */
public class EnvironmentalStatistics {
    private String facilityId;
    private LocalDateTime generatedAt;
    private String period;
    private Map<String, Double> averageValues;
    private Map<String, Double> maxValues;
    private Map<String, Double> minValues;
    private int totalMeasurements;
    private int violationCount;
    private double complianceRate;
    private String overallStatus;

    // 构造函数
    public EnvironmentalStatistics() {
        this.generatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }

    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }

    public Map<String, Double> getAverageValues() { return averageValues; }
    public void setAverageValues(Map<String, Double> averageValues) { this.averageValues = averageValues; }

    public Map<String, Double> getMaxValues() { return maxValues; }
    public void setMaxValues(Map<String, Double> maxValues) { this.maxValues = maxValues; }

    public Map<String, Double> getMinValues() { return minValues; }
    public void setMinValues(Map<String, Double> minValues) { this.minValues = minValues; }

    public int getTotalMeasurements() { return totalMeasurements; }
    public void setTotalMeasurements(int totalMeasurements) { this.totalMeasurements = totalMeasurements; }

    public int getViolationCount() { return violationCount; }
    public void setViolationCount(int violationCount) { this.violationCount = violationCount; }

    public double getComplianceRate() { return complianceRate; }
    public void setComplianceRate(double complianceRate) { this.complianceRate = complianceRate; }

    public String getOverallStatus() { return overallStatus; }
    public void setOverallStatus(String overallStatus) { this.overallStatus = overallStatus; }
}