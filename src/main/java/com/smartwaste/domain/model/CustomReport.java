package com.smartwaste.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 自定义报表模型
 */
public class CustomReport {
    private String reportId;
    private String reportName;
    private String description;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime lastModified;
    private ReportConfig config;
    private List<String> dataFields;
    private List<String> groupByFields;
    private List<FilterCondition> filters;
    private String chartType;
    private Map<String, Object> chartConfig;
    private boolean isPublic;
    private boolean isScheduled;
    private String scheduleExpression;

    public CustomReport() {}

    public CustomReport(String reportName, String createdBy) {
        this.reportName = reportName;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
        this.isPublic = false;
        this.isScheduled = false;
    }

    // 报表配置内部类
    public static class ReportConfig {
        private String title;
        private String subtitle;
        private boolean showHeader;
        private boolean showFooter;
        private String pageSize;
        private String orientation;
        private Map<String, Object> styling;

        // Getters and Setters
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getSubtitle() { return subtitle; }
        public void setSubtitle(String subtitle) { this.subtitle = subtitle; }

        public boolean isShowHeader() { return showHeader; }
        public void setShowHeader(boolean showHeader) { this.showHeader = showHeader; }

        public boolean isShowFooter() { return showFooter; }
        public void setShowFooter(boolean showFooter) { this.showFooter = showFooter; }

        public String getPageSize() { return pageSize; }
        public void setPageSize(String pageSize) { this.pageSize = pageSize; }

        public String getOrientation() { return orientation; }
        public void setOrientation(String orientation) { this.orientation = orientation; }

        public Map<String, Object> getStyling() { return styling; }
        public void setStyling(Map<String, Object> styling) { this.styling = styling; }
    }

    // 过滤条件内部类
    public static class FilterCondition {
        private String field;
        private String operator; // EQUALS, CONTAINS, GREATER_THAN, LESS_THAN, BETWEEN
        private Object value;
        private Object secondValue; // for BETWEEN operator

        public FilterCondition() {}

        public FilterCondition(String field, String operator, Object value) {
            this.field = field;
            this.operator = operator;
            this.value = value;
        }

        // Getters and Setters
        public String getField() { return field; }
        public void setField(String field) { this.field = field; }

        public String getOperator() { return operator; }
        public void setOperator(String operator) { this.operator = operator; }

        public Object getValue() { return value; }
        public void setValue(Object value) { this.value = value; }

        public Object getSecondValue() { return secondValue; }
        public void setSecondValue(Object secondValue) { this.secondValue = secondValue; }
    }

    // Getters and Setters
    public String getReportId() { return reportId; }
    public void setReportId(String reportId) { this.reportId = reportId; }

    public String getReportName() { return reportName; }
    public void setReportName(String reportName) { this.reportName = reportName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getLastModified() { return lastModified; }
    public void setLastModified(LocalDateTime lastModified) { this.lastModified = lastModified; }

    public ReportConfig getConfig() { return config; }
    public void setConfig(ReportConfig config) { this.config = config; }

    public List<String> getDataFields() { return dataFields; }
    public void setDataFields(List<String> dataFields) { this.dataFields = dataFields; }

    public List<String> getGroupByFields() { return groupByFields; }
    public void setGroupByFields(List<String> groupByFields) { this.groupByFields = groupByFields; }

    public List<FilterCondition> getFilters() { return filters; }
    public void setFilters(List<FilterCondition> filters) { this.filters = filters; }

    public String getChartType() { return chartType; }
    public void setChartType(String chartType) { this.chartType = chartType; }

    public Map<String, Object> getChartConfig() { return chartConfig; }
    public void setChartConfig(Map<String, Object> chartConfig) { this.chartConfig = chartConfig; }

    public boolean isPublic() { return isPublic; }
    public void setPublic(boolean isPublic) { this.isPublic = isPublic; }

    public boolean isScheduled() { return isScheduled; }
    public void setScheduled(boolean scheduled) { isScheduled = scheduled; }

    public String getScheduleExpression() { return scheduleExpression; }
    public void setScheduleExpression(String scheduleExpression) { this.scheduleExpression = scheduleExpression; }
}