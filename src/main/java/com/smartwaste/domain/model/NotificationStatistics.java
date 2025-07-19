package com.smartwaste.domain.model;

import java.time.LocalDateTime;

/**
 * 通知统计信息
 * Notification Statistics
 */
public class NotificationStatistics {
    private long totalNotifications;
    private long sentNotifications;
    private long failedNotifications;
    private long readNotifications;
    private long unreadNotifications;
    private long todayNotifications;
    private long highPriorityNotifications;
    private double deliveryRate; // 发送成功率
    private double readRate; // 阅读率
    private LocalDateTime generatedAt;

    // 构造函数
    public NotificationStatistics() {
        this.generatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public long getTotalNotifications() { return totalNotifications; }
    public void setTotalNotifications(long totalNotifications) { this.totalNotifications = totalNotifications; }

    public long getSentNotifications() { return sentNotifications; }
    public void setSentNotifications(long sentNotifications) { this.sentNotifications = sentNotifications; }

    public long getFailedNotifications() { return failedNotifications; }
    public void setFailedNotifications(long failedNotifications) { this.failedNotifications = failedNotifications; }

    public long getReadNotifications() { return readNotifications; }
    public void setReadNotifications(long readNotifications) { this.readNotifications = readNotifications; }

    public long getUnreadNotifications() { return unreadNotifications; }
    public void setUnreadNotifications(long unreadNotifications) { this.unreadNotifications = unreadNotifications; }

    public long getTodayNotifications() { return todayNotifications; }
    public void setTodayNotifications(long todayNotifications) { this.todayNotifications = todayNotifications; }

    public long getHighPriorityNotifications() { return highPriorityNotifications; }
    public void setHighPriorityNotifications(long highPriorityNotifications) { this.highPriorityNotifications = highPriorityNotifications; }

    public double getDeliveryRate() { return deliveryRate; }
    public void setDeliveryRate(double deliveryRate) { this.deliveryRate = deliveryRate; }

    public double getReadRate() { return readRate; }
    public void setReadRate(double readRate) { this.readRate = readRate; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}