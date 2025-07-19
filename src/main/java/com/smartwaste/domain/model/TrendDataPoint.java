package com.smartwaste.domain.model;

import java.time.LocalDateTime;

public class TrendDataPoint {
    private LocalDateTime timestamp;
    private String metric;
    private Double value;
    private String trend; // "increasing", "decreasing", "stable"
    private Double changeRate;

    // Constructors
    public TrendDataPoint() {}

    public TrendDataPoint(LocalDateTime timestamp, String metric, Double value) {
        this.timestamp = timestamp;
        this.metric = metric;
        this.value = value;
    }

    public TrendDataPoint(LocalDateTime timestamp, String metric, Double value, 
                         String trend, Double changeRate) {
        this.timestamp = timestamp;
        this.metric = metric;
        this.value = value;
        this.trend = trend;
        this.changeRate = changeRate;
    }

    // Getters and Setters
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getMetric() { return metric; }
    public void setMetric(String metric) { this.metric = metric; }

    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }

    public String getTrend() { return trend; }
    public void setTrend(String trend) { this.trend = trend; }

    public Double getChangeRate() { return changeRate; }
    public void setChangeRate(Double changeRate) { this.changeRate = changeRate; }
}