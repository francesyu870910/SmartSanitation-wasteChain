package com.smartwaste.domain.model;

import com.smartwaste.domain.service.DispatchNotificationService;
import java.time.LocalDateTime;

/**
 * 任务确认结果
 * Task Confirmation Result
 */
public class TaskConfirmationResult {
    private String taskId;
    private String driverId;
    private DispatchNotificationService.TaskConfirmationType confirmationType;
    private boolean success;
    private String message;
    private TaskStatus newTaskStatus;
    private LocalDateTime timestamp;

    // 构造函数
    public TaskConfirmationResult() {
        this.timestamp = LocalDateTime.now();
    }

    public TaskConfirmationResult(String taskId, String driverId, DispatchNotificationService.TaskConfirmationType confirmationType, boolean success) {
        this();
        this.taskId = taskId;
        this.driverId = driverId;
        this.confirmationType = confirmationType;
        this.success = success;
    }

    // Getters and Setters
    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public DispatchNotificationService.TaskConfirmationType getConfirmationType() { return confirmationType; }
    public void setConfirmationType(DispatchNotificationService.TaskConfirmationType confirmationType) { this.confirmationType = confirmationType; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public TaskStatus getNewTaskStatus() { return newTaskStatus; }
    public void setNewTaskStatus(TaskStatus newTaskStatus) { this.newTaskStatus = newTaskStatus; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}