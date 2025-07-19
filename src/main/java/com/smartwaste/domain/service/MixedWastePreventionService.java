package com.smartwaste.domain.service;

import com.smartwaste.domain.model.*;
import java.util.List;
import java.util.Optional;

/**
 * 混装混运防护服务接口
 * Mixed Waste Prevention Service Interface
 */
public interface MixedWastePreventionService {
    
    /**
     * 验证车辆垃圾类型匹配
     * @param vehicleId 车辆ID
     * @param wasteTypes 垃圾类型列表
     * @return 验证结果
     */
    WasteValidationResult validateWasteTypes(String vehicleId, List<WasteType> wasteTypes);
    
    /**
     * 检测混装风险
     * @param taskId 任务ID
     * @param vehicleId 车辆ID
     * @param containerIds 容器ID列表
     * @return 验证结果
     */
    WasteValidationResult detectMixedWasteRisk(String taskId, String vehicleId, List<String> containerIds);
    
    /**
     * 验证调度任务的垃圾类型一致性
     * @param task 调度任务
     * @return 验证结果
     */
    WasteValidationResult validateDispatchTask(DispatchTask task);
    
    /**
     * 创建混装预警
     * @param vehicleId 车辆ID
     * @param taskId 任务ID
     * @param alertType 预警类型
     * @param severity 严重程度
     * @param message 预警消息
     * @return 创建的预警
     */
    MixedWasteAlert createAlert(String vehicleId, String taskId, 
                               MixedWasteAlert.AlertType alertType, 
                               MixedWasteAlert.AlertSeverity severity, 
                               String message);
    
    /**
     * 解决预警
     * @param alertId 预警ID
     * @param resolvedBy 解决人
     * @param resolutionAction 解决措施
     * @return 更新后的预警
     */
    MixedWasteAlert resolveAlert(String alertId, String resolvedBy, String resolutionAction);
    
    /**
     * 获取车辆的未解决预警
     * @param vehicleId 车辆ID
     * @return 预警列表
     */
    List<MixedWasteAlert> getUnresolvedAlerts(String vehicleId);
    
    /**
     * 获取所有未解决的高优先级预警
     * @return 预警列表
     */
    List<MixedWasteAlert> getHighPriorityAlerts();
    
    /**
     * 重新分配车辆
     * @param taskId 任务ID
     * @param reason 重新分配原因
     * @return 新的车辆分配结果
     */
    VehicleMatchResult reassignVehicle(String taskId, String reason);
    
    /**
     * 检查车辆当前任务的垃圾类型一致性
     * @param vehicleId 车辆ID
     * @return 验证结果
     */
    WasteValidationResult checkVehicleConsistency(String vehicleId);
    
    /**
     * 获取预警统计信息
     * @return 统计信息
     */
    AlertStatistics getAlertStatistics();
    
    /**
     * 根据ID查找预警
     * @param alertId 预警ID
     * @return 预警信息
     */
    Optional<MixedWasteAlert> findAlertById(String alertId);
    
    /**
     * 自动检测和处理混装风险
     * @param vehicleId 车辆ID
     * @return 检测结果
     */
    List<WasteValidationResult> autoDetectMixedWasteRisks(String vehicleId);
    
    /**
     * 预警统计信息类
     */
    class AlertStatistics {
        private long totalAlerts;
        private long unresolvedAlerts;
        private long criticalAlerts;
        private long highAlerts;
        private long mediumAlerts;
        private long lowAlerts;
        private double resolutionRate;

        // Constructors
        public AlertStatistics() {}

        // Getters and Setters
        public long getTotalAlerts() { return totalAlerts; }
        public void setTotalAlerts(long totalAlerts) { this.totalAlerts = totalAlerts; }

        public long getUnresolvedAlerts() { return unresolvedAlerts; }
        public void setUnresolvedAlerts(long unresolvedAlerts) { this.unresolvedAlerts = unresolvedAlerts; }

        public long getCriticalAlerts() { return criticalAlerts; }
        public void setCriticalAlerts(long criticalAlerts) { this.criticalAlerts = criticalAlerts; }

        public long getHighAlerts() { return highAlerts; }
        public void setHighAlerts(long highAlerts) { this.highAlerts = highAlerts; }

        public long getMediumAlerts() { return mediumAlerts; }
        public void setMediumAlerts(long mediumAlerts) { this.mediumAlerts = mediumAlerts; }

        public long getLowAlerts() { return lowAlerts; }
        public void setLowAlerts(long lowAlerts) { this.lowAlerts = lowAlerts; }

        public double getResolutionRate() { return resolutionRate; }
        public void setResolutionRate(double resolutionRate) { this.resolutionRate = resolutionRate; }
    }
}