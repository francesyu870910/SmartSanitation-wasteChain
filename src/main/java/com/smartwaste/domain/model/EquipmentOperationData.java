package com.smartwaste.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 设备运行数据
 * Equipment Operation Data
 */
@Entity
@Table(name = "equipment_operation_data")
public class EquipmentOperationData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "facility_id", nullable = false, length = 50)
    private String facilityId;
    
    @Column(name = "equipment_id", nullable = false, length = 50)
    private String equipmentId;
    
    @Column(name = "equipment_type", length = 50)
    private String equipmentType; // INCINERATOR, CRUSHER, CONVEYOR, WEIGHING_SCALE
    
    @Column(name = "operation_status", length = 20)
    private String operationStatus; // RUNNING, STOPPED, MAINTENANCE, ERROR
    
    @Column(name = "temperature")
    private Double temperature;
    
    @Column(name = "pressure")
    private Double pressure;
    
    @Column(name = "power_consumption")
    private Double powerConsumption;
    
    @Column(name = "throughput_rate")
    private Double throughputRate;
    
    @Column(name = "efficiency_percentage")
    private Double efficiencyPercentage;
    
    @Column(name = "error_code", length = 20)
    private String errorCode;
    
    @Column(name = "error_message", length = 200)
    private String errorMessage;
    
    @Column(name = "maintenance_due_date")
    private LocalDateTime maintenanceDueDate;
    
    @Column(name = "last_maintenance_date")
    private LocalDateTime lastMaintenanceDate;
    
    @Column(name = "operating_hours")
    private Double operatingHours;
    
    @Column(name = "data_timestamp", nullable = false)
    private LocalDateTime dataTimestamp;
    
    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;
    
    // 扩展数据字段（JSON格式存储）
    @Column(name = "extended_data", columnDefinition = "TEXT")
    private String extendedData;
    
    // 构造函数
    public EquipmentOperationData() {
        this.dataTimestamp = LocalDateTime.now();
        this.createdTime = LocalDateTime.now();
    }
    
    public EquipmentOperationData(String facilityId, String equipmentId, String equipmentType) {
        this();
        this.facilityId = facilityId;
        this.equipmentId = equipmentId;
        this.equipmentType = equipmentType;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }
    
    public String getEquipmentId() { return equipmentId; }
    public void setEquipmentId(String equipmentId) { this.equipmentId = equipmentId; }
    
    public String getEquipmentType() { return equipmentType; }
    public void setEquipmentType(String equipmentType) { this.equipmentType = equipmentType; }
    
    public String getOperationStatus() { return operationStatus; }
    public void setOperationStatus(String operationStatus) { this.operationStatus = operationStatus; }
    
    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }
    
    public Double getPressure() { return pressure; }
    public void setPressure(Double pressure) { this.pressure = pressure; }
    
    public Double getPowerConsumption() { return powerConsumption; }
    public void setPowerConsumption(Double powerConsumption) { this.powerConsumption = powerConsumption; }
    
    public Double getThroughputRate() { return throughputRate; }
    public void setThroughputRate(Double throughputRate) { this.throughputRate = throughputRate; }
    
    public Double getEfficiencyPercentage() { return efficiencyPercentage; }
    public void setEfficiencyPercentage(Double efficiencyPercentage) { this.efficiencyPercentage = efficiencyPercentage; }
    
    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }
    
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    
    public LocalDateTime getMaintenanceDueDate() { return maintenanceDueDate; }
    public void setMaintenanceDueDate(LocalDateTime maintenanceDueDate) { this.maintenanceDueDate = maintenanceDueDate; }
    
    public LocalDateTime getLastMaintenanceDate() { return lastMaintenanceDate; }
    public void setLastMaintenanceDate(LocalDateTime lastMaintenanceDate) { this.lastMaintenanceDate = lastMaintenanceDate; }
    
    public Double getOperatingHours() { return operatingHours; }
    public void setOperatingHours(Double operatingHours) { this.operatingHours = operatingHours; }
    
    public LocalDateTime getDataTimestamp() { return dataTimestamp; }
    public void setDataTimestamp(LocalDateTime dataTimestamp) { this.dataTimestamp = dataTimestamp; }
    
    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }
    
    public String getExtendedData() { return extendedData; }
    public void setExtendedData(String extendedData) { this.extendedData = extendedData; }
}