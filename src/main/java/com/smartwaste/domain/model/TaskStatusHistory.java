package com.smartwaste.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 任务状态历史记录
 * Task Status History
 */
@Entity
@Table(name = "task_status_history")
public class TaskStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "task_id", nullable = false)
    private String taskId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "old_status")
    private TaskStatus oldStatus;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "new_status", nullable = false)
    private TaskStatus newStatus;
    
    @Column(name = "update_by", nullable = false)
    private String updateBy;
    
    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;
    
    @Column(name = "remarks", length = 500)
    private String remarks;
    
    @Column(name = "operation_type", length = 50)
    private String operationType; // CREATE, UPDATE, CONFIRM, REJECT
    
    // 构造函数
    public TaskStatusHistory() {}
    
    public TaskStatusHistory(String taskId, TaskStatus oldStatus, TaskStatus newStatus, String updateBy, String operationType) {
        this.taskId = taskId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.updateBy = updateBy;
        this.operationType = operationType;
        this.updateTime = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }
    
    public TaskStatus getOldStatus() { return oldStatus; }
    public void setOldStatus(TaskStatus oldStatus) { this.oldStatus = oldStatus; }
    
    public TaskStatus getNewStatus() { return newStatus; }
    public void setNewStatus(TaskStatus newStatus) { this.newStatus = newStatus; }
    
    public String getUpdateBy() { return updateBy; }
    public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
    
    public LocalDateTime getUpdateTime() { return updateTime; }
    public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    public String getOperationType() { return operationType; }
    public void setOperationType(String operationType) { this.operationType = operationType; }
}