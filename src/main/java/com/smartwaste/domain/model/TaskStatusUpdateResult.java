package com.smartwaste.domain.model;

import java.time.LocalDateTime;

/**
 * 任务状态更新结果
 * Task Status Update Result
 */
public class TaskStatusUpdateResult {
    private String taskId;
    private TaskStatus oldStatus;
    private TaskStatus newStatus;
    private String updateBy;
    private boolean success;
    private String message;
    private String remarks;
    private LocalDateTime timestamp;

    // 构造函数
    public TaskStatusUpdateResult() {
        this.timestamp = LocalDateTime.now();
    }

    public TaskStatusUpdateResult(String taskId, TaskStatus oldStatus, TaskStatus newStatus, String updateBy) {
        this();
        this.taskId = taskId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.updateBy = updateBy;
    }

    // Getters and Setters
    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public TaskStatus getOldStatus() { return oldStatus; }
    public void setOldStatus(TaskStatus oldStatus) { this.oldStatus = oldStatus; }

    public TaskStatus getNewStatus() { return newStatus; }
    public void setNewStatus(TaskStatus newStatus) { this.newStatus = newStatus; }

    public String getUpdateBy() { return updateBy; }
    public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}