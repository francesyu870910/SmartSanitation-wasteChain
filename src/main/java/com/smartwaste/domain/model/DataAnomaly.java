package com.smartwaste.domain.model;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 数据异常模型
 */
public class DataAnomaly {
    private String anomalyId;
    private String dataSource; // 数据源
    private String fieldName; // 异常字段名
    private Object originalValue; // 原始值
    private Object expectedValue; // 期望值
    private String anomalyType; // 异常类型：OUTLIER, MISSING, INVALID, DUPLICATE
    private String severity; // 严重程度：LOW, MEDIUM, HIGH, CRITICAL
    private String description; // 异常描述
    private String reasonAnalysis; // 原因分析
    private LocalDateTime detectedAt; // 检测时间
    private String detectionMethod; // 检测方法
    private boolean isResolved; // 是否已解决
    private String resolution; // 解决方案
    private LocalDateTime resolvedAt; // 解决时间
    private Map<String, Object> metadata; // 元数据

    // Constructors
    public DataAnomaly() {}

    public DataAnomaly(String dataSource, String fieldName, Object originalValue, String anomalyType) {
        this.dataSource = dataSource;
        this.fieldName = fieldName;
        this.originalValue = originalValue;
        this.anomalyType = anomalyType;
        this.detectedAt = LocalDateTime.now();
        this.isResolved = false;
    }

    // Getters and Setters
    public String getAnomalyId() { return anomalyId; }
    public void setAnomalyId(String anomalyId) { this.anomalyId = anomalyId; }

    public String getDataSource() { return dataSource; }
    public void setDataSource(String dataSource) { this.dataSource = dataSource; }

    public String getFieldName() { return fieldName; }
    public void setFieldName(String fieldName) { this.fieldName = fieldName; }

    public Object getOriginalValue() { return originalValue; }
    public void setOriginalValue(Object originalValue) { this.originalValue = originalValue; }

    public Object getExpectedValue() { return expectedValue; }
    public void setExpectedValue(Object expectedValue) { this.expectedValue = expectedValue; }

    public String getAnomalyType() { return anomalyType; }
    public void setAnomalyType(String anomalyType) { this.anomalyType = anomalyType; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getReasonAnalysis() { return reasonAnalysis; }
    public void setReasonAnalysis(String reasonAnalysis) { this.reasonAnalysis = reasonAnalysis; }

    public LocalDateTime getDetectedAt() { return detectedAt; }
    public void setDetectedAt(LocalDateTime detectedAt) { this.detectedAt = detectedAt; }

    public String getDetectionMethod() { return detectionMethod; }
    public void setDetectionMethod(String detectionMethod) { this.detectionMethod = detectionMethod; }

    public boolean isResolved() { return isResolved; }
    public void setResolved(boolean resolved) { isResolved = resolved; }

    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }

    public LocalDateTime getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDateTime resolvedAt) { this.resolvedAt = resolvedAt; }

    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }
}