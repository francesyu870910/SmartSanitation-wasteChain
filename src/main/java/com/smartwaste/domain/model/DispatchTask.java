package com.smartwaste.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 调度任务实体类
 * Dispatch Task Entity
 */
@Entity
@Table(name = "dispatch_tasks")
public class DispatchTask {
    
    @Id
    @Column(name = "task_id", length = 64)
    private String taskId;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "waste_type", nullable = false)
    private WasteType wasteType;
    
    @Column(name = "container_ids", columnDefinition = "TEXT")
    private String containerIds; // JSON格式存储容器ID列表
    
    @Column(name = "assigned_vehicle_id", length = 64)
    private String assignedVehicleId;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DispatchStatus status = DispatchStatus.PENDING;
    
    @Column(name = "priority", nullable = false)
    private Integer priority = 1; // 1-5, 5为最高优先级
    
    @Column(name = "estimated_weight")
    private Double estimatedWeight;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "scheduled_time")
    private LocalDateTime scheduledTime;
    
    @Column(name = "completed_at")
    private LocalDateTime completedAt;
    
    @Column(name = "notes", length = 1000)
    private String notes;

    // Constructors
    public DispatchTask() {}

    public DispatchTask(String taskId, WasteType wasteType, String containerIds) {
        this.taskId = taskId;
        this.wasteType = wasteType;
        this.containerIds = containerIds;
    }

    // Getters and Setters
    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public WasteType getWasteType() { return wasteType; }
    public void setWasteType(WasteType wasteType) { this.wasteType = wasteType; }

    public String getContainerIds() { return containerIds; }
    public void setContainerIds(String containerIds) { this.containerIds = containerIds; }

    public String getAssignedVehicleId() { return assignedVehicleId; }
    public void setAssignedVehicleId(String assignedVehicleId) { this.assignedVehicleId = assignedVehicleId; }

    public DispatchStatus getStatus() { return status; }
    public void setStatus(DispatchStatus status) { this.status = status; }

    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = priority; }

    public Double getEstimatedWeight() { return estimatedWeight; }
    public void setEstimatedWeight(Double estimatedWeight) { this.estimatedWeight = estimatedWeight; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getScheduledTime() { return scheduledTime; }
    public void setScheduledTime(LocalDateTime scheduledTime) { this.scheduledTime = scheduledTime; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}