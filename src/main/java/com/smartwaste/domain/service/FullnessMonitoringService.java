package com.smartwaste.domain.service;

import com.smartwaste.domain.model.FullnessAlert;
import java.util.List;
import java.util.Optional;

/**
 * 满溢监控服务接口
 * Fullness Monitoring Service Interface
 */
public interface FullnessMonitoringService {
    
    /**
     * 检查垃圾桶满溢状态
     */
    FullnessAlert checkContainerFullness(String containerId, Double currentFullness);
    
    /**
     * 创建满溢报警
     */
    FullnessAlert createAlert(String containerId, Double fullnessLevel, Double threshold, 
                             FullnessAlert.AlertLevel alertLevel, String message);
    
    /**
     * 解决报警
     */
    FullnessAlert resolveAlert(String alertId, String resolvedBy);
    
    /**
     * 获取未解决的报警
     */
    List<FullnessAlert> getUnresolvedAlerts();
    
    /**
     * 根据报警级别获取报警
     */
    List<FullnessAlert> getAlertsByLevel(FullnessAlert.AlertLevel alertLevel);
    
    /**
     * 根据垃圾桶ID获取报警历史
     */
    List<FullnessAlert> getContainerAlertHistory(String containerId);
    
    /**
     * 批量检查满溢状态
     */
    List<FullnessAlert> batchCheckFullness();
    
    /**
     * 发送报警通知
     */
    void sendAlertNotification(FullnessAlert alert);
    
    /**
     * 计算报警级别
     */
    FullnessAlert.AlertLevel calculateAlertLevel(Double fullnessLevel);
    
    /**
     * 获取满溢统计信息
     */
    FullnessStatistics getFullnessStatistics();
    
    /**
     * 满溢统计信息类
     */
    class FullnessStatistics {
        private long totalContainers;
        private long alertContainers;
        private long criticalContainers;
        private double averageFullness;
        private long totalAlerts;
        private long unresolvedAlerts;

        // Constructors
        public FullnessStatistics() {}

        public FullnessStatistics(long totalContainers, long alertContainers, long criticalContainers,
                                 double averageFullness, long totalAlerts, long unresolvedAlerts) {
            this.totalContainers = totalContainers;
            this.alertContainers = alertContainers;
            this.criticalContainers = criticalContainers;
            this.averageFullness = averageFullness;
            this.totalAlerts = totalAlerts;
            this.unresolvedAlerts = unresolvedAlerts;
        }

        // Getters and Setters
        public long getTotalContainers() { return totalContainers; }
        public void setTotalContainers(long totalContainers) { this.totalContainers = totalContainers; }
        public long getAlertContainers() { return alertContainers; }
        public void setAlertContainers(long alertContainers) { this.alertContainers = alertContainers; }
        public long getCriticalContainers() { return criticalContainers; }
        public void setCriticalContainers(long criticalContainers) { this.criticalContainers = criticalContainers; }
        public double getAverageFullness() { return averageFullness; }
        public void setAverageFullness(double averageFullness) { this.averageFullness = averageFullness; }
        public long getTotalAlerts() { return totalAlerts; }
        public void setTotalAlerts(long totalAlerts) { this.totalAlerts = totalAlerts; }
        public long getUnresolvedAlerts() { return unresolvedAlerts; }
        public void setUnresolvedAlerts(long unresolvedAlerts) { this.unresolvedAlerts = unresolvedAlerts; }
    }
}