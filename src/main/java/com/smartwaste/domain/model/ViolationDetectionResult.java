package com.smartwaste.domain.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 违规检测结果
 */
public class ViolationDetectionResult {
    
    private boolean violationDetected;
    private String violationType;
    private String violationDescription;
    private double confidence;
    private String expectedWasteType;
    private String actualWasteType;
    private List<String> evidencePhotos;
    private String evidenceVideo;
    private LocalDateTime detectionTime;
    private String deviceId;
    private String location;
    private Double weight;
    private String severity;
    
    // 构造函数
    public ViolationDetectionResult() {
        this.detectionTime = LocalDateTime.now();
    }
    
    public ViolationDetectionResult(boolean violationDetected, String violationType, double confidence) {
        this();
        this.violationDetected = violationDetected;
        this.violationType = violationType;
        this.confidence = confidence;
    }
    
    // Getters and Setters
    public boolean isViolationDetected() {
        return violationDetected;
    }
    
    public void setViolationDetected(boolean violationDetected) {
        this.violationDetected = violationDetected;
    }
    
    public String getViolationType() {
        return violationType;
    }
    
    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }
    
    public String getViolationDescription() {
        return violationDescription;
    }
    
    public void setViolationDescription(String violationDescription) {
        this.violationDescription = violationDescription;
    }
    
    public double getConfidence() {
        return confidence;
    }
    
    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
    
    public String getExpectedWasteType() {
        return expectedWasteType;
    }
    
    public void setExpectedWasteType(String expectedWasteType) {
        this.expectedWasteType = expectedWasteType;
    }
    
    public String getActualWasteType() {
        return actualWasteType;
    }
    
    public void setActualWasteType(String actualWasteType) {
        this.actualWasteType = actualWasteType;
    }
    
    public List<String> getEvidencePhotos() {
        return evidencePhotos;
    }
    
    public void setEvidencePhotos(List<String> evidencePhotos) {
        this.evidencePhotos = evidencePhotos;
    }
    
    public String getEvidenceVideo() {
        return evidenceVideo;
    }
    
    public void setEvidenceVideo(String evidenceVideo) {
        this.evidenceVideo = evidenceVideo;
    }
    
    public LocalDateTime getDetectionTime() {
        return detectionTime;
    }
    
    public void setDetectionTime(LocalDateTime detectionTime) {
        this.detectionTime = detectionTime;
    }
    
    public String getDeviceId() {
        return deviceId;
    }
    
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public Double getWeight() {
        return weight;
    }
    
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    
    public String getSeverity() {
        return severity;
    }
    
    public void setSeverity(String severity) {
        this.severity = severity;
    }
}