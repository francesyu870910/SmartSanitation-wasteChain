package com.smartwaste.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 收运车辆实体类
 * Vehicle Entity
 */
@Entity
@Table(name = "vehicles")
public class Vehicle {
    
    @Id
    @Column(name = "vehicle_id", length = 64)
    private String vehicleId;
    
    @NotBlank
    @Column(name = "license_plate", length = 20, nullable = false, unique = true)
    private String licensePlate;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private WasteType vehicleType;
    
    @Column(name = "capacity", nullable = false)
    private Double capacity;
    
    @Column(name = "driver_id", length = 64)
    private String driverId;
    
    @Column(name = "current_location", length = 200)
    private String currentLocation;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private VehicleStatus status = VehicleStatus.IDLE;

    // Constructors
    public Vehicle() {}

    public Vehicle(String vehicleId, String licensePlate, WasteType vehicleType, Double capacity) {
        this.vehicleId = vehicleId;
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.capacity = capacity;
    }

    // Getters and Setters
    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public WasteType getVehicleType() { return vehicleType; }
    public void setVehicleType(WasteType vehicleType) { this.vehicleType = vehicleType; }

    public Double getCapacity() { return capacity; }
    public void setCapacity(Double capacity) { this.capacity = capacity; }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public String getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(String currentLocation) { this.currentLocation = currentLocation; }

    public VehicleStatus getStatus() { return status; }
    public void setStatus(VehicleStatus status) { this.status = status; }
}