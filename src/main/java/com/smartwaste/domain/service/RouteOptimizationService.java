package com.smartwaste.domain.service;

import com.smartwaste.domain.model.RouteOptimization;
import com.smartwaste.domain.model.RoutePoint;
import java.util.List;

/**
 * 路线优化服务接口
 * Route Optimization Service Interface
 */
public interface RouteOptimizationService {
    
    /**
     * 优化收运路线
     * @param vehicleId 车辆ID
     * @param containerIds 容器ID列表
     * @return 优化后的路线
     */
    RouteOptimization optimizeRoute(String vehicleId, List<String> containerIds);
    
    /**
     * 使用遗传算法优化路线
     * @param vehicleId 车辆ID
     * @param containerIds 容器ID列表
     * @return 优化后的路线
     */
    RouteOptimization optimizeRouteWithGeneticAlgorithm(String vehicleId, List<String> containerIds);
    
    /**
     * 使用蚁群算法优化路线
     * @param vehicleId 车辆ID
     * @param containerIds 容器ID列表
     * @return 优化后的路线
     */
    RouteOptimization optimizeRouteWithAntColonyOptimization(String vehicleId, List<String> containerIds);
    
    /**
     * 计算两点间距离
     * @param point1 起点
     * @param point2 终点
     * @return 距离（公里）
     */
    Double calculateDistance(RoutePoint point1, RoutePoint point2);
    
    /**
     * 计算路线总距离
     * @param route 路线点列表
     * @return 总距离（公里）
     */
    Double calculateTotalDistance(List<RoutePoint> route);
    
    /**
     * 估算行驶时间
     * @param distance 距离（公里）
     * @param averageSpeed 平均速度（公里/小时）
     * @return 时间（分钟）
     */
    Double estimateTravelTime(Double distance, Double averageSpeed);
    
    /**
     * 验证路线约束条件
     * @param vehicleId 车辆ID
     * @param route 路线
     * @return 是否满足约束条件
     */
    boolean validateRouteConstraints(String vehicleId, List<RoutePoint> route);
    
    /**
     * 获取容器位置信息
     * @param containerIds 容器ID列表
     * @return 路线点列表
     */
    List<RoutePoint> getContainerLocations(List<String> containerIds);
}