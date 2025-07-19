package com.smartwaste.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 处置场进场记录
 * Disposal Entry Record
 */
@Entity
@Table(name = "disposal_entries")
public class DisposalEntry {
    @Id
    @Column(name = "entry_id", length = 50)
    private String entryId;
    
    @Column(name = "vehicle_id", nullable = false, length = 50)
    private String vehicleId;
    
    @Column(name = "facility_id", nullable = false, length = 50)
    private String facilityId;
    
    @Column(name = "license_plate", length = 20)
    private String licensePlate;
    
    @Column(name = "driver_id", length = 50)
    private String driverId;
    
    @Column(name = "entry_time", nullable = false)
    private LocalDateTime entryTime;
    
    @Column(name = "entry_weight", nullable = false)
    private Double entryWeight;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "waste_type", nullable = false)
    private WasteType wasteType;
    
    @Column(name = "gate_number", length = 10)
    private String gateNumber;
    
    @Column(name = "recognition_method", length = 20)
    private String recognitionMethod; // AUTO, MANUAL, RFID
    
    @Column(name = "operator_id", length = 50)
    private String operatorId;
    
    @Column(name = "remarks", length = 500)
    private String remarks;
    
    @Column(name = "is_processed", nullable = false)
    private Boolean isProcessed = false;
    
    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;
    
    // 构造函数
    public DisposalEntry() {
        this.entryId = generateEntryId();
        this.entryTime = LocalDateTime.now();
        this.createdTime = LocalDateTime.now();
    }
    
    public DisposalEntry(String vehicleId, String facilityId, double entryWeight, WasteType wasteType) {
        this();
        this.vehicleId = vehicleId;
        this.facilityId = facilityId;
        this.entryWeight = entryWeight;
        this.wasteType = wasteType;
    }
    
    private String generateEntryId() {
        return "ENTRY-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
    }
    
    // Getters and Setters
    public String getEntryId() { return entryId; }
    public void setEntryId(String entryId) { this.entryId = entryId; }
    
    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }
    
    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }
    
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    
    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }
    
    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }
    
    public Double getEntryWeight() { return entryWeight; }
    public void setEntryWeight(Double entryWeight) { this.entryWeight = entryWeight; }
    
    public WasteType getWasteType() { return wasteType; }
    public void setWasteType(WasteType wasteType) { this.wasteType = wasteType; }
    
    public String getGateNumber() { return gateNumber; }
    public void setGateNumber(String gateNumber) { this.gateNumber = gateNumber; }
    
    public String getRecognitionMethod() { return recognitionMethod; }
    public void setRecognitionMethod(String recognitionMethod) { this.recognitionMethod = recognitionMethod; }
    
    public String getOperatorId() { return operatorId; }
    public void setOperatorId(String operatorId) { this.operatorId = operatorId; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    public Boolean getIsProcessed() { return isProcessed; }
    public void setIsProcessed(Boolean isProcessed) { this.isProcessed = isProcessed; }
    
    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }
}