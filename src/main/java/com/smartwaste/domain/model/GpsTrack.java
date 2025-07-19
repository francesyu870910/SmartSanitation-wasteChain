package com.smartwaste.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * GPS轨迹记录实体类
 * GPS Track Record Entity
 */
@Entity
@Table(name = "gps_tracks")
public class GpsTrack {
    
    @Id
    @Column(name = "track_id", length = 64)
    private String trackId;
    
    @NotBlank
    @Column(name = "vehicle_id", length = 64, nullable = false)
    private String vehicleId;
    
    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    @Column(name = "latitude", nullable = false)
    private Double latitude;
    
    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    @Column(name = "longitude", nullable = false)
    private Double longitude;
    
    @Column(name = "altitude")
    private Double altitude;
    
    @Column(name = "speed")
    private Double speed;
    
    @Column(name = "direction")
    private Double direction;
    
    @Column(name = "accuracy")
    private Double accuracy;
    
    @Column(name = "recorded_time", nullable = false)
    private LocalDateTime recordedTime;
    
    @Column(name = "address", length = 500)
    private String address;
    
    @Column(name = "is_valid", nullable = false)
    private Boolean isValid = true;

    // Constructors
    public GpsTrack() {
        this.recordedTime = LocalDateTime.now();
    }

    public GpsTrack(String vehicleId, Double latitude, Double longitude) {
        this();
        this.vehicleId = vehicleId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters
    public String getTrackId() { return trackId; }
    public void setTrackId(String trackId) { this.trackId = trackId; }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Double getAltitude() { return altitude; }
    public void setAltitude(Double altitude) { this.altitude = altitude; }

    public Double getSpeed() { return speed; }
    public void setSpeed(Double speed) { this.speed = speed; }

    public Double getDirection() { return direction; }
    public void setDirection(Double direction) { this.direction = direction; }

    public Double getAccuracy() { return accuracy; }
    public void setAccuracy(Double accuracy) { this.accuracy = accuracy; }

    public LocalDateTime getRecordedTime() { return recordedTime; }
    public void setRecordedTime(LocalDateTime recordedTime) { this.recordedTime = recordedTime; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Boolean getIsValid() { return isValid; }
    public void setIsValid(Boolean isValid) { this.isValid = isValid; }
}