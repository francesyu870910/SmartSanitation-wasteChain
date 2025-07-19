package com.smartwaste.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 用户身份识别记录实体类
 * User Identification Record Entity
 */
@Entity
@Table(name = "user_identifications")
public class UserIdentification {
    
    @Id
    @Column(name = "identification_id", length = 64)
    private String identificationId;
    
    @NotBlank
    @Column(name = "user_id", length = 64, nullable = false)
    private String userId;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "identification_method", nullable = false)
    private IdentificationMethod identificationMethod;
    
    @Column(name = "identification_data", length = 1000)
    private String identificationData;
    
    @Column(name = "confidence_score")
    private Double confidenceScore;
    
    @Column(name = "device_id", length = 64)
    private String deviceId;
    
    @Column(name = "location", length = 500)
    private String location;
    
    @Column(name = "identification_time", nullable = false)
    private LocalDateTime identificationTime;
    
    @Column(name = "is_successful", nullable = false)
    private Boolean isSuccessful = true;
    
    @Column(name = "error_message", length = 500)
    private String errorMessage;

    // Constructors
    public UserIdentification() {
        this.identificationTime = LocalDateTime.now();
    }

    public UserIdentification(String userId, IdentificationMethod method, String deviceId) {
        this();
        this.userId = userId;
        this.identificationMethod = method;
        this.deviceId = deviceId;
    }

    // Getters and Setters
    public String getIdentificationId() { return identificationId; }
    public void setIdentificationId(String identificationId) { this.identificationId = identificationId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public IdentificationMethod getIdentificationMethod() { return identificationMethod; }
    public void setIdentificationMethod(IdentificationMethod identificationMethod) { this.identificationMethod = identificationMethod; }

    public String getIdentificationData() { return identificationData; }
    public void setIdentificationData(String identificationData) { this.identificationData = identificationData; }

    public Double getConfidenceScore() { return confidenceScore; }
    public void setConfidenceScore(Double confidenceScore) { this.confidenceScore = confidenceScore; }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDateTime getIdentificationTime() { return identificationTime; }
    public void setIdentificationTime(LocalDateTime identificationTime) { this.identificationTime = identificationTime; }

    public Boolean getIsSuccessful() { return isSuccessful; }
    public void setIsSuccessful(Boolean isSuccessful) { this.isSuccessful = isSuccessful; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}