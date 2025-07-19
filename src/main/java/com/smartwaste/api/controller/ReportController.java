package com.smartwaste.api.controller;

import com.smartwaste.domain.service.SimpleReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 报表控制器
 */
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private SimpleReportService simpleReportService;

    /**
     * 生成日报
     */
    @GetMapping("/daily")
    public Map<String, Object> generateDailyReport(@RequestParam(required = false) String date) {
        LocalDateTime reportDate = date != null ? 
            LocalDateTime.parse(date + "T00:00:00") : 
            LocalDateTime.now();
        return simpleReportService.generateDailyReport(reportDate);
    }

    /**
     * 生成月报
     */
    @GetMapping("/monthly")
    public Map<String, Object> generateMonthlyReport(
            @RequestParam(defaultValue = "2024") int year,
            @RequestParam(defaultValue = "1") int month) {
        return simpleReportService.generateMonthlyReport(year, month);
    }

    /**
     * 获取趋势分析
     */
    @GetMapping("/trend")
    public Map<String, Object> getTrendAnalysis(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        LocalDateTime start = startDate != null ? 
            LocalDateTime.parse(startDate + "T00:00:00") : 
            LocalDateTime.now().minusDays(30);
        LocalDateTime end = endDate != null ? 
            LocalDateTime.parse(endDate + "T23:59:59") : 
            LocalDateTime.now();
            
        return simpleReportService.getTrendAnalysis(start, end);
    }

    /**
     * 获取KPI统计
     */
    @GetMapping("/kpi")
    public Map<String, Object> getKpiStatistics() {
        return simpleReportService.getKpiStatistics();
    }
}