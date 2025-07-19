package com.smartwaste.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 通知记录
 * Notification Record
 */
@Entity
@Table(name = "notification_records")
public class NotificationRecord {
    @Id
    @Column(name = "notification_id", length = 50)
    private String notificationId;
    
    @Column(name = "recipient_id", nullable = false, length = 50)
    private String recipientId;
    
    @Column(name = "recipient_type", nullable = false, length = 20)
    private String recipientType; // DRIVER, MANAGER
    
    @Column(name = "title", nullable = false, length = 200)
    private String title;
    
    @Column(name = "content", nullable = false, length = 1000)
    private String content;
    
    @Column(name = "notification_type", length = 50)
    private String notificationType; // DISPATCH_PLAN, TASK_REMINDER, ALERT
    
    @Column(name = "channel", length = 20)
    private String channel; // SMS, PUSH, EMAIL
    
    @Column(name = "sent_time", nullable = false)
    private LocalDateTime sentTime;
    
    @Column(name = "read_time")
    private LocalDateTime readTime;
    
    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;
    
    @Column(name = "related_task_id", length = 50)
    private String relatedTaskId;
    
    @Column(name = "priority", length = 20)
    private String priority; // HIGH, MEDIUM, LOW
    
    @Column(name = "status", length = 20)
    private String status; // SENT, FAILED, PENDING
    
    // 构造函数
    public NotificationRecord() {
        this.notificationId = generateNotificationId();
        this.sentTime = LocalDateTime.now();
    }
    
    public NotificationRecord(String recipientId, String recipientType, String title, String content) {
        this();
        this.recipientId = recipientId;
        this.recipientType = recipientType;
        this.title = title;
        this.content = content;
    }
    
    private String generateNotificationId() {
        return "NOTIF-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
    }
    
    // Getters and Setters
    public String getNotificationId() { return notificationId; }
    public void setNotificationId(String notificationId) { this.notificationId = notificationId; }
    
    public String getRecipientId() { return recipientId; }
    public void setRecipientId(String recipientId) { this.recipientId = recipientId; }
    
    public String getRecipientType() { return recipientType; }
    public void setRecipientType(String recipientType) { this.recipientType = recipientType; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public String getNotificationType() { return notificationType; }
    public void setNotificationType(String notificationType) { this.notificationType = notificationType; }
    
    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }
    
    public LocalDateTime getSentTime() { return sentTime; }
    public void setSentTime(LocalDateTime sentTime) { this.sentTime = sentTime; }
    
    public LocalDateTime getReadTime() { return readTime; }
    public void setReadTime(LocalDateTime readTime) { this.readTime = readTime; }
    
    public Boolean getIsRead() { return isRead; }
    public void setIsRead(Boolean isRead) { this.isRead = isRead; }
    
    public String getRelatedTaskId() { return relatedTaskId; }
    public void setRelatedTaskId(String relatedTaskId) { this.relatedTaskId = relatedTaskId; }
    
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}