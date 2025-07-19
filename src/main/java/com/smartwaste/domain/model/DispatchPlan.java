package com.smartwaste.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "dispatch_plans")
public class DispatchPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "plan_name")
    private String planName;
    
    @Column(name = "vehicle_id")
    private String vehicleId;
    
    @Column(name = "driver_id")
    private String driverId;
    
    @Column(name = "waste_type")
    private String wasteType;
    
    @Column(name = "scheduled_date")
    private LocalDateTime scheduledDate;
    
    @Column(name = "estimated_duration")
    private Integer estimatedDuration; // in minutes
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PlanStatus status;
    
    @Column(name = "route_points", columnDefinition = "TEXT")
    private String routePointsJson; // JSON string of route points
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "plan_id")
    private String planId;
    
    @Column(name = "planned_start_time")
    private LocalDateTime plannedStartTime;

    // Constructors
    public DispatchPlan() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = PlanStatus.DRAFT;
    }

    public DispatchPlan(String planName, String vehicleId, String driverId, String wasteType) {
        this();
        this.planName = planName;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.wasteType = wasteType;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public String getWasteType() { return wasteType; }
    public void setWasteType(String wasteType) { this.wasteType = wasteType; }

    public LocalDateTime getScheduledDate() { return scheduledDate; }
    public void setScheduledDate(LocalDateTime scheduledDate) { this.scheduledDate = scheduledDate; }

    public Integer getEstimatedDuration() { return estimatedDuration; }
    public void setEstimatedDuration(Integer estimatedDuration) { this.estimatedDuration = estimatedDuration; }

    public PlanStatus getStatus() { return status; }
    public void setStatus(PlanStatus status) { 
        this.status = status; 
        this.updatedAt = LocalDateTime.now();
    }

    public String getRoutePointsJson() { return routePointsJson; }
    public void setRoutePointsJson(String routePointsJson) { this.routePointsJson = routePointsJson; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getPlanId() { return planId; }
    public void setPlanId(String planId) { this.planId = planId; }

    public LocalDateTime getPlannedStartTime() { return plannedStartTime; }
    public void setPlannedStartTime(LocalDateTime plannedStartTime) { this.plannedStartTime = plannedStartTime; }

    // Helper method to get tasks (for compatibility with existing code)
    public java.util.List<DispatchTask> getTasks() {
        // This would typically be a relationship or derived from the plan
        // For now, return empty list to avoid compilation errors
        return new java.util.ArrayList<>();
    }

    // Enum for plan status
    public enum PlanStatus {
        DRAFT, SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED
    }
}