package com.smartwaste.domain.service.impl;

import com.smartwaste.domain.model.*;
import com.smartwaste.domain.service.AdvancedReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 高级报表服务实现类
 */
@Service
public class AdvancedReportServiceImpl implements AdvancedReportService {

    // 模拟数据存储
    private final Map<String, CustomReport> customReports = new HashMap<>();
    private final List<ReportData> reportHistory = new ArrayList<>();

    @Override
    public ReportData generateDailyReport(LocalDateTime date, String reportType) {
        String reportId = "DAILY_" + date.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "_" + reportType;
        
        ReportData report = new ReportData("DAILY", "日报 - " + reportType);
        report.setReportId(reportId);
        report.setReportPeriodStart(date.withHour(0).withMinute(0).withSecond(0));
        report.setReportPeriodEnd(date.withHour(23).withMinute(59).withSecond(59));
        
        Map<String, Object> data = new HashMap<>();
        
        switch (reportType.toUpperCase()) {
            case "DISPOSAL":
                data = generateDisposalDailyData(date);
                break;
            case "COLLECTION":
                data = generateCollectionDailyData(date);
                break;
            case "DEVICE":
                data = generateDeviceDailyData(date);
                break;
            case "USER":
                data = generateUserDailyData(date);
                break;
            default:
                data = generateOverallDailyData(date);
        }
        
        report.setData(data);
        reportHistory.add(report);
        
        return report;
    }

    @Override
    public ReportData generateMonthlyReport(int year, int month, String reportType) {
        String reportId = "MONTHLY_" + year + String.format("%02d", month) + "_" + reportType;
        
        ReportData report = new ReportData("MONTHLY", "月报 - " + reportType);
        report.setReportId(reportId);
        report.setReportPeriodStart(LocalDateTime.of(year, month, 1, 0, 0));
        report.setReportPeriodEnd(LocalDateTime.of(year, month, 1, 0, 0).plusMonths(1).minusSeconds(1));
        
        Map<String, Object> data = generateMonthlyData(year, month, reportType);
        report.setData(data);
        reportHistory.add(report);
        
        return report;
    }

    @Override
    public ReportData generateYearlyReport(int year, String reportType) {
        String reportId = "YEARLY_" + year + "_" + reportType;
        
        ReportData report = new ReportData("YEARLY", "年报 - " + reportType);
        report.setReportId(reportId);
        report.setReportPeriodStart(LocalDateTime.of(year, 1, 1, 0, 0));
        report.setReportPeriodEnd(LocalDateTime.of(year, 12, 31, 23, 59, 59));
        
        Map<String, Object> data = generateYearlyData(year, reportType);
        report.setData(data);
        reportHistory.add(report);
        
        return report;
    }

    @Override
    public TrendAnalysis generateTrendAnalysis(String metric, LocalDateTime startDate, LocalDateTime endDate) {
        TrendAnalysis analysis = new TrendAnalysis("TREND_ANALYSIS", metric);
        analysis.setAnalysisId("TREND_" + metric + "_" + System.currentTimeMillis());
        analysis.setPeriodStart(startDate);
        analysis.setPeriodEnd(endDate);
        
        // 生成趋势数据点
        List<TrendDataPoint> dataPoints = generateTrendDataPoints(metric, startDate, endDate);
        analysis.setDataPoints(dataPoints);
        
        // 计算趋势和变化率
        if (dataPoints.size() >= 2) {
            double firstValue = dataPoints.get(0).getValue();
            double lastValue = dataPoints.get(dataPoints.size() - 1).getValue();
            double changeRate = ((lastValue - firstValue) / firstValue) * 100;
            
            analysis.setChangeRate(changeRate);
            
            if (changeRate > 5) {
                analysis.setTrend("INCREASING");
            } else if (changeRate < -5) {
                analysis.setTrend("DECREASING");
            } else {
                analysis.setTrend("STABLE");
            }
        }
        
        // 生成洞察和总结
        Map<String, Object> insights = generateTrendInsights(metric, dataPoints, analysis.getTrend());
        analysis.setInsights(insights);
        analysis.setSummary(generateTrendSummary(metric, analysis.getTrend(), analysis.getChangeRate()));
        
        return analysis;
    }

    @Override
    public Map<String, Object> generateComparisonAnalysis(String metric, LocalDateTime currentPeriodStart, 
                                                         LocalDateTime currentPeriodEnd, String comparisonType) {
        Map<String, Object> comparison = new HashMap<>();
        
        // 当前期间数据
        double currentValue = generateMetricValue(metric, currentPeriodStart, currentPeriodEnd);
        
        // 对比期间数据
        LocalDateTime compareStart, compareEnd;
        String compareLabel;
        
        switch (comparisonType.toUpperCase()) {
            case "YEAR_OVER_YEAR":
                compareStart = currentPeriodStart.minusYears(1);
                compareEnd = currentPeriodEnd.minusYears(1);
                compareLabel = "去年同期";
                break;
            case "MONTH_OVER_MONTH":
                compareStart = currentPeriodStart.minusMonths(1);
                compareEnd = currentPeriodEnd.minusMonths(1);
                compareLabel = "上月同期";
                break;
            case "QUARTER_OVER_QUARTER":
                compareStart = currentPeriodStart.minusMonths(3);
                compareEnd = currentPeriodEnd.minusMonths(3);
                compareLabel = "上季度同期";
                break;
            default:
                compareStart = currentPeriodStart.minusDays(7);
                compareEnd = currentPeriodEnd.minusDays(7);
                compareLabel = "上周同期";
        }
        
        double compareValue = generateMetricValue(metric, compareStart, compareEnd);
        double changeRate = compareValue != 0 ? ((currentValue - compareValue) / compareValue) * 100 : 0;
        double changeAmount = currentValue - compareValue;
        
        comparison.put("metric", metric);
        comparison.put("currentPeriod", Map.of(
            "start", currentPeriodStart,
            "end", currentPeriodEnd,
            "value", currentValue,
            "label", "当前期间"
        ));
        comparison.put("comparePeriod", Map.of(
            "start", compareStart,
            "end", compareEnd,
            "value", compareValue,
            "label", compareLabel
        ));
        comparison.put("changeRate", changeRate);
        comparison.put("changeAmount", changeAmount);
        comparison.put("changeDirection", changeRate > 0 ? "INCREASE" : changeRate < 0 ? "DECREASE" : "STABLE");
        comparison.put("analysis", generateComparisonInsights(metric, changeRate, changeAmount));
        
        return comparison;
    }

    @Override
    public CustomReport createCustomReport(CustomReport report) {
        String reportId = "CUSTOM_" + System.currentTimeMillis();
        report.setReportId(reportId);
        report.setCreatedAt(LocalDateTime.now());
        report.setLastModified(LocalDateTime.now());
        
        customReports.put(reportId, report);
        return report;
    }

    @Override
    public ReportData executeCustomReport(String reportId, Map<String, Object> parameters) {
        CustomReport customReport = customReports.get(reportId);
        if (customReport == null) {
            throw new RuntimeException("自定义报表不存在: " + reportId);
        }
        
        ReportData report = new ReportData("CUSTOM", customReport.getReportName());
        report.setReportId("EXEC_" + reportId + "_" + System.currentTimeMillis());
        
        // 执行自定义报表逻辑
        Map<String, Object> data = executeCustomReportLogic(customReport, parameters);
        report.setData(data);
        
        return report;
    }

    @Override
    public List<CustomReport> getAllCustomReports(String userId) {
        return customReports.values().stream()
                .filter(report -> userId == null || userId.equals(report.getCreatedBy()) || report.isPublic())
                .collect(Collectors.toList());
    }

    @Override
    public CustomReport updateCustomReport(String reportId, CustomReport report) {
        if (!customReports.containsKey(reportId)) {
            throw new RuntimeException("自定义报表不存在: " + reportId);
        }
        
        report.setReportId(reportId);
        report.setLastModified(LocalDateTime.now());
        customReports.put(reportId, report);
        
        return report;
    }

    @Override
    public void deleteCustomReport(String reportId) {
        if (!customReports.containsKey(reportId)) {
            throw new RuntimeException("自定义报表不存在: " + reportId);
        }
        customReports.remove(reportId);
    }

    @Override
    public List<String> getAvailableDataFields() {
        return Arrays.asList(
            "disposalCount", "disposalWeight", "userCount", "deviceCount",
            "collectionCount", "collectionEfficiency", "recycleRate",
            "violationCount", "alertCount", "processingTime",
            "energyConsumption", "costAmount", "satisfactionScore"
        );
    }

    @Override
    public List<String> getSupportedChartTypes() {
        return Arrays.asList(
            "LINE", "BAR", "PIE", "AREA", "SCATTER", 
            "COLUMN", "DONUT", "RADAR", "HEATMAP", "GAUGE"
        );
    }

    @Override
    public byte[] exportReport(String reportId, String format) {
        // 模拟报表导出
        String content = "报表导出内容 - ID: " + reportId + ", 格式: " + format;
        return content.getBytes();
    }

    @Override
    public List<ReportData> getReportHistory(String reportType, int limit) {
        return reportHistory.stream()
                .filter(report -> reportType == null || reportType.equals(report.getReportType()))
                .sorted((r1, r2) -> r2.getGeneratedAt().compareTo(r1.getGeneratedAt()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getRealTimeSnapshot() {
        Map<String, Object> snapshot = new HashMap<>();
        
        snapshot.put("timestamp", LocalDateTime.now());
        snapshot.put("onlineDevices", 142 + (int)(Math.random() * 10));
        snapshot.put("activeUsers", 2847 + (int)(Math.random() * 100));
        snapshot.put("todayDisposals", 1284 + (int)(Math.random() * 50));
        snapshot.put("currentAlerts", 3 + (int)(Math.random() * 5));
        snapshot.put("systemLoad", 65.5 + Math.random() * 20);
        snapshot.put("networkLatency", 45 + Math.random() * 30);
        snapshot.put("dataProcessingRate", 1250 + Math.random() * 200);
        
        return snapshot;
    }

    @Override
    public Map<String, Object> getMultiDimensionalAnalysis(List<String> dimensions, 
                                                          List<String> metrics, 
                                                          Map<String, Object> filters) {
        Map<String, Object> analysis = new HashMap<>();
        
        analysis.put("dimensions", dimensions);
        analysis.put("metrics", metrics);
        analysis.put("filters", filters);
        analysis.put("analysisTime", LocalDateTime.now());
        
        // 生成多维度分析数据
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Map<String, Object> dataPoint = new HashMap<>();
            for (String dimension : dimensions) {
                dataPoint.put(dimension, generateDimensionValue(dimension, i));
            }
            for (String metric : metrics) {
                dataPoint.put(metric, generateMetricValue(metric, i));
            }
            data.add(dataPoint);
        }
        
        analysis.put("data", data);
        analysis.put("summary", generateMultiDimensionalSummary(dimensions, metrics, data));
        
        return analysis;
    }

    // 私有辅助方法

    private Map<String, Object> generateDisposalDailyData(LocalDateTime date) {
        Map<String, Object> data = new HashMap<>();
        
        data.put("totalDisposals", 1284 + (int)(Math.random() * 200));
        data.put("kitchenWaste", 456 + (int)(Math.random() * 100));
        data.put("recyclableWaste", 328 + (int)(Math.random() * 80));
        data.put("hazardousWaste", 45 + (int)(Math.random() * 20));
        data.put("otherWaste", 455 + (int)(Math.random() * 100));
        data.put("totalWeight", 2847.5 + Math.random() * 500);
        data.put("averageWeight", 2.2 + Math.random() * 0.8);
        data.put("recycleRate", 78.5 + Math.random() * 10);
        data.put("classificationAccuracy", 89.2 + Math.random() * 8);
        
        // 按小时分布
        List<Map<String, Object>> hourlyData = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++) {
            Map<String, Object> hourData = new HashMap<>();
            hourData.put("hour", hour);
            hourData.put("disposals", (int)(Math.random() * 100) + 20);
            hourData.put("weight", Math.random() * 200 + 50);
            hourlyData.add(hourData);
        }
        data.put("hourlyDistribution", hourlyData);
        
        return data;
    }

    private Map<String, Object> generateCollectionDailyData(LocalDateTime date) {
        Map<String, Object> data = new HashMap<>();
        
        data.put("totalCollections", 156 + (int)(Math.random() * 30));
        data.put("completedCollections", 142 + (int)(Math.random() * 10));
        data.put("collectionEfficiency", 91.2 + Math.random() * 5);
        data.put("averageCollectionTime", 45.5 + Math.random() * 15);
        data.put("totalDistance", 1245.8 + Math.random() * 200);
        data.put("fuelConsumption", 89.5 + Math.random() * 20);
        data.put("vehicleUtilization", 87.3 + Math.random() * 8);
        
        return data;
    }

    private Map<String, Object> generateDeviceDailyData(LocalDateTime date) {
        Map<String, Object> data = new HashMap<>();
        
        data.put("totalDevices", 156);
        data.put("onlineDevices", 142 + (int)(Math.random() * 10));
        data.put("offlineDevices", 14 - (int)(Math.random() * 10));
        data.put("deviceUptime", 94.2 + Math.random() * 4);
        data.put("maintenanceAlerts", 3 + (int)(Math.random() * 5));
        data.put("errorCount", 8 + (int)(Math.random() * 10));
        data.put("dataTransmissionRate", 98.5 + Math.random() * 1.5);
        
        return data;
    }

    private Map<String, Object> generateUserDailyData(LocalDateTime date) {
        Map<String, Object> data = new HashMap<>();
        
        data.put("totalUsers", 12456);
        data.put("activeUsers", 2847 + (int)(Math.random() * 200));
        data.put("newRegistrations", 23 + (int)(Math.random() * 15));
        data.put("averagePointsEarned", 15.6 + Math.random() * 5);
        data.put("topUserPoints", 4856 + (int)(Math.random() * 200));
        data.put("userSatisfaction", 92.8 + Math.random() * 5);
        
        return data;
    }

    private Map<String, Object> generateOverallDailyData(LocalDateTime date) {
        Map<String, Object> data = new HashMap<>();
        
        data.put("systemUptime", 99.8 + Math.random() * 0.2);
        data.put("totalTransactions", 3456 + (int)(Math.random() * 500));
        data.put("dataProcessed", 15.6 + Math.random() * 5); // GB
        data.put("alertsGenerated", 12 + (int)(Math.random() * 8));
        data.put("alertsResolved", 9 + (int)(Math.random() * 5));
        data.put("systemLoad", 65.5 + Math.random() * 20);
        
        return data;
    }

    private Map<String, Object> generateMonthlyData(int year, int month, String reportType) {
        Map<String, Object> data = new HashMap<>();
        
        // 基础月度数据
        data.put("totalDisposals", 38520 + (int)(Math.random() * 5000));
        data.put("totalWeight", 85420.5 + Math.random() * 10000);
        data.put("averageDaily", 1284 + (int)(Math.random() * 200));
        data.put("recycleRate", 87.3 + Math.random() * 5);
        data.put("collectionEfficiency", 91.8 + Math.random() * 3);
        data.put("userGrowthRate", 5.2 + Math.random() * 3);
        data.put("costSavings", 15600 + Math.random() * 3000);
        
        // 按天分布
        List<Map<String, Object>> dailyData = new ArrayList<>();
        for (int day = 1; day <= 30; day++) {
            Map<String, Object> dayData = new HashMap<>();
            dayData.put("day", day);
            dayData.put("disposals", 1200 + (int)(Math.random() * 400));
            dayData.put("weight", 2800 + Math.random() * 800);
            dayData.put("efficiency", 85 + Math.random() * 10);
            dailyData.add(dayData);
        }
        data.put("dailyTrend", dailyData);
        
        return data;
    }

    private Map<String, Object> generateYearlyData(int year, String reportType) {
        Map<String, Object> data = new HashMap<>();
        
        data.put("totalDisposals", 462240 + (int)(Math.random() * 50000));
        data.put("totalWeight", 1025046.0 + Math.random() * 100000);
        data.put("averageMonthly", 38520 + (int)(Math.random() * 5000));
        data.put("yearOverYearGrowth", 12.5 + Math.random() * 8);
        data.put("recycleRateImprovement", 8.3 + Math.random() * 5);
        data.put("costSavingsTotal", 187200 + Math.random() * 30000);
        data.put("carbonFootprintReduction", 245.6 + Math.random() * 50);
        
        // 按月分布
        List<Map<String, Object>> monthlyData = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            Map<String, Object> monthData = new HashMap<>();
            monthData.put("month", month);
            monthData.put("disposals", 35000 + (int)(Math.random() * 8000));
            monthData.put("weight", 80000 + Math.random() * 15000);
            monthData.put("efficiency", 88 + Math.random() * 8);
            monthlyData.add(monthData);
        }
        data.put("monthlyTrend", monthlyData);
        
        return data;
    }

    private List<TrendDataPoint> generateTrendDataPoints(String metric, LocalDateTime startDate, LocalDateTime endDate) {
        List<TrendDataPoint> dataPoints = new ArrayList<>();
        
        LocalDateTime current = startDate;
        double baseValue = getBaseValueForMetric(metric);
        double trend = (Math.random() - 0.5) * 0.1; // -5% to +5% trend per period
        
        while (!current.isAfter(endDate)) {
            TrendDataPoint point = new TrendDataPoint();
            point.setTimestamp(current);
            point.setValue(baseValue + (Math.random() - 0.5) * baseValue * 0.2); // ±20% variation
            point.setLabel(current.format(DateTimeFormatter.ofPattern("MM-dd")));
            
            dataPoints.add(point);
            current = current.plusDays(1);
            baseValue *= (1 + trend); // Apply trend
        }
        
        return dataPoints;
    }

    private double getBaseValueForMetric(String metric) {
        switch (metric.toLowerCase()) {
            case "disposalcount": return 1284;
            case "disposalweight": return 2847;
            case "recyclerate": return 87.3;
            case "collectionefficiency": return 91.8;
            case "deviceuptime": return 94.2;
            case "usersatisfaction": return 92.8;
            default: return 100;
        }
    }

    private Map<String, Object> generateTrendInsights(String metric, List<TrendDataPoint> dataPoints, String trend) {
        Map<String, Object> insights = new HashMap<>();
        
        insights.put("dataPointCount", dataPoints.size());
        insights.put("averageValue", dataPoints.stream().mapToDouble(TrendDataPoint::getValue).average().orElse(0));
        insights.put("maxValue", dataPoints.stream().mapToDouble(TrendDataPoint::getValue).max().orElse(0));
        insights.put("minValue", dataPoints.stream().mapToDouble(TrendDataPoint::getValue).min().orElse(0));
        insights.put("volatility", calculateVolatility(dataPoints));
        insights.put("trendStrength", Math.abs(dataPoints.get(dataPoints.size()-1).getValue() - dataPoints.get(0).getValue()));
        
        return insights;
    }

    private String generateTrendSummary(String metric, String trend, Double changeRate) {
        String direction = "";
        String magnitude = "";
        
        switch (trend) {
            case "INCREASING":
                direction = "呈上升趋势";
                break;
            case "DECREASING":
                direction = "呈下降趋势";
                break;
            case "STABLE":
                direction = "保持稳定";
                break;
        }
        
        if (Math.abs(changeRate) > 20) {
            magnitude = "变化幅度较大";
        } else if (Math.abs(changeRate) > 10) {
            magnitude = "变化幅度适中";
        } else {
            magnitude = "变化幅度较小";
        }
        
        return String.format("%s在分析期间%s，变化率为%.1f%%，%s。", 
                           metric, direction, changeRate, magnitude);
    }

    private double calculateVolatility(List<TrendDataPoint> dataPoints) {
        double mean = dataPoints.stream().mapToDouble(TrendDataPoint::getValue).average().orElse(0);
        double variance = dataPoints.stream()
                .mapToDouble(point -> Math.pow(point.getValue() - mean, 2))
                .average().orElse(0);
        return Math.sqrt(variance);
    }

    private double generateMetricValue(String metric, LocalDateTime startDate, LocalDateTime endDate) {
        // 基于时间范围生成模拟指标值
        long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
        double baseValue = getBaseValueForMetric(metric);
        return baseValue * days * (0.8 + Math.random() * 0.4); // ±20% variation
    }

    private String generateComparisonInsights(String metric, double changeRate, double changeAmount) {
        String trend = changeRate > 0 ? "增长" : changeRate < 0 ? "下降" : "持平";
        String magnitude = Math.abs(changeRate) > 20 ? "显著" : Math.abs(changeRate) > 10 ? "明显" : "轻微";
        
        return String.format("%s相比上期%s%s，变化幅度为%.1f%%（%+.1f）。", 
                           metric, magnitude, trend, Math.abs(changeRate), changeAmount);
    }

    private Map<String, Object> executeCustomReportLogic(CustomReport customReport, Map<String, Object> parameters) {
        Map<String, Object> data = new HashMap<>();
        
        // 模拟执行自定义报表逻辑
        data.put("reportName", customReport.getReportName());
        data.put("executionTime", LocalDateTime.now());
        data.put("parameters", parameters);
        data.put("dataFields", customReport.getDataFields());
        data.put("recordCount", 100 + (int)(Math.random() * 500));
        
        // 生成模拟数据
        List<Map<String, Object>> records = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Map<String, Object> record = new HashMap<>();
            if (customReport.getDataFields() != null) {
                for (String field : customReport.getDataFields()) {
                    record.put(field, generateFieldValue(field, i));
                }
            }
            records.add(record);
        }
        data.put("records", records);
        
        return data;
    }

    private Object generateDimensionValue(String dimension, int index) {
        switch (dimension.toLowerCase()) {
            case "region":
                return Arrays.asList("海淀区", "朝阳区", "西城区", "东城区", "丰台区").get(index % 5);
            case "wastetype":
                return Arrays.asList("厨余垃圾", "可回收垃圾", "有害垃圾", "其他垃圾").get(index % 4);
            case "devicetype":
                return Arrays.asList("智能投放点", "收运车辆", "处置设备", "监控设备").get(index % 4);
            case "timeslot":
                return Arrays.asList("早高峰", "上午", "中午", "下午", "晚高峰", "夜间").get(index % 6);
            default:
                return "维度值" + (index + 1);
        }
    }

    private Object generateMetricValue(String metric, int index) {
        switch (metric.toLowerCase()) {
            case "count":
                return 100 + (int)(Math.random() * 200);
            case "weight":
                return 50.5 + Math.random() * 100;
            case "efficiency":
                return 80.0 + Math.random() * 15;
            case "cost":
                return 1000 + Math.random() * 2000;
            default:
                return Math.random() * 100;
        }
    }

    private Object generateFieldValue(String field, int index) {
        switch (field.toLowerCase()) {
            case "disposalcount":
                return 50 + (int)(Math.random() * 100);
            case "disposalweight":
                return 125.5 + Math.random() * 200;
            case "usercount":
                return 20 + (int)(Math.random() * 50);
            case "devicecount":
                return 5 + (int)(Math.random() * 15);
            case "recyclerate":
                return 75.0 + Math.random() * 20;
            case "efficiency":
                return 85.0 + Math.random() * 10;
            default:
                return "数据" + (index + 1);
        }
    }

    private Map<String, Object> generateMultiDimensionalSummary(List<String> dimensions, 
                                                               List<String> metrics, 
                                                               List<Map<String, Object>> data) {
        Map<String, Object> summary = new HashMap<>();
        
        summary.put("totalRecords", data.size());
        summary.put("dimensionCount", dimensions.size());
        summary.put("metricCount", metrics.size());
        
        // 计算各指标的汇总统计
        Map<String, Object> metricSummary = new HashMap<>();
        for (String metric : metrics) {
            List<Double> values = data.stream()
                    .map(record -> (Double) record.get(metric))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            
            if (!values.isEmpty()) {
                Map<String, Object> stats = new HashMap<>();
                stats.put("count", values.size());
                stats.put("sum", values.stream().mapToDouble(Double::doubleValue).sum());
                stats.put("average", values.stream().mapToDouble(Double::doubleValue).average().orElse(0));
                stats.put("max", values.stream().mapToDouble(Double::doubleValue).max().orElse(0));
                stats.put("min", values.stream().mapToDouble(Double::doubleValue).min().orElse(0));
                metricSummary.put(metric, stats);
            }
        }
        summary.put("metricSummary", metricSummary);
        
        return summary;
    }
}