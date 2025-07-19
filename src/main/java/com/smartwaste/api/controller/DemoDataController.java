package com.smartwaste.api.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 演示数据控制器
 * 提供前端页面所需的模拟数据
 */
@RestController
@RequestMapping("/api/demo")
@CrossOrigin(origins = "*")
public class DemoDataController {

    /**
     * 获取仪表板统计数据
     */
    @GetMapping("/dashboard/stats")
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("onlineDevices", 142);
        stats.put("activeUsers", 1256);
        stats.put("unresolvedAlerts", 8);
        stats.put("todayDisposals", 347);
        return stats;
    }

    /**
     * 获取最新报警信息
     */
    @GetMapping("/dashboard/recent-alerts")
    public List<Map<String, Object>> getRecentAlerts() {
        List<Map<String, Object>> alerts = new ArrayList<>();
        
        Map<String, Object> alert1 = new HashMap<>();
        alert1.put("alertId", "ALT001");
        alert1.put("containerId", "BIN001");
        alert1.put("alertLevel", "HIGH");
        alert1.put("fullnessLevel", 0.92);
        alert1.put("alertTime", LocalDateTime.now().minusMinutes(15));
        alert1.put("isResolved", false);
        alerts.add(alert1);
        
        Map<String, Object> alert2 = new HashMap<>();
        alert2.put("alertId", "ALT002");
        alert2.put("containerId", "BIN005");
        alert2.put("alertLevel", "MEDIUM");
        alert2.put("fullnessLevel", 0.78);
        alert2.put("alertTime", LocalDateTime.now().minusMinutes(32));
        alert2.put("isResolved", false);
        alerts.add(alert2);
        
        Map<String, Object> alert3 = new HashMap<>();
        alert3.put("alertId", "ALT003");
        alert3.put("containerId", "BIN012");
        alert3.put("alertLevel", "CRITICAL");
        alert3.put("fullnessLevel", 0.95);
        alert3.put("alertTime", LocalDateTime.now().minusMinutes(5));
        alert3.put("isResolved", false);
        alerts.add(alert3);
        
        return alerts;
    }

    /**
     * 获取设备列表
     */
    @GetMapping("/devices")
    public List<Map<String, Object>> getDevices() {
        List<Map<String, Object>> devices = new ArrayList<>();
        
        String[] deviceTypes = {"SMART_BIN", "FACE_RECOGNITION", "QR_SCANNER", "IC_CARD_READER", "WEIGHT_SENSOR"};
        String[] statuses = {"ONLINE", "OFFLINE", "MAINTENANCE"};
        String[] locations = {"小区A栋", "小区B栋", "商业街", "学校门口", "公园入口"};
        
        for (int i = 1; i <= 15; i++) {
            Map<String, Object> device = new HashMap<>();
            device.put("deviceId", "DEV" + String.format("%03d", i));
            device.put("deviceName", "设备" + i);
            device.put("deviceType", deviceTypes[i % deviceTypes.length]);
            device.put("location", locations[i % locations.length]);
            device.put("status", statuses[i % statuses.length]);
            device.put("ipAddress", "192.168.1." + (100 + i));
            device.put("lastHeartbeat", LocalDateTime.now().minusMinutes(i * 2));
            devices.add(device);
        }
        
        return devices;
    }

    /**
     * 获取垃圾桶列表
     */
    @GetMapping("/containers")
    public List<Map<String, Object>> getContainers() {
        List<Map<String, Object>> containers = new ArrayList<>();
        
        String[] wasteTypes = {"KITCHEN", "RECYCLABLE", "HAZARDOUS", "OTHER"};
        String[] locations = {"小区A栋楼下", "小区B栋楼下", "商业街中段", "学校门口", "公园入口", "办公楼前"};
        Random random = new Random();
        
        for (int i = 1; i <= 12; i++) {
            Map<String, Object> container = new HashMap<>();
            container.put("containerId", "BIN" + String.format("%03d", i));
            container.put("location", locations[i % locations.length]);
            container.put("wasteType", wasteTypes[i % wasteTypes.length]);
            container.put("currentFullness", Math.round(random.nextDouble() * 100) / 100.0);
            container.put("capacity", 120.0);
            container.put("lastUpdated", LocalDateTime.now().minusMinutes(random.nextInt(60)));
            containers.add(container);
        }
        
        return containers;
    }

    /**
     * 获取报警列表
     */
    @GetMapping("/alerts")
    public List<Map<String, Object>> getAlerts() {
        return getRecentAlerts(); // 复用最新报警数据
    }

    /**
     * 获取用户列表
     */
    @GetMapping("/users")
    public List<Map<String, Object>> getUsers() {
        List<Map<String, Object>> users = new ArrayList<>();
        
        String[] names = {"张三", "李四", "王五", "赵六", "钱七", "孙八", "周九", "吴十"};
        String[] addresses = {"小区A栋101", "小区B栋202", "商业街15号", "学校宿舍", "公园路88号"};
        
        for (int i = 1; i <= 20; i++) {
            Map<String, Object> user = new HashMap<>();
            user.put("userId", "USER" + String.format("%03d", i));
            user.put("name", names[i % names.length] + i);
            user.put("phone", "138" + String.format("%08d", 10000000 + i));
            user.put("address", addresses[i % addresses.length]);
            user.put("idCard", "110101199001010" + String.format("%03d", i));
            user.put("isActive", i % 10 != 0); // 90%的用户是活跃的
            users.add(user);
        }
        
        return users;
    }

    /**
     * 获取车辆列表
     */
    @GetMapping("/vehicles")
    public List<Map<String, Object>> getVehicles() {
        List<Map<String, Object>> vehicles = new ArrayList<>();
        
        String[] vehicleTypes = {"KITCHEN", "RECYCLABLE", "HAZARDOUS", "OTHER"};
        String[] statuses = {"IDLE", "COLLECTING", "TRANSPORTING", "MAINTENANCE"};
        String[] locations = {"小区A区域", "小区B区域", "商业街", "学校区域", "公园区域"};
        
        for (int i = 1; i <= 8; i++) {
            Map<String, Object> vehicle = new HashMap<>();
            vehicle.put("vehicleId", "VEH" + String.format("%03d", i));
            vehicle.put("licensePlate", "京A" + String.format("%05d", 10000 + i));
            vehicle.put("vehicleType", vehicleTypes[i % vehicleTypes.length]);
            vehicle.put("capacity", 5.0 + (i % 3) * 2.5); // 5吨、7.5吨、10吨
            vehicle.put("driverId", "DRV" + String.format("%03d", i));
            vehicle.put("currentLocation", locations[i % locations.length]);
            vehicle.put("status", statuses[i % statuses.length]);
            vehicles.add(vehicle);
        }
        
        return vehicles;
    }

    /**
     * 获取积分排行榜
     */
    @GetMapping("/points/ranking")
    public List<Map<String, Object>> getPointsRanking() {
        List<Map<String, Object>> ranking = new ArrayList<>();
        
        String[] levelNames = {"环保达人", "绿色先锋", "分类专家", "环保新手"};
        Random random = new Random();
        
        for (int i = 1; i <= 10; i++) {
            Map<String, Object> user = new HashMap<>();
            user.put("userId", "USER" + String.format("%03d", i));
            user.put("totalPoints", 5000 - i * 200 + random.nextInt(100));
            user.put("levelName", levelNames[Math.min(i / 3, levelNames.length - 1)]);
            ranking.add(user);
        }
        
        return ranking;
    }

    /**
     * 解决报警
     */
    @PostMapping("/alerts/{alertId}/resolve")
    public Map<String, Object> resolveAlert(@PathVariable String alertId) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "报警 " + alertId + " 已成功解决");
        result.put("resolvedAt", LocalDateTime.now());
        return result;
    }

    /**
     * 批量检查垃圾桶满溢状态
     */
    @PostMapping("/containers/batch-check")
    public Map<String, Object> batchCheckFullness() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("checkedContainers", 12);
        result.put("newAlerts", 2);
        result.put("message", "批量检查完成");
        return result;
    }

    /**
     * 更新设备心跳
     */
    @PostMapping("/devices/{deviceId}/heartbeat")
    public Map<String, Object> updateDeviceHeartbeat(@PathVariable String deviceId) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("deviceId", deviceId);
        result.put("lastHeartbeat", LocalDateTime.now());
        result.put("message", "设备心跳更新成功");
        return result;
    }
}