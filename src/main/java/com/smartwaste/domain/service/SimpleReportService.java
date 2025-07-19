package com.smartwaste.domain.service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 简化的报表服务接口
 */
public interface SimpleReportService {
    
    /**
     * 生成日报
     */
    Map<String, Object> generateDailyReport(LocalDateTime date);
    
    /**
     * 生成月报
     */
    Map<String, Object> generateMonthlyReport(int year, int month);
    
    /**
     * 获取趋势分析
     */
    Map<String, Object> getTrendAnalysis(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * 获取KPI统计
     */
    Map<String, Object> getKpiStatistics();
}