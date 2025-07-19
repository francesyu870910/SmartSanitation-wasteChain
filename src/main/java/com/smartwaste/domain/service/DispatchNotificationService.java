package com.smartwaste.domain.service;

import com.smartwaste.domain.model.*;
import java.util.List;
import java.util.Optional;

/**
 * 调度通知推送服务接口
 * Dispatch Notification Service Interface
 */
public interface DispatchNotificationService {
    
    /**
     * 推送调度方案给司机
     * @param driverId 司机ID
     * @param dispatchPlan 调度方案
     * @return 推送结果
     */
    NotificationResult pushDispatchPlanToDriver(String driverId, DispatchPlan dispatchPlan);
    
    /**
     * 推送调度方案给管理人员
     * @param managerId 管理员ID
     * @param dispatchPlan 调度方案
     * @return 推送结果
     */
    NotificationResult pushDispatchPlanToManager(String managerId, DispatchPlan dispatchPlan);
    
    /**
     * 批量推送调度方案
     * @param dispatchPlan 调度方案
     * @return 推送结果列表
     */
    List<NotificationResult> broadcastDispatchPlan(DispatchPlan dispatchPlan);
    
    /**
     * 司机确认调度任务
     * @param taskId 任务ID
     * @param driverId 司机ID
     * @param confirmationType 确认类型
     * @return 确认结果
     */
    TaskConfirmationResult confirmTask(String taskId, String driverId, TaskConfirmationType confirmationType);
    
    /**
     * 更新任务状态
     * @param taskId 任务ID
     * @param status 新状态
     * @param updateBy 更新人
     * @param remarks 备注
     * @return 更新结果
     */
    TaskStatusUpdateResult updateTaskStatus(String taskId, TaskStatus status, String updateBy, String remarks);
    
    /**
     * 获取司机的待确认任务
     * @param driverId 司机ID
     * @return 待确认任务列表
     */
    List<DispatchTask> getPendingTasksForDriver(String driverId);
    
    /**
     * 获取任务状态历史
     * @param taskId 任务ID
     * @return 状态历史列表
     */
    List<TaskStatusHistory> getTaskStatusHistory(String taskId);
    
    /**
     * 发送任务提醒
     * @param taskId 任务ID
     * @param reminderType 提醒类型
     * @return 提醒结果
     */
    NotificationResult sendTaskReminder(String taskId, ReminderType reminderType);
    
    /**
     * 获取推送统计信息
     * @return 统计信息
     */
    NotificationStatistics getNotificationStatistics();
    
    /**
     * 根据ID查找通知记录
     * @param notificationId 通知ID
     * @return 通知记录
     */
    Optional<NotificationRecord> findNotificationById(String notificationId);
    
    /**
     * 获取用户的通知历史
     * @param userId 用户ID
     * @param limit 限制数量
     * @return 通知历史列表
     */
    List<NotificationRecord> getNotificationHistory(String userId, int limit);
    
    /**
     * 标记通知为已读
     * @param notificationId 通知ID
     * @param userId 用户ID
     * @return 操作结果
     */
    boolean markNotificationAsRead(String notificationId, String userId);
    
    /**
     * 任务确认类型枚举
     */
    enum TaskConfirmationType {
        ACCEPT("接受"),
        REJECT("拒绝"),
        REQUEST_CHANGE("请求修改");
        
        private final String displayName;
        
        TaskConfirmationType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    /**
     * 提醒类型枚举
     */
    enum ReminderType {
        TASK_START("任务开始提醒"),
        TASK_OVERDUE("任务超时提醒"),
        ROUTE_CHANGE("路线变更提醒"),
        EMERGENCY("紧急通知");
        
        private final String displayName;
        
        ReminderType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
}