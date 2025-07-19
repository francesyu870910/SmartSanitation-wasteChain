package com.smartwaste.domain.service;

import com.smartwaste.domain.model.RealTimeStatistics;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 实时统计服务接口
 */
public interface RealTimeStatisticsService {
    
    /**
     * 获取实时综合统计数据
     * @return 实时统计数据
     */
    RealTimeStatistics getRealTimeStatistics();
    
    /**
     * 获取投放统计数据
     * @param timeRange 时间范围
     * @return 投放统计
     */
    RealTimeStatistics getDisposalStatistics(String timeRange);
    
    /**
     * 获取收运统计数据
     * @param timeRange 时间范围
     * @return 收运统计
     */
    RealTimeStatistics getCollectionStatistics(String timeRange);
    
    /**
     * 获取处置统计数据
     * @param timeRange 时间范围
     * @return 处置统计
     */
    RealTimeStatistics getProcessingStatistics(String timeRange);
    
    /**
     * 获取违规统计数据
     * @param timeRange 时间范围
     * @return 违规统计
     */
    RealTimeStatistics getViolationStatistics(String timeRange);
    
    /**
     * 获取设备状态统计
     * @return 设备统计
     */
    RealTimeStatistics getDeviceStatistics();
    
    /**
     * 获取用户活跃度统计
     * @param timeRange 时间范围
     * @return 用户统计
     */
    RealTimeStatistics getUserStatistics(String timeRange);
    
    /**
     * 获取环保指标统计
     * @param timeRange 时间范围
     * @return 环保统计
     */
    RealTimeStatistics getEnvironmentalStatistics(String timeRange);
    
    /**
     * 计算投放量指标
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 投放量统计
     */
    Map<String, Object> calculateDisposalMetrics(LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 计算收运效率指标
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 收运效率统计
     */
    Map<String, Object> calculateCollectionEfficiency(LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 计算处置率指标
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 处置率统计
     */
    Map<String, Object> calculateProcessingRate(LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 计算违规率指标
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 违规率统计
     */
    Map<String, Object> calculateViolationRate(LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 获取关键性能指标(KPI)
     * @return KPI指标
     */
    Map<String, Object> getKeyPerformanceIndicators();
    
    /**
     * 获取实时告警信息
     * @return 告警列表
     */
    List<Map<String, Object>> getRealTimeAlerts();
    
    /**
     * 获取趋势分析数据
     * @param metric 指标名称
     * @param timeRange 时间范围
     * @param granularity 粒度（HOUR, DAY, WEEK, MONTH）
     * @return 趋势数据
     */
    List<Map<String, Object>> getTrendAnalysis(String metric, String timeRange, String granularity);
    
    /**
     * 获取热力图数据
     * @param metric 指标名称
     * @param timeRange 时间范围
     * @return 热力图数据
     */
    Map<String, Object> getHeatmapData(String metric, String timeRange);
    
    /**
     * 获取排行榜数据
     * @param category 分类（USER, LOCATION, DEVICE）
     * @param metric 指标名称
     * @param limit 限制数量
     * @return 排行榜数据
     */
    List<Map<String, Object>> getRankingData(String category, String metric, int limit);
    
    /**
     * 刷新统计缓存
     */
    void refreshStatisticsCache();
    
    /**
     * 获取系统健康状态
     * @return 系统健康状态
     */
    Map<String, Object> getSystemHealthStatus();
    
    /**
     * 获取预测分析数据
     * @param metric 指标名称
     * @param forecastDays 预测天数
     * @return 预测数据
     */
    List<Map<String, Object>> getForecastData(String metric, int forecastDays);
}