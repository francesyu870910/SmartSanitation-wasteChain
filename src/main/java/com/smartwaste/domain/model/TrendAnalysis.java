package com.smartwaste.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 趋势分析数据模型
 */
public class TrendAnalysis {
    private String analysisId;
    private String analysisType;
    private LocalDateTime analysisTime;
    private LocalDateTime periodStart;
    private LocalDateTime periodEnd;
    private String metric;
    private String trend; // INCREASING, DECREASING, STABLE
    private Double changeRate;
    private List<TrendDataPoint> dataPoints;
    private Map<String, Object> insights;
    private String summary;

    public TrendAnalysis() {}

    public TrendAnalysis(String analysisType, String metric) {
        this.analysisType = analysisType;
        this.metric = metric;
        this.analysisTime = LocalDateTime.now();
    }

    // Getters and Setters
    public String getAnalysisId() { return analysisId; }
    public void setAnalysisId(String analysisId) { this.analysisId = analysisId; }

    public String getAnalysisType() { return analysisType; }
    public void setAnalysisType(String analysisType) { this.analysisType = analysisType; }

    public LocalDateTime getAnalysisTime() { return analysisTime; }
    public void setAnalysisTime(LocalDateTime analysisTime) { this.analysisTime = analysisTime; }

    public LocalDateTime getPeriodStart() { return periodStart; }
    public void setPeriodStart(LocalDateTime periodStart) { this.periodStart = periodStart; }

    public LocalDateTime getPeriodEnd() { return periodEnd; }
    public void setPeriodEnd(LocalDateTime periodEnd) { this.periodEnd = periodEnd; }

    public String getMetric() { return metric; }
    public void setMetric(String metric) { this.metric = metric; }

    public String getTrend() { return trend; }
    public void setTrend(String trend) { this.trend = trend; }

    public Double getChangeRate() { return changeRate; }
    public void setChangeRate(Double changeRate) { this.changeRate = changeRate; }

    public List<TrendDataPoint> getDataPoints() { return dataPoints; }
    public void setDataPoints(List<TrendDataPoint> dataPoints) { this.dataPoints = dataPoints; }

    public Map<String, Object> getInsights() { return insights; }
    public void setInsights(Map<String, Object> insights) { this.insights = insights; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
}