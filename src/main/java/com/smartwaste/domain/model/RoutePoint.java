package com.smartwaste.domain.model;

/**
 * 路线点
 * Route Point
 */
public class RoutePoint {
    
    private String pointId;
    private String containerId;
    private Double latitude;
    private Double longitude;
    private String address;
    private Integer sequence;
    private Double distanceFromPrevious;
    private Double estimatedTimeFromPrevious;
    private String pointType; // START, CONTAINER, END

    // Constructors
    public RoutePoint() {}

    public RoutePoint(String pointId, Double latitude, Double longitude) {
        this.pointId = pointId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public RoutePoint(String containerId, Double latitude, Double longitude, String address) {
        this.containerId = containerId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.pointType = "CONTAINER";
    }

    // Getters and Setters
    public String getPointId() { return pointId; }
    public void setPointId(String pointId) { this.pointId = pointId; }

    public String getContainerId() { return containerId; }
    public void setContainerId(String containerId) { this.containerId = containerId; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Integer getSequence() { return sequence; }
    public void setSequence(Integer sequence) { this.sequence = sequence; }

    public Double getDistanceFromPrevious() { return distanceFromPrevious; }
    public void setDistanceFromPrevious(Double distanceFromPrevious) { this.distanceFromPrevious = distanceFromPrevious; }

    public Double getEstimatedTimeFromPrevious() { return estimatedTimeFromPrevious; }
    public void setEstimatedTimeFromPrevious(Double estimatedTimeFromPrevious) { this.estimatedTimeFromPrevious = estimatedTimeFromPrevious; }

    public String getPointType() { return pointType; }
    public void setPointType(String pointType) { this.pointType = pointType; }
}