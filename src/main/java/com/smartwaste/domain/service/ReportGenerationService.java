package com.smartwaste.domain.service;

import com.smartwaste.domain.model.GeneratedReport;
import com.smartwaste.domain.model.ReportTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 报表生成服务接口
 */
public interface ReportGenerationService {
    
    /**
     * 创建报表模板
     * @param templateName 模板名称
     * @param templateType 模板类型
     * @param category 报表分类
     * @param description 描述
     * @param createdBy 创建人
     * @return 创建的模板
     */
    ReportTemplate createReportTemplate(String templateName, String templateType, String category, 
                                      String description, String createdBy);
    
    /**
     * 更新报表模板
     * @param templateId 模板ID
     * @param templateName 模板名称
     * @param description 描述
     * @param updatedBy 更新人
     * @return 更新后的模板
     */
    ReportTemplate updateReportTemplate(String templateId, String templateName, String description, String updatedBy);
    
    /**
     * 配置报表模板
     * @param templateId 模板ID
     * @param dataSources 数据源配置
     * @param metrics 指标配置
     * @param filters 过滤条件
     * @param chartConfig 图表配置
     * @return 配置后的模板
     */
    ReportTemplate configureReportTemplate(String templateId, String dataSources, String metrics, 
                                         String filters, String chartConfig);
    
    /**
     * 生成报表
     * @param templateId 模板ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param format 输出格式
     * @param generatedBy 生成人
     * @return 生成的报表
     */
    GeneratedReport generateReport(String templateId, LocalDateTime startDate, LocalDateTime endDate, 
                                 String format, String generatedBy);
    
    /**
     * 异步生成报表
     * @param templateId 模板ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param format 输出格式
     * @param generatedBy 生成人
     * @return 报表任务ID
     */
    String generateReportAsync(String templateId, LocalDateTime startDate, LocalDateTime endDate, 
                             String format, String generatedBy);
    
    /**
     * 生成日报
     * @param date 日期
     * @param generatedBy 生成人
     * @return 生成的日报列表
     */
    List<GeneratedReport> generateDailyReports(LocalDateTime date, String generatedBy);
    
    /**
     * 生成月报
     * @param year 年份
     * @param month 月份
     * @param generatedBy 生成人
     * @return 生成的月报列表
     */
    List<GeneratedReport> generateMonthlyReports(int year, int month, String generatedBy);
    
    /**
     * 生成自定义报表
     * @param reportConfig 报表配置
     * @param generatedBy 生成人
     * @return 生成的报表
     */
    GeneratedReport generateCustomReport(ReportConfig reportConfig, String generatedBy);
    
    /**
     * 获取报表详情
     * @param reportId 报表ID
     * @return 报表详情
     */
    GeneratedReport getReportDetails(String reportId);
    
    /**
     * 获取报表模板
     * @param templateId 模板ID
     * @return 报表模板
     */
    ReportTemplate getReportTemplate(String templateId);
    
    /**
     * 获取所有报表模板
     * @param category 分类（可选）
     * @return 模板列表
     */
    List<ReportTemplate> getReportTemplates(String category);
    
    /**
     * 获取用户的报表
     * @param userId 用户ID
     * @param limit 限制数量
     * @return 报表列表
     */
    List<GeneratedReport> getUserReports(String userId, int limit);
    
    /**
     * 获取报表列表
     * @param templateId 模板ID（可选）
     * @param status 状态（可选）
     * @param startDate 开始日期（可选）
     * @param endDate 结束日期（可选）
     * @return 报表列表
     */
    List<GeneratedReport> getReports(String templateId, String status, LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * 下载报表
     * @param reportId 报表ID
     * @return 文件下载URL
     */
    String downloadReport(String reportId);
    
    /**
     * 分享报表
     * @param reportId 报表ID
     * @param expiryDays 过期天数
     * @return 分享链接
     */
    String shareReport(String reportId, int expiryDays);
    
    /**
     * 删除报表
     * @param reportId 报表ID
     * @return 是否成功
     */
    boolean deleteReport(String reportId);
    
    /**
     * 获取趋势分析数据
     * @param metric 指标名称
     * @param timeRange 时间范围
     * @param granularity 时间粒度
     * @return 趋势分析数据
     */
    TrendAnalysisData getTrendAnalysis(String metric, String timeRange, String granularity);
    
    /**
     * 获取同比环比数据
     * @param metric 指标名称
     * @param currentPeriod 当前周期
     * @param comparisonType 对比类型：YOY（同比）, MOM（环比）
     * @return 对比分析数据
     */
    ComparisonAnalysisData getComparisonAnalysis(String metric, String currentPeriod, String comparisonType);
    
    /**
     * 获取多维度分析数据
     * @param dimensions 维度列表
     * @param metrics 指标列表
     * @param filters 过滤条件
     * @param timeRange 时间范围
     * @return 多维度分析数据
     */
    MultiDimensionalAnalysisData getMultiDimensionalAnalysis(List<String> dimensions, List<String> metrics, 
                                                           Map<String, Object> filters, String timeRange);
    
    /**
     * 导出报表数据
     * @param reportId 报表ID
     * @param format 导出格式
     * @return 导出文件URL
     */
    String exportReportData(String reportId, String format);
    
    /**
     * 调度报表生成
     * @param templateId 模板ID
     * @param scheduleConfig 调度配置
     * @return 调度任务ID
     */
    String scheduleReportGeneration(String templateId, ScheduleConfig scheduleConfig);
    
    /**
     * 取消报表生成
     * @param reportId 报表ID
     * @return 是否成功
     */
    boolean cancelReportGeneration(String reportId);
    
    /**
     * 获取报表生成状态
     * @param reportId 报表ID
     * @return 生成状态
     */
    ReportGenerationStatus getReportGenerationStatus(String reportId);
    
    /**
     * 生成报表预览
     * @param templateId 模板ID
     * @param sampleSize 样本大小
     * @return 预览数据
     */
    ReportPreviewData generateReportPreview(String templateId, int sampleSize);
    
    /**
     * 验证报表模板
     * @param templateId 模板ID
     * @return 验证结果
     */
    TemplateValidationResult validateReportTemplate(String templateId);
    
    /**
     * 报表配置
     */
    class ReportConfig {
        private String reportName;
        private String reportType;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private List<String> dataSources;
        private List<String> metrics;
        private Map<String, Object> filters;
        private List<String> groupBy;
        private Map<String, String> sorting;
        private String outputFormat;
        private Map<String, Object> chartConfig;
        
        // Getters and Setters
        public String getReportName() {
            return reportName;
        }
        
        public void setReportName(String reportName) {
            this.reportName = reportName;
        }
        
        public String getReportType() {
            return reportType;
        }
        
        public void setReportType(String reportType) {
            this.reportType = reportType;
        }
        
        public LocalDateTime getStartDate() {
            return startDate;
        }
        
        public void setStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
        }
        
        public LocalDateTime getEndDate() {
            return endDate;
        }
        
        public void setEndDate(LocalDateTime endDate) {
            this.endDate = endDate;
        }
        
        public List<String> getDataSources() {
            return dataSources;
        }
        
        public void setDataSources(List<String> dataSources) {
            this.dataSources = dataSources;
        }
        
        public List<String> getMetrics() {
            return metrics;
        }
        
        public void setMetrics(List<String> metrics) {
            this.metrics = metrics;
        }
        
        public Map<String, Object> getFilters() {
            return filters;
        }
        
        public void setFilters(Map<String, Object> filters) {
            this.filters = filters;
        }
        
        public List<String> getGroupBy() {
            return groupBy;
        }
        
        public void setGroupBy(List<String> groupBy) {
            this.groupBy = groupBy;
        }
        
        public Map<String, String> getSorting() {
            return sorting;
        }
        
        public void setSorting(Map<String, String> sorting) {
            this.sorting = sorting;
        }
        
        public String getOutputFormat() {
            return outputFormat;
        }
        
        public void setOutputFormat(String outputFormat) {
            this.outputFormat = outputFormat;
        }
        
        public Map<String, Object> getChartConfig() {
            return chartConfig;
        }
        
        public void setChartConfig(Map<String, Object> chartConfig) {
            this.chartConfig = chartConfig;
        }
    }
    
    /**
     * 趋势分析数据
     */
    class TrendAnalysisData {
        private String metric;
        private String timeRange;
        private List<TrendDataPoint> dataPoints;
        private String trendDirection; // UP, DOWN, STABLE
        private Double trendRate;
        private String analysis;
        
        // Getters and Setters
        public String getMetric() {
            return metric;
        }
        
        public void setMetric(String metric) {
            this.metric = metric;
        }
        
        public String getTimeRange() {
            return timeRange;
        }
        
        public void setTimeRange(String timeRange) {
            this.timeRange = timeRange;
        }
        
        public List<TrendDataPoint> getDataPoints() {
            return dataPoints;
        }
        
        public void setDataPoints(List<TrendDataPoint> dataPoints) {
            this.dataPoints = dataPoints;
        }
        
        public String getTrendDirection() {
            return trendDirection;
        }
        
        public void setTrendDirection(String trendDirection) {
            this.trendDirection = trendDirection;
        }
        
        public Double getTrendRate() {
            return trendRate;
        }
        
        public void setTrendRate(Double trendRate) {
            this.trendRate = trendRate;
        }
        
        public String getAnalysis() {
            return analysis;
        }
        
        public void setAnalysis(String analysis) {
            this.analysis = analysis;
        }
    }
    
    /**
     * 趋势数据点
     */
    class TrendDataPoint {
        private LocalDateTime timestamp;
        private Double value;
        private Double changeRate;
        
        // Getters and Setters
        public LocalDateTime getTimestamp() {
            return timestamp;
        }
        
        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }
        
        public Double getValue() {
            return value;
        }
        
        public void setValue(Double value) {
            this.value = value;
        }
        
        public Double getChangeRate() {
            return changeRate;
        }
        
        public void setChangeRate(Double changeRate) {
            this.changeRate = changeRate;
        }
    }
    
    /**
     * 对比分析数据
     */
    class ComparisonAnalysisData {
        private String metric;
        private String comparisonType;
        private Double currentValue;
        private Double previousValue;
        private Double changeValue;
        private Double changeRate;
        private String changeDirection;
        private String analysis;
        
        // Getters and Setters
        public String getMetric() {
            return metric;
        }
        
        public void setMetric(String metric) {
            this.metric = metric;
        }
        
        public String getComparisonType() {
            return comparisonType;
        }
        
        public void setComparisonType(String comparisonType) {
            this.comparisonType = comparisonType;
        }
        
        public Double getCurrentValue() {
            return currentValue;
        }
        
        public void setCurrentValue(Double currentValue) {
            this.currentValue = currentValue;
        }
        
        public Double getPreviousValue() {
            return previousValue;
        }
        
        public void setPreviousValue(Double previousValue) {
            this.previousValue = previousValue;
        }
        
        public Double getChangeValue() {
            return changeValue;
        }
        
        public void setChangeValue(Double changeValue) {
            this.changeValue = changeValue;
        }
        
        public Double getChangeRate() {
            return changeRate;
        }
        
        public void setChangeRate(Double changeRate) {
            this.changeRate = changeRate;
        }
        
        public String getChangeDirection() {
            return changeDirection;
        }
        
        public void setChangeDirection(String changeDirection) {
            this.changeDirection = changeDirection;
        }
        
        public String getAnalysis() {
            return analysis;
        }
        
        public void setAnalysis(String analysis) {
            this.analysis = analysis;
        }
    }
    
    /**
     * 多维度分析数据
     */
    class MultiDimensionalAnalysisData {
        private List<String> dimensions;
        private List<String> metrics;
        private List<Map<String, Object>> data;
        private Map<String, Object> summary;
        private List<String> insights;
        
        // Getters and Setters
        public List<String> getDimensions() {
            return dimensions;
        }
        
        public void setDimensions(List<String> dimensions) {
            this.dimensions = dimensions;
        }
        
        public List<String> getMetrics() {
            return metrics;
        }
        
        public void setMetrics(List<String> metrics) {
            this.metrics = metrics;
        }
        
        public List<Map<String, Object>> getData() {
            return data;
        }
        
        public void setData(List<Map<String, Object>> data) {
            this.data = data;
        }
        
        public Map<String, Object> getSummary() {
            return summary;
        }
        
        public void setSummary(Map<String, Object> summary) {
            this.summary = summary;
        }
        
        public List<String> getInsights() {
            return insights;
        }
        
        public void setInsights(List<String> insights) {
            this.insights = insights;
        }
    }
    
    /**
     * 调度配置
     */
    class ScheduleConfig {
        private String scheduleType; // DAILY, WEEKLY, MONTHLY
        private String cronExpression;
        private List<String> recipients;
        private String outputFormat;
        private boolean enabled;
        
        // Getters and Setters
        public String getScheduleType() {
            return scheduleType;
        }
        
        public void setScheduleType(String scheduleType) {
            this.scheduleType = scheduleType;
        }
        
        public String getCronExpression() {
            return cronExpression;
        }
        
        public void setCronExpression(String cronExpression) {
            this.cronExpression = cronExpression;
        }
        
        public List<String> getRecipients() {
            return recipients;
        }
        
        public void setRecipients(List<String> recipients) {
            this.recipients = recipients;
        }
        
        public String getOutputFormat() {
            return outputFormat;
        }
        
        public void setOutputFormat(String outputFormat) {
            this.outputFormat = outputFormat;
        }
        
        public boolean isEnabled() {
            return enabled;
        }
        
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
    
    /**
     * 报表生成状态
     */
    class ReportGenerationStatus {
        private String reportId;
        private String status;
        private int progress;
        private String currentStep;
        private String errorMessage;
        private LocalDateTime startTime;
        private LocalDateTime estimatedCompletionTime;
        
        // Getters and Setters
        public String getReportId() {
            return reportId;
        }
        
        public void setReportId(String reportId) {
            this.reportId = reportId;
        }
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            this.status = status;
        }
        
        public int getProgress() {
            return progress;
        }
        
        public void setProgress(int progress) {
            this.progress = progress;
        }
        
        public String getCurrentStep() {
            return currentStep;
        }
        
        public void setCurrentStep(String currentStep) {
            this.currentStep = currentStep;
        }
        
        public String getErrorMessage() {
            return errorMessage;
        }
        
        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
        
        public LocalDateTime getStartTime() {
            return startTime;
        }
        
        public void setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }
        
        public LocalDateTime getEstimatedCompletionTime() {
            return estimatedCompletionTime;
        }
        
        public void setEstimatedCompletionTime(LocalDateTime estimatedCompletionTime) {
            this.estimatedCompletionTime = estimatedCompletionTime;
        }
    }
    
    /**
     * 报表预览数据
     */
    class ReportPreviewData {
        private String templateId;
        private List<Map<String, Object>> sampleData;
        private Map<String, Object> chartPreview;
        private int totalRecords;
        private String dataQuality;
        
        // Getters and Setters
        public String getTemplateId() {
            return templateId;
        }
        
        public void setTemplateId(String templateId) {
            this.templateId = templateId;
        }
        
        public List<Map<String, Object>> getSampleData() {
            return sampleData;
        }
        
        public void setSampleData(List<Map<String, Object>> sampleData) {
            this.sampleData = sampleData;
        }
        
        public Map<String, Object> getChartPreview() {
            return chartPreview;
        }
        
        public void setChartPreview(Map<String, Object> chartPreview) {
            this.chartPreview = chartPreview;
        }
        
        public int getTotalRecords() {
            return totalRecords;
        }
        
        public void setTotalRecords(int totalRecords) {
            this.totalRecords = totalRecords;
        }
        
        public String getDataQuality() {
            return dataQuality;
        }
        
        public void setDataQuality(String dataQuality) {
            this.dataQuality = dataQuality;
        }
    }
    
    /**
     * 模板验证结果
     */
    class TemplateValidationResult {
        private boolean isValid;
        private List<String> errors;
        private List<String> warnings;
        private Map<String, Object> suggestions;
        
        // Getters and Setters
        public boolean isValid() {
            return isValid;
        }
        
        public void setValid(boolean valid) {
            isValid = valid;
        }
        
        public List<String> getErrors() {
            return errors;
        }
        
        public void setErrors(List<String> errors) {
            this.errors = errors;
        }
        
        public List<String> getWarnings() {
            return warnings;
        }
        
        public void setWarnings(List<String> warnings) {
            this.warnings = warnings;
        }
        
        public Map<String, Object> getSuggestions() {
            return suggestions;
        }
        
        public void setSuggestions(Map<String, Object> suggestions) {
            this.suggestions = suggestions;
        }
    }
}