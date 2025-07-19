package com.smartwaste.integration.service;

import java.util.Map;

/**
 * 服务集成接口
 */
public interface ServiceIntegrationService {
    
    /**
     * 同步用户数据到各个服务
     * @param userId 用户ID
     * @param userData 用户数据
     */
    void syncUserData(String userId, Map<String, Object> userData);
    
    /**
     * 同步设备数据到各个服务
     * @param deviceId 设备ID
     * @param deviceData 设备数据
     */
    void syncDeviceData(String deviceId, Map<String, Object> deviceData);
    
    /**
     * 处理垃圾投放事件
     * @param disposalEvent 投放事件数据
     */
    void handleDisposalEvent(Map<String, Object> disposalEvent);
    
    /**
     * 处理收运完成事件
     * @param collectionEvent 收运事件数据
     */
    void handleCollectionEvent(Map<String, Object> collectionEvent);
    
    /**
     * 处理违规检测事件
     * @param violationEvent 违规事件数据
     */
    void handleViolationEvent(Map<String, Object> violationEvent);
    
    /**
     * 发送通知
     * @param notificationType 通知类型
     * @param recipient 接收者
     * @param content 通知内容
     * @return 发送结果
     */
    Map<String, Object> sendNotification(String notificationType, String recipient, Map<String, Object> content);
    
    /**
     * 获取服务健康状态
     * @return 服务状态信息
     */
    Map<String, Object> getServiceHealthStatus();
    
    /**
     * 执行服务间数据同步
     */
    void performDataSync();
}