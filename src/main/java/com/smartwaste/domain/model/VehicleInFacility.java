package com.smartwaste.domain.model;

import java.time.LocalDateTime;

/**
 * 处置场内车辆信息
 * Vehicle In Facility
 */
public class VehicleInFacility {
    private String vehicleId;
    private String licensePlate;
    private String driverId;
    private String driverName;
    private LocalDateTime entryTime;
    private String currentLocation;
    private String status; // WAITING, UNLOADING, PROCESSING, COMPLETED
    private double entryWeight;
    private WasteType wasteType;
    private String gateNumber;
    private int estimatedProcessingTime; // 预计处理时间（分钟）
    private int actualProcessingTime; // 实际处理时间（分钟）
    private String processingStage; // 处理阶段
    
    // 构造函数
    public VehicleInFacility() {}
    
    public VehicleInFacility(String vehicleId, String licensePlate, LocalDateTime entryTime) {
        this.vehicleId = vehicleId;
        this.licensePlate = licensePlate;
        this.entryTime = entryTime;
    }
    
    // Getters and Setters
    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }
    
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    
    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }
    
    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
    
    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }
    
    public String getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(String currentLocation) { this.currentLocation = currentLocation; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public double getEntryWeight() { return entryWeight; }
    public void setEntryWeight(double entryWeight) { this.entryWeight = entryWeight; }
    
    public WasteType getWasteType() { return wasteType; }
    public void setWasteType(WasteType wasteType) { this.wasteType = wasteType; }
    
    public String getGateNumber() { return gateNumber; }
    public void setGateNumber(String gateNumber) { this.gateNumber = gateNumber; }
    
    public int getEstimatedProcessingTime() { return estimatedProcessingTime; }
    public void setEstimatedProcessingTime(int estimatedProcessingTime) { this.estimatedProcessingTime = estimatedProcessingTime; }
    
    public int getActualProcessingTime() { return actualProcessingTime; }
    public void setActualProcessingTime(int actualProcessingTime) { this.actualProcessingTime = actualProcessingTime; }
    
    public String getProcessingStage() { return processingStage; }
    public void setProcessingStage(String processingStage) { this.processingStage = processingStage; }
}