package com.smartwaste.domain.model;

import java.time.LocalDateTime;

/**
 * 车辆识别结果
 * Vehicle Recognition Result
 */
public class VehicleRecognitionResult {
    private String vehicleId;
    private String licensePlate;
    private String facilityId;
    private boolean recognized;
    private double confidence;
    private String recognitionMethod;
    private LocalDateTime recognitionTime;
    private String gateNumber;
    private String message;
    private VehicleInfo vehicleInfo;
    
    // 构造函数
    public VehicleRecognitionResult() {
        this.recognitionTime = LocalDateTime.now();
    }
    
    public VehicleRecognitionResult(String licensePlate, String facilityId, boolean recognized) {
        this();
        this.licensePlate = licensePlate;
        this.facilityId = facilityId;
        this.recognized = recognized;
    }
    
    // Getters and Setters
    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }
    
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    
    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }
    
    public boolean isRecognized() { return recognized; }
    public void setRecognized(boolean recognized) { this.recognized = recognized; }
    
    public double getConfidence() { return confidence; }
    public void setConfidence(double confidence) { this.confidence = confidence; }
    
    public String getRecognitionMethod() { return recognitionMethod; }
    public void setRecognitionMethod(String recognitionMethod) { this.recognitionMethod = recognitionMethod; }
    
    public LocalDateTime getRecognitionTime() { return recognitionTime; }
    public void setRecognitionTime(LocalDateTime recognitionTime) { this.recognitionTime = recognitionTime; }
    
    public String getGateNumber() { return gateNumber; }
    public void setGateNumber(String gateNumber) { this.gateNumber = gateNumber; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public VehicleInfo getVehicleInfo() { return vehicleInfo; }
    public void setVehicleInfo(VehicleInfo vehicleInfo) { this.vehicleInfo = vehicleInfo; }
    
    /**
     * 车辆信息内部类
     */
    public static class VehicleInfo {
        private String vehicleId;
        private String vehicleType;
        private String driverId;
        private String driverName;
        private double capacity;
        private WasteType allowedWasteType;
        
        // Getters and Setters
        public String getVehicleId() { return vehicleId; }
        public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }
        
        public String getVehicleType() { return vehicleType; }
        public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
        
        public String getDriverId() { return driverId; }
        public void setDriverId(String driverId) { this.driverId = driverId; }
        
        public String getDriverName() { return driverName; }
        public void setDriverName(String driverName) { this.driverName = driverName; }
        
        public double getCapacity() { return capacity; }
        public void setCapacity(double capacity) { this.capacity = capacity; }
        
        public WasteType getAllowedWasteType() { return allowedWasteType; }
        public void setAllowedWasteType(WasteType allowedWasteType) { this.allowedWasteType = allowedWasteType; }
    }
}