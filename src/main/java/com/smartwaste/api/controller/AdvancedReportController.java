package com.smartwaste.api.controller;

import com.smartwaste.domain.model.*;
import com.smartwaste.domain.service.AdvancedReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 高级报表控制器
 */
@RestController
@RequestMapping("/api/advanced-reports")
@CrossOrigin(origins = "*")
public class AdvancedReportController {

    @Autowired
    private AdvancedReportService advancedReportService;

    /**
     * 生成日报
     */
    @GetMapping("/daily")
    public ResponseEntity<ReportData> generateDailyReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam(defaultValue = "OVERALL") String reportType) {
        
        ReportData report = advancedReportService.generateDailyReport(date, reportType);
        return ResponseEntity.ok(report);
    }

    /**
     * 生成月报
     */
    @GetMapping("/monthly")
    public ResponseEntity<ReportData> generateMonthlyReport(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam(defaultValue = "OVERALL") String reportType) {
        
        ReportData report = advancedReportService.generateMonthlyReport(year, month, reportType);
        return ResponseEntity.ok(report);
    }

    /**
     * 生成年报
     */
    @GetMapping("/yearly")
    public ResponseEntity<ReportData> generateYearlyReport(
            @RequestParam int year,
            @RequestParam(defaultValue = "OVERALL") String reportType) {
        
        ReportData report = advancedReportService.generateYearlyReport(year, reportType);
        return ResponseEntity.ok(report);
    }

    /**
     * 生成趋势分析
     */
    @GetMapping("/trend-analysis")
    public ResponseEntity<TrendAnalysis> generateTrendAnalysis(
            @RequestParam String metric,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        
        TrendAnalysis analysis = advancedReportService.generateTrendAnalysis(metric, startDate, endDate);
        return ResponseEntity.ok(analysis);
    }

    /**
     * 生成同比环比分析
     */
    @GetMapping("/comparison-analysis")
    public ResponseEntity<Map<String, Object>> generateComparisonAnalysis(
            @RequestParam String metric,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime currentPeriodStart,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime currentPeriodEnd,
            @RequestParam(defaultValue = "YEAR_OVER_YEAR") String comparisonType) {
        
        Map<String, Object> analysis = advancedReportService.generateComparisonAnalysis(
                metric, currentPeriodStart, currentPeriodEnd, comparisonType);
        return ResponseEntity.ok(analysis);
    }

    /**
     * 创建自定义报表
     */
    @PostMapping("/custom")
    public ResponseEntity<CustomReport> createCustomReport(@RequestBody CustomReport report) {
        CustomReport createdReport = advancedReportService.createCustomReport(report);
        return ResponseEntity.ok(createdReport);
    }

    /**
     * 执行自定义报表
     */
    @PostMapping("/custom/{reportId}/execute")
    public ResponseEntity<ReportData> executeCustomReport(
            @PathVariable String reportId,
            @RequestBody(required = false) Map<String, Object> parameters) {
        
        ReportData result = advancedReportService.executeCustomReport(reportId, parameters);
        return ResponseEntity.ok(result);
    }

    /**
     * 获取所有自定义报表
     */
    @GetMapping("/custom")
    public ResponseEntity<List<CustomReport>> getAllCustomReports(
            @RequestParam(required = false) String userId) {
        
        List<CustomReport> reports = advancedReportService.getAllCustomReports(userId);
        return ResponseEntity.ok(reports);
    }

    /**
     * 更新自定义报表
     */
    @PutMapping("/custom/{reportId}")
    public ResponseEntity<CustomReport> updateCustomReport(
            @PathVariable String reportId,
            @RequestBody CustomReport report) {
        
        CustomReport updatedReport = advancedReportService.updateCustomReport(reportId, report);
        return ResponseEntity.ok(updatedReport);
    }

    /**
     * 删除自定义报表
     */
    @DeleteMapping("/custom/{reportId}")
    public ResponseEntity<Void> deleteCustomReport(@PathVariable String reportId) {
        advancedReportService.deleteCustomReport(reportId);
        return ResponseEntity.ok().build();
    }

    /**
     * 获取可用数据字段
     */
    @GetMapping("/data-fields")
    public ResponseEntity<List<String>> getAvailableDataFields() {
        List<String> fields = advancedReportService.getAvailableDataFields();
        return ResponseEntity.ok(fields);
    }

    /**
     * 获取支持的图表类型
     */
    @GetMapping("/chart-types")
    public ResponseEntity<List<String>> getSupportedChartTypes() {
        List<String> chartTypes = advancedReportService.getSupportedChartTypes();
        return ResponseEntity.ok(chartTypes);
    }

    /**
     * 导出报表
     */
    @GetMapping("/export/{reportId}")
    public ResponseEntity<byte[]> exportReport(
            @PathVariable String reportId,
            @RequestParam(defaultValue = "PDF") String format) {
        
        byte[] reportData = advancedReportService.exportReport(reportId, format);
        
        HttpHeaders headers = new HttpHeaders();
        switch (format.toUpperCase()) {
            case "PDF":
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "report_" + reportId + ".pdf");
                break;
            case "EXCEL":
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", "report_" + reportId + ".xlsx");
                break;
            case "CSV":
                headers.setContentType(MediaType.TEXT_PLAIN);
                headers.setContentDispositionFormData("attachment", "report_" + reportId + ".csv");
                break;
            default:
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        }
        
        return ResponseEntity.ok()
                .headers(headers)
                .body(reportData);
    }

    /**
     * 获取报表历史
     */
    @GetMapping("/history")
    public ResponseEntity<List<ReportData>> getReportHistory(
            @RequestParam(required = false) String reportType,
            @RequestParam(defaultValue = "10") int limit) {
        
        List<ReportData> history = advancedReportService.getReportHistory(reportType, limit);
        return ResponseEntity.ok(history);
    }

    /**
     * 获取实时数据快照
     */
    @GetMapping("/real-time-snapshot")
    public ResponseEntity<Map<String, Object>> getRealTimeSnapshot() {
        Map<String, Object> snapshot = advancedReportService.getRealTimeSnapshot();
        return ResponseEntity.ok(snapshot);
    }

    /**
     * 获取多维度数据分析
     */
    @PostMapping("/multi-dimensional-analysis")
    public ResponseEntity<Map<String, Object>> getMultiDimensionalAnalysis(
            @RequestBody Map<String, Object> request) {
        
        @SuppressWarnings("unchecked")
        List<String> dimensions = (List<String>) request.get("dimensions");
        @SuppressWarnings("unchecked")
        List<String> metrics = (List<String>) request.get("metrics");
        @SuppressWarnings("unchecked")
        Map<String, Object> filters = (Map<String, Object>) request.get("filters");
        
        Map<String, Object> analysis = advancedReportService.getMultiDimensionalAnalysis(
                dimensions, metrics, filters);
        return ResponseEntity.ok(analysis);
    }

    /**
     * 获取报表模板列表
     */
    @GetMapping("/templates")
    public ResponseEntity<List<Map<String, Object>>> getReportTemplates() {
        List<Map<String, Object>> templates = List.of(
            Map.of(
                "id", "daily_disposal_template",
                "name", "日投放量报表模板",
                "description", "展示每日垃圾投放量统计",
                "category", "投放管理",
                "fields", List.of("disposalCount", "disposalWeight", "recycleRate"),
                "chartType", "LINE"
            ),
            Map.of(
                "id", "monthly_efficiency_template",
                "name", "月度效率报表模板",
                "description", "分析月度收运效率趋势",
                "category", "收运管理",
                "fields", List.of("collectionEfficiency", "vehicleUtilization", "fuelConsumption"),
                "chartType", "BAR"
            ),
            Map.of(
                "id", "device_status_template",
                "name", "设备状态报表模板",
                "description", "监控设备运行状态和健康度",
                "category", "设备管理",
                "fields", List.of("deviceUptime", "errorCount", "maintenanceAlerts"),
                "chartType", "PIE"
            ),
            Map.of(
                "id", "user_engagement_template",
                "name", "用户参与度报表模板",
                "description", "分析用户参与度和满意度",
                "category", "用户管理",
                "fields", List.of("activeUsers", "userSatisfaction", "averagePointsEarned"),
                "chartType", "AREA"
            )
        );
        
        return ResponseEntity.ok(templates);
    }

    /**
     * 基于模板创建报表
     */
    @PostMapping("/templates/{templateId}/create")
    public ResponseEntity<CustomReport> createReportFromTemplate(
            @PathVariable String templateId,
            @RequestBody Map<String, Object> customization) {
        
        // 模拟基于模板创建报表
        CustomReport report = new CustomReport();
        report.setReportName((String) customization.getOrDefault("name", "基于模板的报表"));
        report.setDescription((String) customization.getOrDefault("description", ""));
        report.setCreatedBy((String) customization.getOrDefault("createdBy", "system"));
        
        // 根据模板ID设置默认配置
        switch (templateId) {
            case "daily_disposal_template":
                report.setDataFields(List.of("disposalCount", "disposalWeight", "recycleRate"));
                report.setChartType("LINE");
                break;
            case "monthly_efficiency_template":
                report.setDataFields(List.of("collectionEfficiency", "vehicleUtilization", "fuelConsumption"));
                report.setChartType("BAR");
                break;
            case "device_status_template":
                report.setDataFields(List.of("deviceUptime", "errorCount", "maintenanceAlerts"));
                report.setChartType("PIE");
                break;
            case "user_engagement_template":
                report.setDataFields(List.of("activeUsers", "userSatisfaction", "averagePointsEarned"));
                report.setChartType("AREA");
                break;
        }
        
        CustomReport createdReport = advancedReportService.createCustomReport(report);
        return ResponseEntity.ok(createdReport);
    }

    /**
     * 获取报表性能统计
     */
    @GetMapping("/performance-stats")
    public ResponseEntity<Map<String, Object>> getReportPerformanceStats() {
        Map<String, Object> stats = Map.of(
            "totalReportsGenerated", 1247 + (int)(Math.random() * 100),
            "averageGenerationTime", 2.3 + Math.random() * 1.5,
            "cacheHitRate", 78.5 + Math.random() * 15,
            "popularReportTypes", List.of(
                Map.of("type", "DAILY", "count", 456),
                Map.of("type", "MONTHLY", "count", 234),
                Map.of("type", "CUSTOM", "count", 189),
                Map.of("type", "TREND", "count", 156)
            ),
            "systemLoad", 65.2 + Math.random() * 20,
            "memoryUsage", 45.8 + Math.random() * 25
        );
        
        return ResponseEntity.ok(stats);
    }
}