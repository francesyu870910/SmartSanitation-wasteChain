package com.smartwaste.domain.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据采集结果
 * Data Collection Result
 */
public class DataCollectionResult {
    private String facilityId;
    private String equipmentId;
    private boolean success;
    private String message;
    private LocalDateTime collectionTime;
    private int dataPointsCollected;
    private List<String> errors;
    private DataQuality dataQuality;
    
    // 构造函数
    public DataCollectionResult() {
        this.collectionTime = LocalDateTime.now();
    }
    
    public DataCollectionResult(String facilityId, String equipmentId, boolean success) {
        this();
        this.facilityId = facilityId;
        this.equipmentId = equipmentId;
        this.success = success;
    }
    
    // Getters and Setters
    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }
    
    public String getEquipmentId() { return equipmentId; }
    public void setEquipmentId(String equipmentId) { this.equipmentId = equipmentId; }
    
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public LocalDateTime getCollectionTime() { return collectionTime; }
    public void setCollectionTime(LocalDateTime collectionTime) { this.collectionTime = collectionTime; }
    
    public int getDataPointsCollected() { return dataPointsCollected; }
    public void setDataPointsCollected(int dataPointsCollected) { this.dataPointsCollected = dataPointsCollected; }
    
    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }
    
    public DataQuality getDataQuality() { return dataQuality; }
    public void setDataQuality(DataQuality dataQuality) { this.dataQuality = dataQuality; }
    
    /**
     * 数据质量评估
     */
    public static class DataQuality {
        private double completeness; // 完整性 0-1
        private double accuracy; // 准确性 0-1
        private double timeliness; // 及时性 0-1
        private double consistency; // 一致性 0-1
        private double overallScore; // 总体评分 0-1
        
        // Getters and Setters
        public double getCompleteness() { return completeness; }
        public void setCompleteness(double completeness) { this.completeness = completeness; }
        
        public double getAccuracy() { return accuracy; }
        public void setAccuracy(double accuracy) { this.accuracy = accuracy; }
        
        public double getTimeliness() { return timeliness; }
        public void setTimeliness(double timeliness) { this.timeliness = timeliness; }
        
        public double getConsistency() { return consistency; }
        public void setConsistency(double consistency) { this.consistency = consistency; }
        
        public double getOverallScore() { return overallScore; }
        public void setOverallScore(double overallScore) { this.overallScore = overallScore; }
    }
}