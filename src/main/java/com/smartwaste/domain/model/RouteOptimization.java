package com.smartwaste.domain.model;

import java.util.List;

/**
 * 路线优化结果
 * Route Optimization Result
 */
public class RouteOptimization {
    
    private String vehicleId;
    private List<String> containerIds;
    private List<RoutePoint> optimizedRoute;
    private Double totalDistance;
    private Double estimatedTime;
    private String algorithm;
    private Double optimizationScore;
    private String notes;

    // Constructors
    public RouteOptimization() {}

    public RouteOptimization(String vehicleId, List<String> containerIds) {
        this.vehicleId = vehicleId;
        this.containerIds = containerIds;
    }

    // Getters and Setters
    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public List<String> getContainerIds() { return containerIds; }
    public void setContainerIds(List<String> containerIds) { this.containerIds = containerIds; }

    public List<RoutePoint> getOptimizedRoute() { return optimizedRoute; }
    public void setOptimizedRoute(List<RoutePoint> optimizedRoute) { this.optimizedRoute = optimizedRoute; }

    public Double getTotalDistance() { return totalDistance; }
    public void setTotalDistance(Double totalDistance) { this.totalDistance = totalDistance; }

    public Double getEstimatedTime() { return estimatedTime; }
    public void setEstimatedTime(Double estimatedTime) { this.estimatedTime = estimatedTime; }

    public String getAlgorithm() { return algorithm; }
    public void setAlgorithm(String algorithm) { this.algorithm = algorithm; }

    public Double getOptimizationScore() { return optimizationScore; }
    public void setOptimizationScore(Double optimizationScore) { this.optimizationScore = optimizationScore; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}