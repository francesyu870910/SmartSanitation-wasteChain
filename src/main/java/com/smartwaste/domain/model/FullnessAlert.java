package com.smartwaste.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 满溢报警记录实体类
 * Fullness Alert Record Entity
 */
@Entity
@Table(name = "fullness_alerts")
public class FullnessAlert {
    
    @Id
    @Column(name = "alert_id", length = 64)
    private String alertId;
    
    @NotBlank
    @Column(name = "container_id", length = 64, nullable = false)
    private String containerId;
    
    @DecimalMin("0.0")
    @DecimalMax("1.0")
    @Column(name = "fullness_level", nullable = false)
    private Double fullnessLevel;
    
    @DecimalMin("0.0")
    @DecimalMax("1.0")
    @Column(name = "threshold", nullable = false)
    private Double threshold;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "alert_level", nullable = false)
    private AlertLevel alertLevel;
    
    @Column(name = "alert_time", nullable = false)
    private LocalDateTime alertTime;
    
    @Column(name = "is_resolved", nullable = false)
    private Boolean isResolved = false;
    
    @Column(name = "resolved_time")
    private LocalDateTime resolvedTime;
    
    @Column(name = "resolved_by", length = 64)
    private String resolvedBy;
    
    @Column(name = "location", length = 500)
    private String location;
    
    @Column(name = "message", length = 1000)
    private String message;

    // Constructors
    public FullnessAlert() {
        this.alertTime = LocalDateTime.now();
    }

    public FullnessAlert(String containerId, Double fullnessLevel, Double threshold, AlertLevel alertLevel) {
        this();
        this.containerId = containerId;
        this.fullnessLevel = fullnessLevel;
        this.threshold = threshold;
        this.alertLevel = alertLevel;
    }

    // Getters and Setters
    public String getAlertId() { return alertId; }
    public void setAlertId(String alertId) { this.alertId = alertId; }

    public String getContainerId() { return containerId; }
    public void setContainerId(String containerId) { this.containerId = containerId; }

    public Double getFullnessLevel() { return fullnessLevel; }
    public void setFullnessLevel(Double fullnessLevel) { this.fullnessLevel = fullnessLevel; }

    public Double getThreshold() { return threshold; }
    public void setThreshold(Double threshold) { this.threshold = threshold; }

    public AlertLevel getAlertLevel() { return alertLevel; }
    public void setAlertLevel(AlertLevel alertLevel) { this.alertLevel = alertLevel; }

    public LocalDateTime getAlertTime() { return alertTime; }
    public void setAlertTime(LocalDateTime alertTime) { this.alertTime = alertTime; }

    public Boolean getIsResolved() { return isResolved; }
    public void setIsResolved(Boolean isResolved) { this.isResolved = isResolved; }

    public LocalDateTime getResolvedTime() { return resolvedTime; }
    public void setResolvedTime(LocalDateTime resolvedTime) { this.resolvedTime = resolvedTime; }

    public String getResolvedBy() { return resolvedBy; }
    public void setResolvedBy(String resolvedBy) { this.resolvedBy = resolvedBy; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    /**
     * 报警级别枚举
     */
    public enum AlertLevel {
        /**
         * 低级报警 (70-80%)
         */
        LOW("低级报警"),
        
        /**
         * 中级报警 (80-90%)
         */
        MEDIUM("中级报警"),
        
        /**
         * 高级报警 (90-95%)
         */
        HIGH("高级报警"),
        
        /**
         * 紧急报警 (95%+)
         */
        CRITICAL("紧急报警");

        private final String displayName;

        AlertLevel(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}