package com.smartwaste.domain.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 环保数据记录
 * Environmental Data Record
 */
public class EnvironmentalDataRecord {
    private String recordId;
    private String facilityId;
    private LocalDateTime timestamp;
    private EnvironmentalData environmentalData;
    private double dataQuality;
    private boolean compliant;
    private List<String> violations;
    private List<String> qualityIssues;
    private String verificationStatus;
    
    // 构造函数
    public EnvironmentalDataRecord() {
        this.recordId = generateRecordId();
        this.timestamp = LocalDateTime.now();
    }
    
    private String generateRecordId() {
        return "ENV-REC-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
    }
    
    // Getters and Setters
    public String getRecordId() { return recordId; }
    public void setRecordId(String recordId) { this.recordId = recordId; }
    
    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    
    public EnvironmentalData getEnvironmentalData() { return environmentalData; }
    public void setEnvironmentalData(EnvironmentalData environmentalData) { this.environmentalData = environmentalData; }
    
    public double getDataQuality() { return dataQuality; }
    public void setDataQuality(double dataQuality) { this.dataQuality = dataQuality; }
    
    public boolean isCompliant() { return compliant; }
    public void setCompliant(boolean compliant) { this.compliant = compliant; }
    
    public List<String> getViolations() { return violations; }
    public void setViolations(List<String> violations) { this.violations = violations; }
    
    public List<String> getQualityIssues() { return qualityIssues; }
    public void setQualityIssues(List<String> qualityIssues) { this.qualityIssues = qualityIssues; }
    
    public String getVerificationStatus() { return verificationStatus; }
    public void setVerificationStatus(String verificationStatus) { this.verificationStatus = verificationStatus; }
}