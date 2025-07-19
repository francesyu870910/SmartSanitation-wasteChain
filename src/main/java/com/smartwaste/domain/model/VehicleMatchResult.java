package com.smartwaste.domain.model;

import java.time.LocalDateTime;

/**
 * 车辆匹配结果
 * Vehicle Match Result
 */
public class VehicleMatchResult {
    private String vehicleId;
    private Vehicle vehicle;
    private Double matchScore;
    private String matchReason;
    private boolean available;
    private LocalDateTime evaluatedAt;

    // 构造函数
    public VehicleMatchResult() {
        this.evaluatedAt = LocalDateTime.now();
    }

    public VehicleMatchResult(Vehicle vehicle, Double matchScore, String matchReason) {
        this();
        this.vehicle = vehicle;
        this.vehicleId = vehicle.getVehicleId();
        this.matchScore = matchScore;
        this.matchReason = matchReason;
        this.available = true;
    }

    // Getters and Setters
    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { 
        this.vehicle = vehicle;
        if (vehicle != null) {
            this.vehicleId = vehicle.getVehicleId();
        }
    }

    public Double getMatchScore() { return matchScore; }
    public void setMatchScore(Double matchScore) { this.matchScore = matchScore; }

    public String getMatchReason() { return matchReason; }
    public void setMatchReason(String matchReason) { this.matchReason = matchReason; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public LocalDateTime getEvaluatedAt() { return evaluatedAt; }
    public void setEvaluatedAt(LocalDateTime evaluatedAt) { this.evaluatedAt = evaluatedAt; }
}