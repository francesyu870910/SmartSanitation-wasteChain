package com.smartwaste.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 混装混运预警实体类
 * Mixed Waste Alert Entity
 */
@Entity
@Table(name = "mixed_waste_alerts")
public class MixedWasteAlert {
    
    @Id
    @Column(name = "alert_id", length = 64)
    private String alertId;
    
    @Column(name = "vehicle_id", length = 64, nullable = false)
    private String vehicleId;
    
    @Column(name = "task_id", length = 64)
    private String taskId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "alert_type", nullable = false)
    private AlertType alertType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "severity", nullable = false)
    private AlertSeverity severity;
    
    @Column(name = "message", columnDefinition = "TEXT")
    private String message;
    
    @Column(name = "detected_waste_types", columnDefinition = "TEXT")
    private String detectedWasteTypes; // JSON格式存储检测到的垃圾类型
    
    @Column(name = "expected_waste_type")
    @Enumerated(EnumType.STRING)
    private WasteType expectedWasteType;
    
    @Column(name = "alert_time", nullable = false)
    private LocalDateTime alertTime = LocalDateTime.now();
    
    @Column(name = "is_resolved", nullable = false)
    private Boolean isResolved = false;
    
    @Column(name = "resolved_time")
    private LocalDateTime resolvedTime;
    
    @Column(name = "resolved_by", length = 64)
    private String resolvedBy;
    
    @Column(name = "resolution_action", columnDefinition = "TEXT")
    private String resolutionAction;
    
    @Column(name = "created_by", length = 64)
    private String createdBy = "SYSTEM";

    // Constructors
    public MixedWasteAlert() {}

    public MixedWasteAlert(String vehicleId, AlertType alertType, AlertSeverity severity, String message) {
        this.alertId = generateAlertId();
        this.vehicleId = vehicleId;
        this.alertType = alertType;
        this.severity = severity;
        this.message = message;
    }

    // Enums
    public enum AlertType {
        MIXED_WASTE_DETECTED("检测到混装"),
        WRONG_VEHICLE_TYPE("车辆类型不匹配"),
        CAPACITY_EXCEEDED("容量超限"),
        ROUTE_VIOLATION("路线违规");

        private final String displayName;

        AlertType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public enum AlertSeverity {
        LOW("低级"),
        MEDIUM("中级"),
        HIGH("高级"),
        CRITICAL("紧急");

        private final String displayName;

        AlertSeverity(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    // Getters and Setters
    public String getAlertId() { return alertId; }
    public void setAlertId(String alertId) { this.alertId = alertId; }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public AlertType getAlertType() { return alertType; }
    public void setAlertType(AlertType alertType) { this.alertType = alertType; }

    public AlertSeverity getSeverity() { return severity; }
    public void setSeverity(AlertSeverity severity) { this.severity = severity; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getDetectedWasteTypes() { return detectedWasteTypes; }
    public void setDetectedWasteTypes(String detectedWasteTypes) { this.detectedWasteTypes = detectedWasteTypes; }

    public WasteType getExpectedWasteType() { return expectedWasteType; }
    public void setExpectedWasteType(WasteType expectedWasteType) { this.expectedWasteType = expectedWasteType; }

    public LocalDateTime getAlertTime() { return alertTime; }
    public void setAlertTime(LocalDateTime alertTime) { this.alertTime = alertTime; }

    public Boolean getIsResolved() { return isResolved; }
    public void setIsResolved(Boolean isResolved) { this.isResolved = isResolved; }

    public LocalDateTime getResolvedTime() { return resolvedTime; }
    public void setResolvedTime(LocalDateTime resolvedTime) { this.resolvedTime = resolvedTime; }

    public String getResolvedBy() { return resolvedBy; }
    public void setResolvedBy(String resolvedBy) { this.resolvedBy = resolvedBy; }

    public String getResolutionAction() { return resolutionAction; }
    public void setResolutionAction(String resolutionAction) { this.resolutionAction = resolutionAction; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    // Helper methods
    private String generateAlertId() {
        return "ALERT_" + System.currentTimeMillis() + "_" + 
               java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}