package com.smartwaste.domain.model;

import com.smartwaste.domain.service.EnvironmentalMonitoringService;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 环保预警实体类
 * Environmental Alert Entity
 */
@Entity
@Table(name = "environmental_alerts")
public class EnvironmentalAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "alert_id", unique = true)
    private String alertId;
    
    @Column(name = "facility_id")
    private String facilityId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "alert_type")
    private EnvironmentalMonitoringService.AlertType alertType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "severity")
    private EnvironmentalMonitoringService.AlertSeverity severity;
    
    @Column(name = "message", length = 1000)
    private String message;
    
    @Column(name = "current_value")
    private Double currentValue;
    
    @Column(name = "standard_value")
    private Double standardValue;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;
    
    @Column(name = "resolved_by")
    private String resolvedBy;
    
    @Column(name = "resolution_action", length = 1000)
    private String resolutionAction;
    
    @Column(name = "status")
    private String status; // ACTIVE, RESOLVED, DISMISSED

    // 构造函数
    public EnvironmentalAlert() {
        this.alertId = generateAlertId();
        this.createdAt = LocalDateTime.now();
        this.status = "ACTIVE";
    }

    public EnvironmentalAlert(String facilityId, EnvironmentalMonitoringService.AlertType alertType, 
                            EnvironmentalMonitoringService.AlertSeverity severity, String message, 
                            double currentValue, double standardValue) {
        this();
        this.facilityId = facilityId;
        this.alertType = alertType;
        this.severity = severity;
        this.message = message;
        this.currentValue = currentValue;
        this.standardValue = standardValue;
    }

    private String generateAlertId() {
        return "ENV-ALERT-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAlertId() { return alertId; }
    public void setAlertId(String alertId) { this.alertId = alertId; }

    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }

    public EnvironmentalMonitoringService.AlertType getAlertType() { return alertType; }
    public void setAlertType(EnvironmentalMonitoringService.AlertType alertType) { this.alertType = alertType; }

    public EnvironmentalMonitoringService.AlertSeverity getSeverity() { return severity; }
    public void setSeverity(EnvironmentalMonitoringService.AlertSeverity severity) { this.severity = severity; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Double getCurrentValue() { return currentValue; }
    public void setCurrentValue(Double currentValue) { this.currentValue = currentValue; }

    public Double getStandardValue() { return standardValue; }
    public void setStandardValue(Double standardValue) { this.standardValue = standardValue; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDateTime resolvedAt) { this.resolvedAt = resolvedAt; }

    public String getResolvedBy() { return resolvedBy; }
    public void setResolvedBy(String resolvedBy) { this.resolvedBy = resolvedBy; }

    public String getResolutionAction() { return resolutionAction; }
    public void setResolutionAction(String resolutionAction) { this.resolutionAction = resolutionAction; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}