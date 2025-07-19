package com.smartwaste.domain.model;

import java.time.LocalDateTime;

/**
 * 通知推送结果
 * Notification Result
 */
public class NotificationResult {
    private String recipientId;
    private String recipientType;
    private boolean success;
    private String message;
    private LocalDateTime timestamp;
    private String channel; // PUSH, EMAIL, SMS
    private String errorCode;

    // 构造函数
    public NotificationResult() {
        this.timestamp = LocalDateTime.now();
    }

    public NotificationResult(String recipientId, String recipientType, boolean success, String message) {
        this();
        this.recipientId = recipientId;
        this.recipientType = recipientType;
        this.success = success;
        this.message = message;
    }

    // Getters and Setters
    public String getRecipientId() { return recipientId; }
    public void setRecipientId(String recipientId) { this.recipientId = recipientId; }

    public String getRecipientType() { return recipientType; }
    public void setRecipientType(String recipientType) { this.recipientType = recipientType; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }

    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }
}