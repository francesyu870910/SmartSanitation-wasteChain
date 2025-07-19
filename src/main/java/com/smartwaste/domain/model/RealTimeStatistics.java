package com.smartwaste.domain.model;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 实时统计数据模型
 */
public class RealTimeStatistics {
    
    private LocalDateTime timestamp;
    private String statisticsType; // DISPOSAL, COLLECTION, PROCESSING, VIOLATION
    private String timeRange; // HOURLY, DAILY, WEEKLY, MONTHLY
    
    // 投放统计
    private Long totalDisposals;
    private Map<String, Long> disposalsByWasteType;
    private Double totalWeight;
    private Map<String, Double> weightByWasteType;
    private Long activeUsers;
    private Double averageWeightPerDisposal;
    
    // 收运统计
    private Long totalCollections;
    private Long activeVehicles;
    private Double collectionEfficiency; // 收运效率百分比
    private Double averageCollectionTime; // 平均收运时间（分钟）
    private Long completedRoutes;
    private Long pendingCollections;
    
    // 处置统计
    private Long totalProcessed;
    private Map<String, Long> processedByFacility;
    private Double processingRate; // 处置率百分比
    private Double recyclingRate; // 回收率百分比
    private Map<String, Double> processingEfficiency;
    
    // 违规统计
    private Long totalViolations;
    private Map<String, Long> violationsByType;
    private Double violationRate; // 违规率百分比
    private Long resolvedViolations;
    private Long pendingViolations;
    
    // 设备统计
    private Long totalDevices;
    private Long onlineDevices;
    private Long offlineDevices;
    private Double deviceUptime; // 设备在线率
    private Map<String, Long> devicesByStatus;
    
    // 用户统计
    private Long totalUsers;
    private Long activeUsersToday;
    private Long newUsersToday;
    private Double userEngagementRate;
    
    // 环保指标
    private Map<String, Double> environmentalMetrics;
    private Long environmentalAlerts;
    
    // 构造函数
    public RealTimeStatistics() {
        this.timestamp = LocalDateTime.now();
    }
    
    public RealTimeStatistics(String statisticsType, String timeRange) {
        this();
        this.statisticsType = statisticsType;
        this.timeRange = timeRange;
    }
    
    // Getters and Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getStatisticsType() {
        return statisticsType;
    }
    
    public void setStatisticsType(String statisticsType) {
        this.statisticsType = statisticsType;
    }
    
    public String getTimeRange() {
        return timeRange;
    }
    
    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }
    
    public Long getTotalDisposals() {
        return totalDisposals;
    }
    
    public void setTotalDisposals(Long totalDisposals) {
        this.totalDisposals = totalDisposals;
    }
    
    public Map<String, Long> getDisposalsByWasteType() {
        return disposalsByWasteType;
    }
    
    public void setDisposalsByWasteType(Map<String, Long> disposalsByWasteType) {
        this.disposalsByWasteType = disposalsByWasteType;
    }
    
    public Double getTotalWeight() {
        return totalWeight;
    }
    
    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }
    
    public Map<String, Double> getWeightByWasteType() {
        return weightByWasteType;
    }
    
    public void setWeightByWasteType(Map<String, Double> weightByWasteType) {
        this.weightByWasteType = weightByWasteType;
    }
    
    public Long getActiveUsers() {
        return activeUsers;
    }
    
    public void setActiveUsers(Long activeUsers) {
        this.activeUsers = activeUsers;
    }
    
    public Double getAverageWeightPerDisposal() {
        return averageWeightPerDisposal;
    }
    
    public void setAverageWeightPerDisposal(Double averageWeightPerDisposal) {
        this.averageWeightPerDisposal = averageWeightPerDisposal;
    }
    
    public Long getTotalCollections() {
        return totalCollections;
    }
    
    public void setTotalCollections(Long totalCollections) {
        this.totalCollections = totalCollections;
    }
    
    public Long getActiveVehicles() {
        return activeVehicles;
    }
    
    public void setActiveVehicles(Long activeVehicles) {
        this.activeVehicles = activeVehicles;
    }
    
    public Double getCollectionEfficiency() {
        return collectionEfficiency;
    }
    
    public void setCollectionEfficiency(Double collectionEfficiency) {
        this.collectionEfficiency = collectionEfficiency;
    }
    
    public Double getAverageCollectionTime() {
        return averageCollectionTime;
    }
    
    public void setAverageCollectionTime(Double averageCollectionTime) {
        this.averageCollectionTime = averageCollectionTime;
    }
    
    public Long getCompletedRoutes() {
        return completedRoutes;
    }
    
    public void setCompletedRoutes(Long completedRoutes) {
        this.completedRoutes = completedRoutes;
    }
    
    public Long getPendingCollections() {
        return pendingCollections;
    }
    
    public void setPendingCollections(Long pendingCollections) {
        this.pendingCollections = pendingCollections;
    }
    
    public Long getTotalProcessed() {
        return totalProcessed;
    }
    
    public void setTotalProcessed(Long totalProcessed) {
        this.totalProcessed = totalProcessed;
    }
    
    public Map<String, Long> getProcessedByFacility() {
        return processedByFacility;
    }
    
    public void setProcessedByFacility(Map<String, Long> processedByFacility) {
        this.processedByFacility = processedByFacility;
    }
    
    public Double getProcessingRate() {
        return processingRate;
    }
    
    public void setProcessingRate(Double processingRate) {
        this.processingRate = processingRate;
    }
    
    public Double getRecyclingRate() {
        return recyclingRate;
    }
    
    public void setRecyclingRate(Double recyclingRate) {
        this.recyclingRate = recyclingRate;
    }
    
    public Map<String, Double> getProcessingEfficiency() {
        return processingEfficiency;
    }
    
    public void setProcessingEfficiency(Map<String, Double> processingEfficiency) {
        this.processingEfficiency = processingEfficiency;
    }
    
    public Long getTotalViolations() {
        return totalViolations;
    }
    
    public void setTotalViolations(Long totalViolations) {
        this.totalViolations = totalViolations;
    }
    
    public Map<String, Long> getViolationsByType() {
        return violationsByType;
    }
    
    public void setViolationsByType(Map<String, Long> violationsByType) {
        this.violationsByType = violationsByType;
    }
    
    public Double getViolationRate() {
        return violationRate;
    }
    
    public void setViolationRate(Double violationRate) {
        this.violationRate = violationRate;
    }
    
    public Long getResolvedViolations() {
        return resolvedViolations;
    }
    
    public void setResolvedViolations(Long resolvedViolations) {
        this.resolvedViolations = resolvedViolations;
    }
    
    public Long getPendingViolations() {
        return pendingViolations;
    }
    
    public void setPendingViolations(Long pendingViolations) {
        this.pendingViolations = pendingViolations;
    }
    
    public Long getTotalDevices() {
        return totalDevices;
    }
    
    public void setTotalDevices(Long totalDevices) {
        this.totalDevices = totalDevices;
    }
    
    public Long getOnlineDevices() {
        return onlineDevices;
    }
    
    public void setOnlineDevices(Long onlineDevices) {
        this.onlineDevices = onlineDevices;
    }
    
    public Long getOfflineDevices() {
        return offlineDevices;
    }
    
    public void setOfflineDevices(Long offlineDevices) {
        this.offlineDevices = offlineDevices;
    }
    
    public Double getDeviceUptime() {
        return deviceUptime;
    }
    
    public void setDeviceUptime(Double deviceUptime) {
        this.deviceUptime = deviceUptime;
    }
    
    public Map<String, Long> getDevicesByStatus() {
        return devicesByStatus;
    }
    
    public void setDevicesByStatus(Map<String, Long> devicesByStatus) {
        this.devicesByStatus = devicesByStatus;
    }
    
    public Long getTotalUsers() {
        return totalUsers;
    }
    
    public void setTotalUsers(Long totalUsers) {
        this.totalUsers = totalUsers;
    }
    
    public Long getActiveUsersToday() {
        return activeUsersToday;
    }
    
    public void setActiveUsersToday(Long activeUsersToday) {
        this.activeUsersToday = activeUsersToday;
    }
    
    public Long getNewUsersToday() {
        return newUsersToday;
    }
    
    public void setNewUsersToday(Long newUsersToday) {
        this.newUsersToday = newUsersToday;
    }
    
    public Double getUserEngagementRate() {
        return userEngagementRate;
    }
    
    public void setUserEngagementRate(Double userEngagementRate) {
        this.userEngagementRate = userEngagementRate;
    }
    
    public Map<String, Double> getEnvironmentalMetrics() {
        return environmentalMetrics;
    }
    
    public void setEnvironmentalMetrics(Map<String, Double> environmentalMetrics) {
        this.environmentalMetrics = environmentalMetrics;
    }
    
    public Long getEnvironmentalAlerts() {
        return environmentalAlerts;
    }
    
    public void setEnvironmentalAlerts(Long environmentalAlerts) {
        this.environmentalAlerts = environmentalAlerts;
    }
}