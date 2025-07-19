package com.smartwaste.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class EnvironmentalComplianceReport {
    private String facilityId;
    private LocalDateTime reportDate;
    private boolean compliant;
    private List<String> violations;
    private Double overallScore;
    private String recommendations;
    private LocalDateTime generatedAt;

    // Constructors
    public EnvironmentalComplianceReport() {
        this.generatedAt = LocalDateTime.now();
    }

    public EnvironmentalComplianceReport(String facilityId, boolean compliant, 
                                       List<String> violations, Double overallScore) {
        this();
        this.facilityId = facilityId;
        this.reportDate = LocalDateTime.now();
        this.compliant = compliant;
        this.violations = violations;
        this.overallScore = overallScore;
    }

    // Getters and Setters
    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }

    public LocalDateTime getReportDate() { return reportDate; }
    public void setReportDate(LocalDateTime reportDate) { this.reportDate = reportDate; }

    public boolean isCompliant() { return compliant; }
    public void setCompliant(boolean compliant) { this.compliant = compliant; }

    public List<String> getViolations() { return violations; }
    public void setViolations(List<String> violations) { this.violations = violations; }

    public Double getOverallScore() { return overallScore; }
    public void setOverallScore(Double overallScore) { this.overallScore = overallScore; }

    public String getRecommendations() { return recommendations; }
    public void setRecommendations(String recommendations) { this.recommendations = recommendations; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}