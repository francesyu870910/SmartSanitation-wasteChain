package com.smartwaste.domain.service;

import com.smartwaste.domain.model.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 高级报表服务接口
 */
public interface AdvancedReportService {
    
    /**
     * 生成日报
     */
    ReportData generateDailyReport(LocalDateTime date, String reportType);
    
    /**
     * 生成月报
     */
    ReportData generateMonthlyReport(int year, int month, String reportType);
    
    /**
     * 生成年报
     */
    ReportData generateYearlyReport(int year, String reportType);
    
    /**
     * 生成趋势分析报告
     */
    TrendAnalysis generateTrendAnalysis(String metric, LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * 生成同比环比分析
     */
    Map<String, Object> generateComparisonAnalysis(String metric, LocalDateTime currentPeriodStart, 
                                                  LocalDateTime currentPeriodEnd, String comparisonType);
    
    /**
     * 创建自定义报表
     */
    CustomReport createCustomReport(CustomReport report);
    
    /**
     * 执行自定义报表
     */
    ReportData executeCustomReport(String reportId, Map<String, Object> parameters);
    
    /**
     * 获取所有自定义报表
     */
    List<CustomReport> getAllCustomReports(String userId);
    
    /**
     * 更新自定义报表
     */
    CustomReport updateCustomReport(String reportId, CustomReport report);
    
    /**
     * 删除自定义报表
     */
    void deleteCustomReport(String reportId);
    
    /**
     * 获取报表数据源字段
     */
    List<String> getAvailableDataFields();
    
    /**
     * 获取支持的图表类型
     */
    List<String> getSupportedChartTypes();
    
    /**
     * 导出报表为不同格式
     */
    byte[] exportReport(String reportId, String format); // PDF, EXCEL, CSV
    
    /**
     * 获取报表执行历史
     */
    List<ReportData> getReportHistory(String reportType, int limit);
    
    /**
     * 获取实时数据快照
     */
    Map<String, Object> getRealTimeSnapshot();
    
    /**
     * 获取多维度数据分析
     */
    Map<String, Object> getMultiDimensionalAnalysis(List<String> dimensions, 
                                                   List<String> metrics, 
                                                   Map<String, Object> filters);
}