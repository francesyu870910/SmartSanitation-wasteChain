package com.smartwaste.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 处置场出场记录
 * Disposal Exit Record
 */
@Entity
@Table(name = "disposal_exits")
public class DisposalExit {
    @Id
    @Column(name = "exit_id", length = 50)
    private String exitId;
    
    @Column(name = "entry_id", nullable = false, length = 50)
    private String entryId;
    
    @Column(name = "vehicle_id", nullable = false, length = 50)
    private String vehicleId;
    
    @Column(name = "facility_id", nullable = false, length = 50)
    private String facilityId;
    
    @Column(name = "exit_time", nullable = false)
    private LocalDateTime exitTime;
    
    @Column(name = "exit_weight", nullable = false)
    private Double exitWeight;
    
    @Column(name = "processed_weight")
    private Double processedWeight;
    
    @Column(name = "duration_minutes")
    private Integer durationMinutes;
    
    @Column(name = "gate_number", length = 10)
    private String gateNumber;
    
    @Column(name = "operator_id", length = 50)
    private String operatorId;
    
    @Column(name = "disposal_method", length = 50)
    private String disposalMethod; // INCINERATION, LANDFILL, RECYCLING, COMPOSTING
    
    @Column(name = "disposal_efficiency")
    private Double disposalEfficiency;
    
    @Column(name = "remarks", length = 500)
    private String remarks;
    
    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;
    
    // 构造函数
    public DisposalExit() {
        this.exitId = generateExitId();
        this.exitTime = LocalDateTime.now();
        this.createdTime = LocalDateTime.now();
    }
    
    public DisposalExit(String entryId, String vehicleId, String facilityId, double exitWeight) {
        this();
        this.entryId = entryId;
        this.vehicleId = vehicleId;
        this.facilityId = facilityId;
        this.exitWeight = exitWeight;
    }
    
    private String generateExitId() {
        return "EXIT-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
    }
    
    // Getters and Setters
    public String getExitId() { return exitId; }
    public void setExitId(String exitId) { this.exitId = exitId; }
    
    public String getEntryId() { return entryId; }
    public void setEntryId(String entryId) { this.entryId = entryId; }
    
    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }
    
    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }
    
    public LocalDateTime getExitTime() { return exitTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }
    
    public Double getExitWeight() { return exitWeight; }
    public void setExitWeight(Double exitWeight) { this.exitWeight = exitWeight; }
    
    public Double getProcessedWeight() { return processedWeight; }
    public void setProcessedWeight(Double processedWeight) { this.processedWeight = processedWeight; }
    
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
    
    public String getGateNumber() { return gateNumber; }
    public void setGateNumber(String gateNumber) { this.gateNumber = gateNumber; }
    
    public String getOperatorId() { return operatorId; }
    public void setOperatorId(String operatorId) { this.operatorId = operatorId; }
    
    public String getDisposalMethod() { return disposalMethod; }
    public void setDisposalMethod(String disposalMethod) { this.disposalMethod = disposalMethod; }
    
    public Double getDisposalEfficiency() { return disposalEfficiency; }
    public void setDisposalEfficiency(Double disposalEfficiency) { this.disposalEfficiency = disposalEfficiency; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }
}