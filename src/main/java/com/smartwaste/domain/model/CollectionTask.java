package com.smartwaste.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 收运任务实体类
 * Collection Task Entity
 */
@Entity
@Table(name = "collection_tasks")
public class CollectionTask {
    
    @Id
    @Column(name = "task_id", length = 64)
    private String taskId;
    
    @NotBlank
    @Column(name = "vehicle_id", length = 64, nullable = false)
    private String vehicleId;
    
    @Column(name = "driver_id", length = 64)
    private String driverId;
    
    @Column(name = "route_name", length = 200)
    private String routeName;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "task_status", nullable = false)
    private TaskStatus taskStatus = TaskStatus.PENDING;
    
    @Column(name = "planned_start_time")
    private LocalDateTime plannedStartTime;
    
    @Column(name = "actual_start_time")
    private LocalDateTime actualStartTime;
    
    @Column(name = "planned_end_time")
    private LocalDateTime plannedEndTime;
    
    @Column(name = "actual_end_time")
    private LocalDateTime actualEndTime;
    
    @Column(name = "total_containers")
    private Integer totalContainers;
    
    @Column(name = "completed_containers")
    private Integer completedContainers = 0;
    
    @Column(name = "total_weight")
    private Double totalWeight = 0.0;
    
    @Column(name = "distance_traveled")
    private Double distanceTraveled = 0.0;
    
    @Column(name = "fuel_consumption")
    private Double fuelConsumption;
    
    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;
    
    @Column(name = "notes", length = 1000)
    private String notes;

    // Constructors
    public CollectionTask() {
        this.createdTime = LocalDateTime.now();
    }

    public CollectionTask(String vehicleId, String routeName) {
        this();
        this.vehicleId = vehicleId;
        this.routeName = routeName;
    }

    // Getters and Setters
    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public String getRouteName() { return routeName; }
    public void setRouteName(String routeName) { this.routeName = routeName; }

    public TaskStatus getTaskStatus() { return taskStatus; }
    public void setTaskStatus(TaskStatus taskStatus) { this.taskStatus = taskStatus; }

    public LocalDateTime getPlannedStartTime() { return plannedStartTime; }
    public void setPlannedStartTime(LocalDateTime plannedStartTime) { this.plannedStartTime = plannedStartTime; }

    public LocalDateTime getActualStartTime() { return actualStartTime; }
    public void setActualStartTime(LocalDateTime actualStartTime) { this.actualStartTime = actualStartTime; }

    public LocalDateTime getPlannedEndTime() { return plannedEndTime; }
    public void setPlannedEndTime(LocalDateTime plannedEndTime) { this.plannedEndTime = plannedEndTime; }

    public LocalDateTime getActualEndTime() { return actualEndTime; }
    public void setActualEndTime(LocalDateTime actualEndTime) { this.actualEndTime = actualEndTime; }

    public Integer getTotalContainers() { return totalContainers; }
    public void setTotalContainers(Integer totalContainers) { this.totalContainers = totalContainers; }

    public Integer getCompletedContainers() { return completedContainers; }
    public void setCompletedContainers(Integer completedContainers) { this.completedContainers = completedContainers; }

    public Double getTotalWeight() { return totalWeight; }
    public void setTotalWeight(Double totalWeight) { this.totalWeight = totalWeight; }

    public Double getDistanceTraveled() { return distanceTraveled; }
    public void setDistanceTraveled(Double distanceTraveled) { this.distanceTraveled = distanceTraveled; }

    public Double getFuelConsumption() { return fuelConsumption; }
    public void setFuelConsumption(Double fuelConsumption) { this.fuelConsumption = fuelConsumption; }

    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    /**
     * 任务状态枚举
     */
    public enum TaskStatus {
        /**
         * 待执行
         */
        PENDING("待执行"),
        
        /**
         * 执行中
         */
        IN_PROGRESS("执行中"),
        
        /**
         * 已完成
         */
        COMPLETED("已完成"),
        
        /**
         * 已取消
         */
        CANCELLED("已取消"),
        
        /**
         * 异常中断
         */
        INTERRUPTED("异常中断");

        private final String displayName;

        TaskStatus(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}