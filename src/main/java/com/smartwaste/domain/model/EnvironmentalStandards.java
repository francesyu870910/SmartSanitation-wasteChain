package com.smartwaste.domain.model;

import com.smartwaste.domain.service.EnvironmentalMonitoringService;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 环保标准配置
 * Environmental Standards Configuration
 */
public class EnvironmentalStandards {
    private String facilityId;
    private Map<EnvironmentalMonitoringService.IndicatorType, Double> standardLimits;
    private Map<EnvironmentalMonitoringService.IndicatorType, Double> warningThresholds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private boolean active;

    // 构造函数
    public EnvironmentalStandards() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.active = true;
    }

    // Getters and Setters
    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }

    public Map<EnvironmentalMonitoringService.IndicatorType, Double> getStandardLimits() { return standardLimits; }
    public void setStandardLimits(Map<EnvironmentalMonitoringService.IndicatorType, Double> standardLimits) { this.standardLimits = standardLimits; }

    public Map<EnvironmentalMonitoringService.IndicatorType, Double> getWarningThresholds() { return warningThresholds; }
    public void setWarningThresholds(Map<EnvironmentalMonitoringService.IndicatorType, Double> warningThresholds) { this.warningThresholds = warningThresholds; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}