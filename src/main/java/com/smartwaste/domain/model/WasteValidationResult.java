package com.smartwaste.domain.model;

import java.util.List;

/**
 * 垃圾分类验证结果
 * Waste Validation Result
 */
public class WasteValidationResult {
    
    private boolean isValid;
    private String vehicleId;
    private WasteType expectedWasteType;
    private List<WasteType> detectedWasteTypes;
    private List<String> violations;
    private double confidenceScore;
    private String validationMethod;
    private String message;

    // Constructors
    public WasteValidationResult() {}

    public WasteValidationResult(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    public WasteValidationResult(String vehicleId, WasteType expectedWasteType) {
        this.vehicleId = vehicleId;
        this.expectedWasteType = expectedWasteType;
    }

    // Getters and Setters
    public boolean isValid() { return isValid; }
    public void setValid(boolean valid) { isValid = valid; }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public WasteType getExpectedWasteType() { return expectedWasteType; }
    public void setExpectedWasteType(WasteType expectedWasteType) { this.expectedWasteType = expectedWasteType; }

    public List<WasteType> getDetectedWasteTypes() { return detectedWasteTypes; }
    public void setDetectedWasteTypes(List<WasteType> detectedWasteTypes) { this.detectedWasteTypes = detectedWasteTypes; }

    public List<String> getViolations() { return violations; }
    public void setViolations(List<String> violations) { this.violations = violations; }

    public double getConfidenceScore() { return confidenceScore; }
    public void setConfidenceScore(double confidenceScore) { this.confidenceScore = confidenceScore; }

    public String getValidationMethod() { return validationMethod; }
    public void setValidationMethod(String validationMethod) { this.validationMethod = validationMethod; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    // Helper methods
    public boolean hasMixedWaste() {
        return detectedWasteTypes != null && detectedWasteTypes.size() > 1;
    }

    public boolean hasWrongWasteType() {
        return detectedWasteTypes != null && !detectedWasteTypes.isEmpty() && 
               expectedWasteType != null && !detectedWasteTypes.contains(expectedWasteType);
    }
}