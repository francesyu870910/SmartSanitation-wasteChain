package com.smartwaste.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 数据质量报告模型
 */
public class DataQualityReport {
    private String reportId;
    private String dataSource;
    private LocalDateTime assessmentTime;
    private LocalDateTime periodStart;
    private LocalDateTime periodEnd;
    
    // 数据质量指标
    private double completenessScore; // 完整性得分 (0-100)
    private double accuracyScore; // 准确性得分 (0-100)
    private double consistencyScore; // 一致性得分 (0-100)
    private double timelinessScore; // 及时性得分 (0-100)
    private double validityScore; // 有效性得分 (0-100)
    private double overallScore; // 总体得分 (0-100)
    
    // 详细统计
    private long totalRecords; // 总记录数
    private long validRecords; // 有效记录数
    private long invalidRecords; // 无效记录数
    private long missingRecords; // 缺失记录数
    private long duplicateRecords; // 重复记录数
    private long outlierRecords; // 异常记录数
    
    // 字段级质量统计
    private Map<String, FieldQualityMetrics> fieldMetrics;
    
    // 异常列表
    private List<DataAnomaly> anomalies;
    
    // 改进建议
    private List<String> recommendations;
    
    // 趋势数据
    private List<QualityTrendPoint> qualityTrend;

    // Constructors
    public DataQualityReport() {}

    public DataQualityReport(String dataSource) {
        this.dataSource = dataSource;
        this.assessmentTime = LocalDateTime.now();
    }

    // Getters and Setters
    public String getReportId() { return reportId; }
    public void setReportId(String reportId) { this.reportId = reportId; }

    public String getDataSource() { return dataSource; }
    public void setDataSource(String dataSource) { this.dataSource = dataSource; }

    public LocalDateTime getAssessmentTime() { return assessmentTime; }
    public void setAssessmentTime(LocalDateTime assessmentTime) { this.assessmentTime = assessmentTime; }

    public LocalDateTime getPeriodStart() { return periodStart; }
    public void setPeriodStart(LocalDateTime periodStart) { this.periodStart = periodStart; }

    public LocalDateTime getPeriodEnd() { return periodEnd; }
    public void setPeriodEnd(LocalDateTime periodEnd) { this.periodEnd = periodEnd; }

    public double getCompletenessScore() { return completenessScore; }
    public void setCompletenessScore(double completenessScore) { this.completenessScore = completenessScore; }

    public double getAccuracyScore() { return accuracyScore; }
    public void setAccuracyScore(double accuracyScore) { this.accuracyScore = accuracyScore; }

    public double getConsistencyScore() { return consistencyScore; }
    public void setConsistencyScore(double consistencyScore) { this.consistencyScore = consistencyScore; }

    public double getTimelinessScore() { return timelinessScore; }
    public void setTimelinessScore(double timelinessScore) { this.timelinessScore = timelinessScore; }

    public double getValidityScore() { return validityScore; }
    public void setValidityScore(double validityScore) { this.validityScore = validityScore; }

    public double getOverallScore() { return overallScore; }
    public void setOverallScore(double overallScore) { this.overallScore = overallScore; }

    public long getTotalRecords() { return totalRecords; }
    public void setTotalRecords(long totalRecords) { this.totalRecords = totalRecords; }

    public long getValidRecords() { return validRecords; }
    public void setValidRecords(long validRecords) { this.validRecords = validRecords; }

    public long getInvalidRecords() { return invalidRecords; }
    public void setInvalidRecords(long invalidRecords) { this.invalidRecords = invalidRecords; }

    public long getMissingRecords() { return missingRecords; }
    public void setMissingRecords(long missingRecords) { this.missingRecords = missingRecords; }

    public long getDuplicateRecords() { return duplicateRecords; }
    public void setDuplicateRecords(long duplicateRecords) { this.duplicateRecords = duplicateRecords; }

    public long getOutlierRecords() { return outlierRecords; }
    public void setOutlierRecords(long outlierRecords) { this.outlierRecords = outlierRecords; }

    public Map<String, FieldQualityMetrics> getFieldMetrics() { return fieldMetrics; }
    public void setFieldMetrics(Map<String, FieldQualityMetrics> fieldMetrics) { this.fieldMetrics = fieldMetrics; }

    public List<DataAnomaly> getAnomalies() { return anomalies; }
    public void setAnomalies(List<DataAnomaly> anomalies) { this.anomalies = anomalies; }

    public List<String> getRecommendations() { return recommendations; }
    public void setRecommendations(List<String> recommendations) { this.recommendations = recommendations; }

    public List<QualityTrendPoint> getQualityTrend() { return qualityTrend; }
    public void setQualityTrend(List<QualityTrendPoint> qualityTrend) { this.qualityTrend = qualityTrend; }

    /**
     * 字段质量指标内部类
     */
    public static class FieldQualityMetrics {
        private String fieldName;
        private double completeness; // 完整性
        private double accuracy; // 准确性
        private double validity; // 有效性
        private long nullCount; // 空值数量
        private long invalidCount; // 无效值数量
        private long totalCount; // 总数量

        // Constructors
        public FieldQualityMetrics() {}

        public FieldQualityMetrics(String fieldName) {
            this.fieldName = fieldName;
        }

        // Getters and Setters
        public String getFieldName() { return fieldName; }
        public void setFieldName(String fieldName) { this.fieldName = fieldName; }

        public double getCompleteness() { return completeness; }
        public void setCompleteness(double completeness) { this.completeness = completeness; }

        public double getAccuracy() { return accuracy; }
        public void setAccuracy(double accuracy) { this.accuracy = accuracy; }

        public double getValidity() { return validity; }
        public void setValidity(double validity) { this.validity = validity; }

        public long getNullCount() { return nullCount; }
        public void setNullCount(long nullCount) { this.nullCount = nullCount; }

        public long getInvalidCount() { return invalidCount; }
        public void setInvalidCount(long invalidCount) { this.invalidCount = invalidCount; }

        public long getTotalCount() { return totalCount; }
        public void setTotalCount(long totalCount) { this.totalCount = totalCount; }
    }

    /**
     * 质量趋势点内部类
     */
    public static class QualityTrendPoint {
        private LocalDateTime timestamp;
        private double overallScore;
        private double completenessScore;
        private double accuracyScore;
        private double consistencyScore;

        // Constructors
        public QualityTrendPoint() {}

        public QualityTrendPoint(LocalDateTime timestamp, double overallScore) {
            this.timestamp = timestamp;
            this.overallScore = overallScore;
        }

        // Getters and Setters
        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

        public double getOverallScore() { return overallScore; }
        public void setOverallScore(double overallScore) { this.overallScore = overallScore; }

        public double getCompletenessScore() { return completenessScore; }
        public void setCompletenessScore(double completenessScore) { this.completenessScore = completenessScore; }

        public double getAccuracyScore() { return accuracyScore; }
        public void setAccuracyScore(double accuracyScore) { this.accuracyScore = accuracyScore; }

        public double getConsistencyScore() { return consistencyScore; }
        public void setConsistencyScore(double consistencyScore) { this.consistencyScore = consistencyScore; }
    }
}