package com.smartwaste.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 垃圾桶实体类
 * Container Entity
 */
@Entity
@Table(name = "containers")
public class Container {
    
    @Id
    @Column(name = "container_id", length = 64)
    private String containerId;
    
    @NotBlank
    @Column(name = "location", length = 500, nullable = false)
    private String location;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "waste_type", nullable = false)
    private WasteType wasteType;
    
    @DecimalMin("0.0")
    @Column(name = "capacity", nullable = false)
    private Double capacity;
    
    @DecimalMin("0.0")
    @DecimalMax("1.0")
    @Column(name = "current_fullness", nullable = false)
    private Double currentFullness = 0.0;
    
    @Column(name = "rfid_tag", length = 64, unique = true)
    private String rfidTag;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    // Constructors
    public Container() {}

    public Container(String containerId, String location, WasteType wasteType, Double capacity) {
        this.containerId = containerId;
        this.location = location;
        this.wasteType = wasteType;
        this.capacity = capacity;
    }

    // Getters and Setters
    public String getContainerId() { return containerId; }
    public void setContainerId(String containerId) { this.containerId = containerId; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public WasteType getWasteType() { return wasteType; }
    public void setWasteType(WasteType wasteType) { this.wasteType = wasteType; }

    public Double getCapacity() { return capacity; }
    public void setCapacity(Double capacity) { this.capacity = capacity; }

    public Double getCurrentFullness() { return currentFullness; }
    public void setCurrentFullness(Double currentFullness) { this.currentFullness = currentFullness; }

    public String getRfidTag() { return rfidTag; }
    public void setRfidTag(String rfidTag) { this.rfidTag = rfidTag; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}