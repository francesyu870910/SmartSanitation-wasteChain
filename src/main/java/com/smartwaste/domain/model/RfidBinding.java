package com.smartwaste.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * RFID绑定记录实体类
 * RFID Binding Record Entity
 */
@Entity
@Table(name = "rfid_bindings")
public class RfidBinding {
    
    @Id
    @Column(name = "binding_id", length = 64)
    private String bindingId;
    
    @NotBlank
    @Column(name = "container_id", length = 64, nullable = false)
    private String containerId;
    
    @NotBlank
    @Column(name = "vehicle_id", length = 64, nullable = false)
    private String vehicleId;
    
    @NotBlank
    @Column(name = "rfid_tag", length = 64, nullable = false)
    private String rfidTag;
    
    @Column(name = "binding_time", nullable = false)
    private LocalDateTime bindingTime;
    
    @Column(name = "unbinding_time")
    private LocalDateTime unbindingTime;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
    
    @Column(name = "operator_id", length = 64)
    private String operatorId;
    
    @Column(name = "notes", length = 500)
    private String notes;

    // Constructors
    public RfidBinding() {
        this.bindingTime = LocalDateTime.now();
    }

    public RfidBinding(String containerId, String vehicleId, String rfidTag) {
        this();
        this.containerId = containerId;
        this.vehicleId = vehicleId;
        this.rfidTag = rfidTag;
    }

    // Getters and Setters
    public String getBindingId() { return bindingId; }
    public void setBindingId(String bindingId) { this.bindingId = bindingId; }

    public String getContainerId() { return containerId; }
    public void setContainerId(String containerId) { this.containerId = containerId; }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public String getRfidTag() { return rfidTag; }
    public void setRfidTag(String rfidTag) { this.rfidTag = rfidTag; }

    public LocalDateTime getBindingTime() { return bindingTime; }
    public void setBindingTime(LocalDateTime bindingTime) { this.bindingTime = bindingTime; }

    public LocalDateTime getUnbindingTime() { return unbindingTime; }
    public void setUnbindingTime(LocalDateTime unbindingTime) { this.unbindingTime = unbindingTime; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public String getOperatorId() { return operatorId; }
    public void setOperatorId(String operatorId) { this.operatorId = operatorId; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}