package com.smartwaste.domain.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 处置场实时状态
 * Disposal Facility Status
 */
public class DisposalFacilityStatus {
    private String facilityId;
    private String facilityName;
    private String operationStatus; // OPERATIONAL, MAINTENANCE, CLOSED, EMERGENCY
    private LocalDateTime statusTime;
    private int vehiclesInFacility;
    private int queueLength;
    private double currentCapacityUsage;
    private double maxCapacity;
    private double availableCapacity;
    private List<EquipmentStatus> equipmentStatuses;
    private List<GateStatus> gateStatuses;
    private EnvironmentalMetrics environmentalMetrics;
    private SafetyStatus safetyStatus;
    
    // 构造函数
    public DisposalFacilityStatus() {
        this.statusTime = LocalDateTime.now();
    }
    
    public DisposalFacilityStatus(String facilityId, String facilityName) {
        this();
        this.facilityId = facilityId;
        this.facilityName = facilityName;
    }
    
    // Getters and Setters
    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }
    
    public String getFacilityName() { return facilityName; }
    public void setFacilityName(String facilityName) { this.facilityName = facilityName; }
    
    public String getOperationStatus() { return operationStatus; }
    public void setOperationStatus(String operationStatus) { this.operationStatus = operationStatus; }
    
    public LocalDateTime getStatusTime() { return statusTime; }
    public void setStatusTime(LocalDateTime statusTime) { this.statusTime = statusTime; }
    
    public int getVehiclesInFacility() { return vehiclesInFacility; }
    public void setVehiclesInFacility(int vehiclesInFacility) { this.vehiclesInFacility = vehiclesInFacility; }
    
    public int getQueueLength() { return queueLength; }
    public void setQueueLength(int queueLength) { this.queueLength = queueLength; }
    
    public double getCurrentCapacityUsage() { return currentCapacityUsage; }
    public void setCurrentCapacityUsage(double currentCapacityUsage) { this.currentCapacityUsage = currentCapacityUsage; }
    
    public double getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(double maxCapacity) { this.maxCapacity = maxCapacity; }
    
    public double getAvailableCapacity() { return availableCapacity; }
    public void setAvailableCapacity(double availableCapacity) { this.availableCapacity = availableCapacity; }
    
    public List<EquipmentStatus> getEquipmentStatuses() { return equipmentStatuses; }
    public void setEquipmentStatuses(List<EquipmentStatus> equipmentStatuses) { this.equipmentStatuses = equipmentStatuses; }
    
    public List<GateStatus> getGateStatuses() { return gateStatuses; }
    public void setGateStatuses(List<GateStatus> gateStatuses) { this.gateStatuses = gateStatuses; }
    
    public EnvironmentalMetrics getEnvironmentalMetrics() { return environmentalMetrics; }
    public void setEnvironmentalMetrics(EnvironmentalMetrics environmentalMetrics) { this.environmentalMetrics = environmentalMetrics; }
    
    public SafetyStatus getSafetyStatus() { return safetyStatus; }
    public void setSafetyStatus(SafetyStatus safetyStatus) { this.safetyStatus = safetyStatus; }
    
    /**
     * 闸门状态
     */
    public static class GateStatus {
        private String gateNumber;
        private String status; // OPEN, CLOSED, MAINTENANCE
        private String currentVehicle;
        private LocalDateTime lastActivity;
        
        // Getters and Setters
        public String getGateNumber() { return gateNumber; }
        public void setGateNumber(String gateNumber) { this.gateNumber = gateNumber; }
        
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        
        public String getCurrentVehicle() { return currentVehicle; }
        public void setCurrentVehicle(String currentVehicle) { this.currentVehicle = currentVehicle; }
        
        public LocalDateTime getLastActivity() { return lastActivity; }
        public void setLastActivity(LocalDateTime lastActivity) { this.lastActivity = lastActivity; }
    }
    
    /**
     * 环境指标
     */
    public static class EnvironmentalMetrics {
        private double temperature;
        private double humidity;
        private double airQualityIndex;
        private double noiseLevel;
        private double dustLevel;
        private boolean withinLimits;
        
        // Getters and Setters
        public double getTemperature() { return temperature; }
        public void setTemperature(double temperature) { this.temperature = temperature; }
        
        public double getHumidity() { return humidity; }
        public void setHumidity(double humidity) { this.humidity = humidity; }
        
        public double getAirQualityIndex() { return airQualityIndex; }
        public void setAirQualityIndex(double airQualityIndex) { this.airQualityIndex = airQualityIndex; }
        
        public double getNoiseLevel() { return noiseLevel; }
        public void setNoiseLevel(double noiseLevel) { this.noiseLevel = noiseLevel; }
        
        public double getDustLevel() { return dustLevel; }
        public void setDustLevel(double dustLevel) { this.dustLevel = dustLevel; }
        
        public boolean isWithinLimits() { return withinLimits; }
        public void setWithinLimits(boolean withinLimits) { this.withinLimits = withinLimits; }
    }
    
    /**
     * 安全状态
     */
    public static class SafetyStatus {
        private String level; // SAFE, WARNING, DANGER
        private List<String> activeAlerts;
        private int emergencyExitsAvailable;
        private boolean fireSuppressionActive;
        
        // Getters and Setters
        public String getLevel() { return level; }
        public void setLevel(String level) { this.level = level; }
        
        public List<String> getActiveAlerts() { return activeAlerts; }
        public void setActiveAlerts(List<String> activeAlerts) { this.activeAlerts = activeAlerts; }
        
        public int getEmergencyExitsAvailable() { return emergencyExitsAvailable; }
        public void setEmergencyExitsAvailable(int emergencyExitsAvailable) { this.emergencyExitsAvailable = emergencyExitsAvailable; }
        
        public boolean isFireSuppressionActive() { return fireSuppressionActive; }
        public void setFireSuppressionActive(boolean fireSuppressionActive) { this.fireSuppressionActive = fireSuppressionActive; }
    }
}