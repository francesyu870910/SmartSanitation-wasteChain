package com.smartwaste.domain.service;

import com.smartwaste.domain.model.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 环保指标监控服务接口
 * Environmental Monitoring Service Interface
 */
public interface EnvironmentalMonitoringService {
    
    /**
     * 记录环保数据
     * @param facilityId 处置场ID
     * @param data 环保数据
     * @return 记录结果
     */
    EnvironmentalDataRecord recordEnvironmentalData(String facilityId, EnvironmentalData data);
    
    /**
     * 获取实时环保指标
     * @param facilityId 处置场ID
     * @return 实时环保指标
     */
    RealTimeEnvironmentalMetrics getRealTimeMetrics(String facilityId);
    
    /**
     * 检查环保指标超标情况
     * @param facilityId 处置场ID
     * @return 超标检查结果
     */
    List<EnvironmentalAlert> checkEnvironmentalStandards(String facilityId);
    
    /**
     * 创建环保预警
     * @param facilityId 处置场ID
     * @param alertType 预警类型
     * @param severity 严重程度
     * @param message 预警消息
     * @param currentValue 当前值
     * @param standardValue 标准值
     * @return 创建的预警
     */
    EnvironmentalAlert createEnvironmentalAlert(String facilityId, AlertType alertType, 
                                              AlertSeverity severity, String message, 
                                              double currentValue, double standardValue);
    
    /**
     * 解决环保预警
     * @param alertId 预警ID
     * @param resolvedBy 解决人
     * @param resolutionAction 解决措施
     * @return 更新后的预警
     */
    EnvironmentalAlert resolveEnvironmentalAlert(String alertId, String resolvedBy, String resolutionAction);
    
    /**
     * 获取环保数据趋势分析
     * @param facilityId 处置场ID
     * @param indicatorType 指标类型
     * @param period 分析周期
     * @return 趋势分析结果
     */
    EnvironmentalTrendAnalysis getTrendAnalysis(String facilityId, IndicatorType indicatorType, AnalysisPeriod period);
    
    /**
     * 生成环保合规报告
     * @param facilityId 处置场ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 合规报告
     */
    EnvironmentalComplianceReport generateComplianceReport(String facilityId, LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 获取环保指标历史数据
     * @param facilityId 处置场ID
     * @param indicatorType 指标类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 历史数据列表
     */
    List<EnvironmentalDataRecord> getHistoricalData(String facilityId, IndicatorType indicatorType, 
                                                   LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 计算环保指标统计
     * @param facilityId 处置场ID
     * @param period 统计周期
     * @return 统计结果
     */
    EnvironmentalStatistics calculateEnvironmentalStatistics(String facilityId, StatisticsPeriod period);
    
    /**
     * 获取未解决的环保预警
     * @param facilityId 处置场ID
     * @return 未解决预警列表
     */
    List<EnvironmentalAlert> getUnresolvedAlerts(String facilityId);
    
    /**
     * 获取高优先级环保预警
     * @return 高优先级预警列表
     */
    List<EnvironmentalAlert> getHighPriorityAlerts();
    
    /**
     * 设置环保标准阈值
     * @param facilityId 处置场ID
     * @param standards 环保标准配置
     * @return 设置结果
     */
    boolean setEnvironmentalStandards(String facilityId, EnvironmentalStandards standards);
    
    /**
     * 获取环保标准配置
     * @param facilityId 处置场ID
     * @return 环保标准配置
     */
    Optional<EnvironmentalStandards> getEnvironmentalStandards(String facilityId);
    
    /**
     * 预测环保指标趋势
     * @param facilityId 处置场ID
     * @param indicatorType 指标类型
     * @param forecastHours 预测小时数
     * @return 预测结果
     */
    EnvironmentalForecast forecastEnvironmentalTrend(String facilityId, IndicatorType indicatorType, int forecastHours);
    
    /**
     * 环保指标类型枚举
     */
    enum IndicatorType {
        AIR_QUALITY("空气质量"),
        WATER_QUALITY("水质"),
        NOISE_LEVEL("噪声"),
        DUST_LEVEL("粉尘"),
        TEMPERATURE("温度"),
        HUMIDITY("湿度"),
        SO2("二氧化硫"),
        NOX("氮氧化物"),
        PM25("PM2.5"),
        PM10("PM10"),
        COD("化学需氧量"),
        PH("pH值"),
        HEAVY_METALS("重金属");
        
        private final String displayName;
        
        IndicatorType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    /**
     * 预警类型枚举
     */
    enum AlertType {
        EXCEEDS_STANDARD("超标预警"),
        APPROACHING_LIMIT("接近限值"),
        EQUIPMENT_MALFUNCTION("设备故障"),
        DATA_ANOMALY("数据异常"),
        TREND_WARNING("趋势预警");
        
        private final String displayName;
        
        AlertType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    /**
     * 预警严重程度枚举
     */
    enum AlertSeverity {
        CRITICAL("严重"),
        HIGH("高"),
        MEDIUM("中"),
        LOW("低");
        
        private final String displayName;
        
        AlertSeverity(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    /**
     * 分析周期枚举
     */
    enum AnalysisPeriod {
        HOURLY("小时"),
        DAILY("日"),
        WEEKLY("周"),
        MONTHLY("月"),
        YEARLY("年");
        
        private final String displayName;
        
        AnalysisPeriod(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    /**
     * 统计周期枚举
     */
    enum StatisticsPeriod {
        TODAY("今日"),
        YESTERDAY("昨日"),
        THIS_WEEK("本周"),
        LAST_WEEK("上周"),
        THIS_MONTH("本月"),
        LAST_MONTH("上月");
        
        private final String displayName;
        
        StatisticsPeriod(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
}