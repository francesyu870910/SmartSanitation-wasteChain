package com.smartwaste.integration;

import com.smartwaste.integration.service.ServiceIntegrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 服务集成测试
 */
@SpringBootTest
@ActiveProfiles("test")
public class ServiceIntegrationTest {
    
    @Autowired
    private ServiceIntegrationService serviceIntegrationService;
    
    @Test
    public void testSyncUserData() {
        // 测试用户数据同步
        String userId = "test_user_001";
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", "测试用户");
        userData.put("phone", "13800138000");
        userData.put("address", "测试地址");
        
        assertDoesNotThrow(() -> {
            serviceIntegrationService.syncUserData(userId, userData);
        });
    }
    
    @Test
    public void testSyncDeviceData() {
        // 测试设备数据同步
        String deviceId = "test_device_001";
        Map<String, Object> deviceData = new HashMap<>();
        deviceData.put("status", "ONLINE");
        deviceData.put("location", "测试位置");
        deviceData.put("type", "SMART_BIN");
        
        assertDoesNotThrow(() -> {
            serviceIntegrationService.syncDeviceData(deviceId, deviceData);
        });
    }
    
    @Test
    public void testHandleDisposalEvent() {
        // 测试处理投放事件
        Map<String, Object> disposalEvent = new HashMap<>();
        disposalEvent.put("userId", "test_user_001");
        disposalEvent.put("wasteType", "厨余垃圾");
        disposalEvent.put("weight", 2.5);
        disposalEvent.put("containerId", "container_001");
        disposalEvent.put("location", "小区A");
        
        assertDoesNotThrow(() -> {
            serviceIntegrationService.handleDisposalEvent(disposalEvent);
        });
    }
    
    @Test
    public void testHandleCollectionEvent() {
        // 测试处理收运事件
        Map<String, Object> collectionEvent = new HashMap<>();
        collectionEvent.put("wasteId", "waste_001");
        collectionEvent.put("vehicleId", "vehicle_001");
        collectionEvent.put("driverId", "driver_001");
        collectionEvent.put("route", "路线A");
        
        assertDoesNotThrow(() -> {
            serviceIntegrationService.handleCollectionEvent(collectionEvent);
        });
    }
    
    @Test
    public void testHandleViolationEvent() {
        // 测试处理违规事件
        Map<String, Object> violationEvent = new HashMap<>();
        violationEvent.put("userId", "test_user_001");
        violationEvent.put("violationType", "WRONG_CLASSIFICATION");
        violationEvent.put("violationId", "violation_001");
        violationEvent.put("location", "小区A");
        
        assertDoesNotThrow(() -> {
            serviceIntegrationService.handleViolationEvent(violationEvent);
        });
    }
    
    @Test
    public void testSendNotification() {
        // 测试发送通知
        Map<String, Object> content = new HashMap<>();
        content.put("message", "测试通知");
        content.put("timestamp", System.currentTimeMillis());
        
        Map<String, Object> result = serviceIntegrationService.sendNotification(
                "SMS", "test_user_001", content);
        
        assertNotNull(result);
        assertTrue(result.containsKey("success"));
    }
    
    @Test
    public void testGetServiceHealthStatus() {
        // 测试获取服务健康状态
        Map<String, Object> healthStatus = serviceIntegrationService.getServiceHealthStatus();
        
        assertNotNull(healthStatus);
        assertTrue(healthStatus.containsKey("overallStatus"));
        assertTrue(healthStatus.containsKey("timestamp"));
    }
    
    @Test
    public void testPerformDataSync() {
        // 测试执行数据同步
        assertDoesNotThrow(() -> {
            serviceIntegrationService.performDataSync();
        });
    }
}