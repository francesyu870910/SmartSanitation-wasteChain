package com.smartwaste.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 环保数据实体类
 * Environmental Data Entity
 */
@Entity
@Table(name = "environmental_data")
public class EnvironmentalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "facility_id")
    private String facilityId;
    
    @Column(name = "air_quality_index")
    private Double airQualityIndex;
    
    @Column(name = "water_quality_ph")
    private Double waterQualityPh;
    
    @Column(name = "noise_level")
    private Double noiseLevel;
    
    @Column(name = "temperature")
    private Double temperature;
    
    @Column(name = "humidity")
    private Double humidity;
    
    @Column(name = "recorded_at")
    private LocalDateTime recordedAt;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "data_quality")
    private Double dataQuality;
    
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    // 构造函数
    public EnvironmentalData() {
        this.createdAt = LocalDateTime.now();
    }

    public EnvironmentalData(String facilityId, Double airQualityIndex, Double waterQualityPh, 
                           Double noiseLevel, Double temperature, Double humidity) {
        this();
        this.facilityId = facilityId;
        this.airQualityIndex = airQualityIndex;
        this.waterQualityPh = waterQualityPh;
        this.noiseLevel = noiseLevel;
        this.temperature = temperature;
        this.humidity = humidity;
        this.recordedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }

    public Double getAirQualityIndex() { return airQualityIndex; }
    public void setAirQualityIndex(Double airQualityIndex) { this.airQualityIndex = airQualityIndex; }

    public Double getWaterQualityPh() { return waterQualityPh; }
    public void setWaterQualityPh(Double waterQualityPh) { this.waterQualityPh = waterQualityPh; }

    public Double getNoiseLevel() { return noiseLevel; }
    public void setNoiseLevel(Double noiseLevel) { this.noiseLevel = noiseLevel; }

    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }

    public Double getHumidity() { return humidity; }
    public void setHumidity(Double humidity) { this.humidity = humidity; }

    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Double getDataQuality() { return dataQuality; }
    public void setDataQuality(Double dataQuality) { this.dataQuality = dataQuality; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}