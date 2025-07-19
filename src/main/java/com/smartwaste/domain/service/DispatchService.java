package com.smartwaste.domain.service;

import com.smartwaste.domain.model.*;
import java.util.List;
import java.util.Optional;

/**
 * 智能调度服务接口
 * Smart Dispatch Service Interface
 */
public interface DispatchService {
    
    /**
     * 创建调度任务
     * @param wasteType 垃圾类型
     * @param containerIds 容器ID列表
     * @param priority 优先级 (1-5)
     * @return 创建的调度任务
     */
    DispatchTask createDispatchTask(WasteType wasteType, List<String> containerIds, Integer priority);
    
    /**
     * 为调度任务智能匹配车辆
     * @param taskId 任务ID
     * @return 匹配的车辆结果列表，按匹配度排序
     */
    List<VehicleMatchResult> matchVehiclesForTask(String taskId);
    
    /**
     * 分配车辆到调度任务
     * @param taskId 任务ID
     * @param vehicleId 车辆ID
     * @return 更新后的调度任务
     */
    DispatchTask assignVehicleToTask(String taskId, String vehicleId);
    
    /**
     * 验证车辆和垃圾类型匹配
     * @param vehicleId 车辆ID
     * @param wasteType 垃圾类型
     * @return 是否匹配
     */
    boolean validateVehicleWasteTypeMatch(String vehicleId, WasteType wasteType);
    
    /**
     * 检查车辆容量约束
     * @param vehicleId 车辆ID
     * @param estimatedWeight 预估重量
     * @return 是否满足容量约束
     */
    boolean checkVehicleCapacityConstraint(String vehicleId, Double estimatedWeight);
    
    /**
     * 获取待分配的任务列表
     * @return 待分配任务列表，按优先级排序
     */
    List<DispatchTask> getPendingTasks();
    
    /**
     * 获取指定车辆的当前任务
     * @param vehicleId 车辆ID
     * @return 当前任务列表
     */
    List<DispatchTask> getCurrentTasksForVehicle(String vehicleId);
    
    /**
     * 更新任务状态
     * @param taskId 任务ID
     * @param status 新状态
     * @return 更新后的任务
     */
    DispatchTask updateTaskStatus(String taskId, DispatchStatus status);
    
    /**
     * 取消调度任务
     * @param taskId 任务ID
     * @param reason 取消原因
     * @return 取消后的任务
     */
    DispatchTask cancelTask(String taskId, String reason);
    
    /**
     * 根据ID查找调度任务
     * @param taskId 任务ID
     * @return 调度任务
     */
    Optional<DispatchTask> findTaskById(String taskId);
    
    /**
     * 自动分配算法 - 为所有待分配任务自动匹配最优车辆
     * @return 成功分配的任务数量
     */
    int autoAssignTasks();
    
    /**
     * 计算车辆匹配度评分
     * @param vehicle 车辆信息
     * @param task 调度任务
     * @return 匹配度评分 (0-100)
     */
    Double calculateMatchScore(Vehicle vehicle, DispatchTask task);
    
    /**
     * 优化收运路线
     * @param vehicleId 车辆ID
     * @param containerIds 容器ID列表
     * @return 优化后的路线
     */
    RouteOptimization optimizeRoute(String vehicleId, List<String> containerIds);
    
    /**
     * 更新任务状态（使用TaskStatus）
     * @param taskId 任务ID
     * @param status 新状态
     * @return 更新后的任务
     */
    DispatchTask updateTaskStatus(String taskId, TaskStatus status);
    
    /**
     * 根据司机ID和状态获取任务列表
     * @param driverId 司机ID
     * @param status 任务状态
     * @return 任务列表
     */
    List<DispatchTask> getTasksByDriverAndStatus(String driverId, TaskStatus status);
}