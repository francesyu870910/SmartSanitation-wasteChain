package com.smartwaste.domain.model;

import com.smartwaste.domain.service.EnvironmentalMonitoringService.IndicatorType;
import com.smartwaste.domain.service.EnvironmentalMonitoringService.AnalysisPeriod;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 环保趋势分析
 * Environmental Trend Analysis
 */
public class EnvironmentalTrendAnalysis {
    private String facilityId;
    private IndicatorType indicatorType;
    private AnalysisPeriod period;
    private LocalDateTime analysisTime;
    private List<TrendDataPoint> trendData;
    private double averageValue;
    private double maxValue;
    private double minValue;
    private String trendDirection;
    private double changeRate;
    private String summary;
    
    // Getters and Setters
    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }
    
    public IndicatorType getIndicatorType() { return indicatorType; }
    public void setIndicatorType(IndicatorType indicatorType) { this.indicatorType = indicatorType; }
    
    public AnalysisPeriod getPeriod() { return period; }
    public void setPeriod(AnalysisPeriod period) { this.period = period; }
    
    public LocalDateTime getAnalysisTime() { return analysisTime; }
    public void setAnalysisTime(LocalDateTime analysisTime) { this.analysisTime = analysisTime; }
    
    public List<TrendDataPoint> getTrendData() { return trendData; }
    public void setTrendData(List<TrendDataPoint> trendData) { this.trendData = trendData; }
    
    public double getAverageValue() { return averageValue; }
    public void setAverageValue(double averageValue) { this.averageValue = averageValue; }
    
    public double getMaxValue() { return maxValue; }
    public void setMaxValue(double maxValue) { this.maxValue = maxValue; }
    
    public double getMinValue() { return minValue; }
    public void setMinValue(double minValue) { this.minValue = minValue; }
    
    public String getTrendDirection() { return trendDirection; }
    public void setTrendDirection(String trendDirection) { this.trendDirection = trendDirection; }
    
    public double getChangeRate() { return changeRate; }
    public void setChangeRate(double changeRate) { this.changeRate = changeRate; }
    
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
}

