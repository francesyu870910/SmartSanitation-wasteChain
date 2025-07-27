package com.smartwaste.domain.service.impl;

import com.smartwaste.domain.model.CustomReport;
import com.smartwaste.domain.model.ReportData;
import com.smartwaste.domain.model.TrendAnalysis;
import com.smartwaste.domain.service.AdvancedReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 高级报表服务测试类
 */
@SpringBootTest
class AdvancedReportServiceImplTest {

    private AdvancedReportService reportService;

    @BeforeEach
    void setUp() {
        reportService = new AdvancedReportServiceImpl();
    }

    @Test
    void testGenerateDailyReport() {
        // 测试日报生成
        LocalDateTime date = LocalDateTime.of(2024, 1, 15, 0, 0);
        ReportData report = reportService.generateDailyReport(date, "收运");
        
        assertNotNull(report);
        assertEquals("收运", report.getReportType());
        assertEquals("收运日报", report.getTitle());
        assertNotNull(report.getReportId());
        assertNotNull(report.getData());
        assertTrue(report.getData().containsKey("totalCollection"));
        assertTrue(report.getData().containsKey("completionRate"));
    }

    @Test
    void testGenerateMonthlyReport() {
        // 测试月报生成
        ReportData report = reportService.generateMonthlyReport(2024, 1, "投放");
        
        assertNotNull(report);
        assertEquals("投放", report.getReportType());
        assertEquals("投放月报", report.getTitle());
        assertNotNull(report.getReportId());
        assertNotNull(report.getData());
        assertTrue(report.getData().containsKey("totalDisposal"));
        assertTrue(report.getData().containsKey("userParticipation"));
    }

    @Test
    void testGenerateYearlyReport() {
        // 测试年报生成
        ReportData report = reportService.generateYearlyReport(2024, "处置");
        
        assertNotNull(report);
        assertEquals("处置", report.getReportType());
        assertEquals("处置年报", report.getTitle());
        assertNotNull(report.getReportId());
        assertNotNull(report.getData());
        assertTrue(report.getData().containsKey("totalProcessed"));
        assertTrue(report.getData().containsKey("recyclingRate"));
    }

    @Test
    void testGenerateTrendAnalysis() {
        // 测试趋势分析
        LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 1, 31, 23, 59);
        
        TrendAnalysis analysis = reportService.generateTrendAnalysis("收运量", startDate, endDate);
        
        assertNotNull(analysis);
        assertEquals("趋势分析", analysis.getAnalysisType());
        assertEquals("收运量", analysis.getMetric());
        assertNotNull(analysis.getDataPoints());
        assertFalse(analysis.getDataPoints().isEmpty());
        assertNotNull(analysis.getTrend());
        assertNotNull(analysis.getSummary());
        assertTrue(analysis.getTrend().matches("INCREASING|DECREASING|STABLE"));
    }

    @Test
    void testGenerateComparisonAnalysis() {
        // 测试同比环比分析
        LocalDateTime currentStart = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime currentEnd = LocalDateTime.of(2024, 1, 31, 23, 59);
        
        Map<String, Object> yoyAnalysis = reportService.generateComparisonAnalysis(
            "收运量", currentStart, currentEnd, "YOY");
        
        assertNotNull(yoyAnalysis);
        assertTrue(yoyAnalysis.containsKey("currentValue"));
        assertTrue(yoyAnalysis.containsKey("previousValue"));
        assertTrue(yoyAnalysis.containsKey("percentageChange"));
        assertTrue(yoyAnalysis.containsKey("trend"));
        assertEquals("同比", yoyAnalysis.get("comparisonType"));
        
        Map<String, Object> momAnalysis = reportService.generateComparisonAnalysis(
            "收运量", currentStart, currentEnd, "MOM");
        
        assertNotNull(momAnalysis);
        assertEquals("环比", momAnalysis.get("comparisonType"));
    }

    @Test
    void testCreateAndExecuteCustomReport() {
        // 测试创建自定义报表
        CustomReport customReport = new CustomReport();
        customReport.setReportName("测试自定义报表");
        customReport.setDescription("这是一个测试报表");
        customReport.setCreatedBy("test-user");
        customReport.setDataFields(List.of("收运量", "回收率"));
        customReport.setChartType("LINE");
        customReport.setPublic(true);
        
        CustomReport created = reportService.createCustomReport(customReport);
        
        assertNotNull(created);
        assertNotNull(created.getReportId());
        assertEquals("测试自定义报表", created.getReportName());
        assertNotNull(created.getCreatedAt());
        
        // 测试执行自定义报表
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", "test-user");
        
        ReportData result = reportService.executeCustomReport(created.getReportId(), parameters);
        
        assertNotNull(result);
        assertEquals("自定义报表", result.getReportType());
        assertEquals("测试自定义报表", result.getTitle());
        assertNotNull(result.getData());
    }

    @Test
    void testGetAllCustomReports() {
        // 先创建一个自定义报表
        CustomReport customReport = new CustomReport();
        customReport.setReportName("测试报表列表");
        customReport.setCreatedBy("test-user");
        customReport.setPublic(true);
        
        reportService.createCustomReport(customReport);
        
        // 测试获取报表列表
        List<CustomReport> reports = reportService.getAllCustomReports("test-user");
        
        assertNotNull(reports);
        assertFalse(reports.isEmpty());
        assertTrue(reports.stream().anyMatch(r -> "测试报表列表".equals(r.getReportName())));
    }

    @Test
    void testGetAvailableDataFields() {
        // 测试获取可用数据字段
        List<String> fields = reportService.getAvailableDataFields();
        
        assertNotNull(fields);
        assertFalse(fields.isEmpty());
        assertTrue(fields.contains("收运量"));
        assertTrue(fields.contains("回收率"));
        assertTrue(fields.contains("用户参与度"));
    }

    @Test
    void testGetSupportedChartTypes() {
        // 测试获取支持的图表类型
        List<String> chartTypes = reportService.getSupportedChartTypes();
        
        assertNotNull(chartTypes);
        assertFalse(chartTypes.isEmpty());
        assertTrue(chartTypes.contains("LINE"));
        assertTrue(chartTypes.contains("BAR"));
        assertTrue(chartTypes.contains("PIE"));
    }

    @Test
    void testGetRealTimeSnapshot() {
        // 测试获取实时数据快照
        Map<String, Object> snapshot = reportService.getRealTimeSnapshot();
        
        assertNotNull(snapshot);
        assertTrue(snapshot.containsKey("timestamp"));
        assertTrue(snapshot.containsKey("systemStatus"));
        assertTrue(snapshot.containsKey("deviceStatus"));
        assertTrue(snapshot.containsKey("containerStatus"));
        assertTrue(snapshot.containsKey("vehicleStatus"));
        assertTrue(snapshot.containsKey("todayStats"));
    }

    @Test
    void testGetMultiDimensionalAnalysis() {
        // 测试多维度数据分析
        List<String> dimensions = List.of("区域", "垃圾类型");
        List<String> metrics = List.of("收运量", "回收率");
        Map<String, Object> filters = new HashMap<>();
        
        Map<String, Object> analysis = reportService.getMultiDimensionalAnalysis(
            dimensions, metrics, filters);
        
        assertNotNull(analysis);
        assertTrue(analysis.containsKey("dimensions"));
        assertTrue(analysis.containsKey("metrics"));
        assertTrue(analysis.containsKey("data"));
        assertTrue(analysis.containsKey("summary"));
        
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) analysis.get("data");
        assertNotNull(data);
        assertFalse(data.isEmpty());
    }

    @Test
    void testGetReportHistory() {
        // 先生成一些报表以创建历史记录
        reportService.generateDailyReport(LocalDateTime.now(), "收运");
        reportService.generateMonthlyReport(2024, 1, "投放");
        
        // 测试获取报表历史
        List<ReportData> history = reportService.getReportHistory(null, 10);
        
        assertNotNull(history);
        assertFalse(history.isEmpty());
        assertTrue(history.size() <= 10);
        
        // 测试按类型过滤
        List<ReportData> filteredHistory = reportService.getReportHistory("收运", 5);
        assertNotNull(filteredHistory);
        assertTrue(filteredHistory.stream().allMatch(r -> "收运".equals(r.getReportType())));
    }

    @Test
    void testUpdateCustomReport() {
        // 先创建一个报表
        CustomReport original = new CustomReport();
        original.setReportName("原始报表");
        original.setCreatedBy("test-user");
        
        CustomReport created = reportService.createCustomReport(original);
        
        // 更新报表
        CustomReport updated = new CustomReport();
        updated.setReportName("更新后的报表");
        updated.setDescription("更新后的描述");
        
        CustomReport result = reportService.updateCustomReport(created.getReportId(), updated);
        
        assertNotNull(result);
        assertEquals("更新后的报表", result.getReportName());
        assertEquals("更新后的描述", result.getDescription());
        assertEquals(created.getReportId(), result.getReportId());
        assertEquals("test-user", result.getCreatedBy()); // 创建者不应该改变
    }

    @Test
    void testDeleteCustomReport() {
        // 先创建一个报表
        CustomReport customReport = new CustomReport();
        customReport.setReportName("待删除报表");
        customReport.setCreatedBy("test-user");
        
        CustomReport created = reportService.createCustomReport(customReport);
        
        // 确认报表存在
        List<CustomReport> beforeDelete = reportService.getAllCustomReports("test-user");
        assertTrue(beforeDelete.stream().anyMatch(r -> created.getReportId().equals(r.getReportId())));
        
        // 删除报表
        reportService.deleteCustomReport(created.getReportId());
        
        // 确认报表已删除
        List<CustomReport> afterDelete = reportService.getAllCustomReports("test-user");
        assertFalse(afterDelete.stream().anyMatch(r -> created.getReportId().equals(r.getReportId())));
    }

    @Test
    void testExportReport() {
        // 生成一个报表
        ReportData report = reportService.generateDailyReport(LocalDateTime.now(), "收运");
        
        // 测试导出功能（这里只是测试方法调用，实际导出内容是模拟的）
        byte[] pdfData = reportService.exportReport(report.getReportId(), "PDF");
        assertNotNull(pdfData);
        assertTrue(pdfData.length > 0);
        
        byte[] excelData = reportService.exportReport(report.getReportId(), "EXCEL");
        assertNotNull(excelData);
        assertTrue(excelData.length > 0);
    }
}