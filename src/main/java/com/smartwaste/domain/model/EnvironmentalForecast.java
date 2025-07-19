package com.smartwaste.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class EnvironmentalForecast {
    private String facilityId;
    private LocalDateTime forecastDate;
    private List<ForecastDataPoint> predictions;
    private String methodology;
    private Double confidenceLevel;
    private LocalDateTime generatedAt;

    // Constructors
    public EnvironmentalForecast() {
        this.generatedAt = LocalDateTime.now();
    }

    public EnvironmentalForecast(String facilityId, List<ForecastDataPoint> predictions, 
                               String methodology, Double confidenceLevel) {
        this();
        this.facilityId = facilityId;
        this.forecastDate = LocalDateTime.now();
        this.predictions = predictions;
        this.methodology = methodology;
        this.confidenceLevel = confidenceLevel;
    }

    // Getters and Setters
    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }

    public LocalDateTime getForecastDate() { return forecastDate; }
    public void setForecastDate(LocalDateTime forecastDate) { this.forecastDate = forecastDate; }

    public List<ForecastDataPoint> getPredictions() { return predictions; }
    public void setPredictions(List<ForecastDataPoint> predictions) { this.predictions = predictions; }

    public String getMethodology() { return methodology; }
    public void setMethodology(String methodology) { this.methodology = methodology; }

    public Double getConfidenceLevel() { return confidenceLevel; }
    public void setConfidenceLevel(Double confidenceLevel) { this.confidenceLevel = confidenceLevel; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }

    // Inner class for forecast data points
    public static class ForecastDataPoint {
        private LocalDateTime timestamp;
        private String metric;
        private Double predictedValue;
        private Double uncertainty;

        public ForecastDataPoint() {}

        public ForecastDataPoint(LocalDateTime timestamp, String metric, 
                               Double predictedValue, Double uncertainty) {
            this.timestamp = timestamp;
            this.metric = metric;
            this.predictedValue = predictedValue;
            this.uncertainty = uncertainty;
        }

        // Getters and Setters
        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

        public String getMetric() { return metric; }
        public void setMetric(String metric) { this.metric = metric; }

        public Double getPredictedValue() { return predictedValue; }
        public void setPredictedValue(Double predictedValue) { this.predictedValue = predictedValue; }

        public Double getUncertainty() { return uncertainty; }
        public void setUncertainty(Double uncertainty) { this.uncertainty = uncertainty; }
    }
}