package com.smartwaste.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 称重记录实体类
 * Weighing Record Entity
 */
@Entity
@Table(name = "weighing_records")
public class WeighingRecord {
    
    @Id
    @Column(name = "record_id", length = 64)
    private String recordId;
    
    @NotBlank
    @Column(name = "device_id", length = 64, nullable = false)
    private String deviceId;
    
    @Column(name = "user_id", length = 64)
    private String userId;
    
    @Column(name = "container_id", length = 64)
    private String containerId;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "waste_type", nullable = false)
    private WasteType wasteType;
    
    @DecimalMin("0.0")
    @Column(name = "weight", nullable = false)
    private Double weight;
    
    @Column(name = "raw_weight_data", length = 1000)
    private String rawWeightData;
    
    @Column(name = "weighing_time", nullable = false)
    private LocalDateTime weighingTime;
    
    @Column(name = "is_accurate", nullable = false)
    private Boolean isAccurate = true;
    
    @Column(name = "accuracy_score")
    private Double accuracyScore;
    
    @Column(name = "temperature")
    private Double temperature;
    
    @Column(name = "humidity")
    private Double humidity;

    // Constructors
    public WeighingRecord() {
        this.weighingTime = LocalDateTime.now();
    }

    public WeighingRecord(String deviceId, WasteType wasteType, Double weight) {
        this();
        this.deviceId = deviceId;
        this.wasteType = wasteType;
        this.weight = weight;
    }

    // Getters and Setters
    public String getRecordId() { return recordId; }
    public void setRecordId(String recordId) { this.recordId = recordId; }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getContainerId() { return containerId; }
    public void setContainerId(String containerId) { this.containerId = containerId; }

    public WasteType getWasteType() { return wasteType; }
    public void setWasteType(WasteType wasteType) { this.wasteType = wasteType; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public String getRawWeightData() { return rawWeightData; }
    public void setRawWeightData(String rawWeightData) { this.rawWeightData = rawWeightData; }

    public LocalDateTime getWeighingTime() { return weighingTime; }
    public void setWeighingTime(LocalDateTime weighingTime) { this.weighingTime = weighingTime; }

    public Boolean getIsAccurate() { return isAccurate; }
    public void setIsAccurate(Boolean isAccurate) { this.isAccurate = isAccurate; }

    public Double getAccuracyScore() { return accuracyScore; }
    public void setAccuracyScore(Double accuracyScore) { this.accuracyScore = accuracyScore; }

    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }

    public Double getHumidity() { return humidity; }
    public void setHumidity(Double humidity) { this.humidity = humidity; }
}