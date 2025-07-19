package com.smartwaste.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import java.time.LocalDateTime;

/**
 * 垃圾投放记录实体类
 * Waste Disposal Record Entity
 */
@Entity
@Table(name = "waste_disposals")
public class WasteDisposal {
    
    @Id
    @Column(name = "disposal_id", length = 64)
    private String disposalId;
    
    @NotBlank
    @Column(name = "user_id", length = 64, nullable = false)
    private String userId;
    
    @NotBlank
    @Column(name = "container_id", length = 64, nullable = false)
    private String containerId;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "waste_type", nullable = false)
    private WasteType wasteType;
    
    @DecimalMin("0.0")
    @Column(name = "weight", nullable = false)
    private Double weight;
    
    @Column(name = "points_earned")
    private Integer pointsEarned;
    
    @Column(name = "disposal_time", nullable = false)
    private LocalDateTime disposalTime;
    
    @Column(name = "qr_code", length = 128)
    private String qrCode;
    
    @Column(name = "is_correct_classification", nullable = false)
    private Boolean isCorrectClassification = true;

    // Constructors
    public WasteDisposal() {
        this.disposalTime = LocalDateTime.now();
    }

    public WasteDisposal(String userId, String containerId, WasteType wasteType, Double weight) {
        this();
        this.userId = userId;
        this.containerId = containerId;
        this.wasteType = wasteType;
        this.weight = weight;
    }

    // Getters and Setters
    public String getDisposalId() { return disposalId; }
    public void setDisposalId(String disposalId) { this.disposalId = disposalId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getContainerId() { return containerId; }
    public void setContainerId(String containerId) { this.containerId = containerId; }

    public WasteType getWasteType() { return wasteType; }
    public void setWasteType(WasteType wasteType) { this.wasteType = wasteType; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Integer getPointsEarned() { return pointsEarned; }
    public void setPointsEarned(Integer pointsEarned) { this.pointsEarned = pointsEarned; }

    public LocalDateTime getDisposalTime() { return disposalTime; }
    public void setDisposalTime(LocalDateTime disposalTime) { this.disposalTime = disposalTime; }

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }

    public Boolean getIsCorrectClassification() { return isCorrectClassification; }
    public void setIsCorrectClassification(Boolean isCorrectClassification) { this.isCorrectClassification = isCorrectClassification; }
}