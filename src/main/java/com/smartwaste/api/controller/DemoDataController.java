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
        stats.put("onlineDevices", 156);
        stats.put("activeUsers", 2847);
        stats.put("unresolvedAlerts", 3);
        stats.put("todayDisposals", 1284);
        return stats;
    }

    /**
     * 获取最新报警信息
     */
    @GetMapping("/dashboard/recent-alerts")
    public List<Map<String, Object>> getRecentAlerts() {
        List<Map<String, Object>> alerts = new ArrayList<>();
        
        Map<String, Object> alert1 = new HashMap<>();
        alert1.put("alertId", "ALT20250719001");
        alert1.put("containerId", "HY-CBD-001");
        alert1.put("alertLevel", "CRITICAL");
        alert1.put("fullnessLevel", 0.96);
        alert1.put("alertTime", LocalDateTime.now().minusMinutes(8));
        alert1.put("isResolved", false);
        alert1.put("location", "海淀区中关村大街1号院");
        alerts.add(alert1);
        
        Map<String, Object> alert2 = new HashMap<>();
        alert2.put("alertId", "ALT20250719002");
        alert2.put("containerId", "CY-RES-015");
        alert2.put("alertLevel", "HIGH");
        alert2.put("fullnessLevel", 0.89);
        alert2.put("alertTime", LocalDateTime.now().minusMinutes(23));
        alert2.put("isResolved", false);
        alert2.put("location", "朝阳区望京SOHO");
        alerts.add(alert2);
        
        Map<String, Object> alert3 = new HashMap<>();
        alert3.put("alertId", "ALT20250719003");
        alert3.put("containerId", "XC-SCH-008");
        alert3.put("alertLevel", "MEDIUM");
        alert3.put("fullnessLevel", 0.82);
        alert3.put("alertTime", LocalDateTime.now().minusMinutes(45));
        alert3.put("isResolved", false);
        alert3.put("location", "西城区北京小学门口");
        alerts.add(alert3);
        
        return alerts;
    }

    /**
     * 获取设备列表
     */
    @GetMapping("/devices")
    public List<Map<String, Object>> getDevices() {
        List<Map<String, Object>> devices = new ArrayList<>();
        
        // 真实的设备配置数据
        Object[][] deviceData = {
            {"SB-HY-001", "中关村智能投放点1号", "SMART_BIN", "海淀区中关村大街1号院", "ONLINE", "10.168.1.101"},
            {"SB-HY-002", "中关村智能投放点2号", "SMART_BIN", "海淀区中关村大街3号院", "ONLINE", "10.168.1.102"},
            {"FR-CY-001", "望京人脸识别终端", "FACE_RECOGNITION", "朝阳区望京SOHO T1", "ONLINE", "10.168.2.101"},
            {"FR-CY-002", "国贸人脸识别终端", "FACE_RECOGNITION", "朝阳区国贸CBD", "MAINTENANCE", "10.168.2.102"},
            {"QR-XC-001", "金融街二维码扫描器", "QR_SCANNER", "西城区金融街购物中心", "ONLINE", "10.168.3.101"},
            {"QR-XC-002", "西单二维码扫描器", "QR_SCANNER", "西城区西单大悦城", "ONLINE", "10.168.3.102"},
            {"IC-DC-001", "前门IC卡读取器", "IC_CARD_READER", "东城区前门大街", "ONLINE", "10.168.4.101"},
            {"IC-DC-002", "王府井IC卡读取器", "IC_CARD_READER", "东城区王府井步行街", "OFFLINE", "10.168.4.102"},
            {"WS-HY-001", "清华园称重传感器", "WEIGHT_SENSOR", "海淀区清华大学东门", "ONLINE", "10.168.1.201"},
            {"WS-HY-002", "北大称重传感器", "WEIGHT_SENSOR", "海淀区北京大学南门", "ONLINE", "10.168.1.202"},
            {"GPS-V001", "厨余垃圾车GPS", "GPS_TRACKER", "车辆京A12345", "ONLINE", "10.168.5.101"},
            {"GPS-V002", "可回收垃圾车GPS", "GPS_TRACKER", "车辆京A12346", "ONLINE", "10.168.5.102"},
            {"CAM-001", "中关村监控摄像头", "CAMERA", "海淀区中关村大街投放点", "ONLINE", "10.168.6.101"},
            {"CAM-002", "望京监控摄像头", "CAMERA", "朝阳区望京投放点", "ONLINE", "10.168.6.102"},
            {"FS-001", "CBD满溢检测器", "FULLNESS_SENSOR", "朝阳区CBD核心区", "ONLINE", "10.168.7.101"}
        };
        
        Random random = new Random();
        for (int i = 0; i < deviceData.length; i++) {
            Object[] data = deviceData[i];
            Map<String, Object> device = new HashMap<>();
            device.put("deviceId", data[0]);
            device.put("deviceName", data[1]);
            device.put("deviceType", data[2]);
            device.put("location", data[3]);
            device.put("status", data[4]);
            device.put("ipAddress", data[5]);
            
            // 根据状态设置心跳时间
            int minutesAgo = "ONLINE".equals(data[4]) ? random.nextInt(5) + 1 : 
                           "MAINTENANCE".equals(data[4]) ? random.nextInt(30) + 60 : 
                           random.nextInt(120) + 180;
            device.put("lastHeartbeat", LocalDateTime.now().minusMinutes(minutesAgo));
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
        
        // 真实的垃圾桶配置数据
        Object[][] containerData = {
            {"HY-CBD-001", "海淀区中关村大街1号院", "KITCHEN", 240.0, 0.96},
            {"HY-CBD-002", "海淀区中关村大街1号院", "RECYCLABLE", 240.0, 0.45},
            {"HY-CBD-003", "海淀区中关村大街1号院", "HAZARDOUS", 120.0, 0.23},
            {"HY-CBD-004", "海淀区中关村大街1号院", "OTHER", 240.0, 0.67},
            {"CY-RES-015", "朝阳区望京SOHO", "KITCHEN", 240.0, 0.89},
            {"CY-RES-016", "朝阳区望京SOHO", "RECYCLABLE", 240.0, 0.34},
            {"CY-RES-017", "朝阳区望京SOHO", "HAZARDOUS", 120.0, 0.12},
            {"CY-RES-018", "朝阳区望京SOHO", "OTHER", 240.0, 0.78},
            {"XC-SCH-008", "西城区北京小学门口", "KITCHEN", 180.0, 0.82},
            {"XC-SCH-009", "西城区北京小学门口", "RECYCLABLE", 180.0, 0.56},
            {"XC-SCH-010", "西城区北京小学门口", "OTHER", 180.0, 0.71},
            {"DC-COM-021", "东城区王府井步行街", "KITCHEN", 300.0, 0.63},
            {"DC-COM-022", "东城区王府井步行街", "RECYCLABLE", 300.0, 0.41},
            {"DC-COM-023", "东城区王府井步行街", "HAZARDOUS", 120.0, 0.18},
            {"DC-COM-024", "东城区王府井步行街", "OTHER", 300.0, 0.55},
            {"FT-PAR-031", "丰台区北京园博园", "KITCHEN", 180.0, 0.29},
            {"FT-PAR-032", "丰台区北京园博园", "RECYCLABLE", 180.0, 0.38},
            {"FT-PAR-033", "丰台区北京园博园", "OTHER", 180.0, 0.42}
        };
        
        Random random = new Random();
        for (Object[] data : containerData) {
            Map<String, Object> container = new HashMap<>();
            container.put("containerId", data[0]);
            container.put("location", data[1]);
            container.put("wasteType", data[2]);
            container.put("capacity", data[3]);
            container.put("currentFullness", data[4]);
            container.put("lastUpdated", LocalDateTime.now().minusMinutes(random.nextInt(30) + 5));
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
        
        // 真实的用户数据
        Object[][] userData = {
            {"U20250001", "张建国", "13801234567", "海淀区中关村大街1号院3单元501", "110108197503151234", true},
            {"U20250002", "李秀英", "13901234568", "海淀区中关村大街1号院5单元302", "110108198012251235", true},
            {"U20250003", "王志强", "13701234569", "朝阳区望京SOHO T1-1501", "110105198506101236", true},
            {"U20250004", "赵美丽", "15801234570", "朝阳区望京SOHO T2-2301", "110105199201151237", true},
            {"U20250005", "陈小明", "18601234571", "西城区金融街购物中心A座", "110102199508201238", false},
            {"U20250006", "刘大华", "13501234572", "西城区西单大悦城B座1201", "110102197812051239", true},
            {"U20250007", "杨丽华", "13301234573", "东城区前门大街88号", "110101198903101240", true},
            {"U20250008", "周建华", "13201234574", "东城区王府井步行街15号", "110101197706251241", true},
            {"U20250009", "吴小红", "13101234575", "海淀区清华大学教师公寓", "110108199112151242", true},
            {"U20250010", "郑志明", "13001234576", "海淀区北京大学燕园", "110108198704081243", true},
            {"U20250011", "孙丽娟", "18901234577", "丰台区北京园博园社区", "110106199009201244", true},
            {"U20250012", "马建军", "18801234578", "丰台区丰台科技园", "110106197511301245", false},
            {"U20250013", "朱小芳", "18701234579", "石景山区万达广场", "110107199306151246", true},
            {"U20250014", "胡大伟", "18601234580", "石景山区首钢园区", "110107198208101247", true},
            {"U20250015", "林小玲", "18501234581", "通州区万达广场", "110112199505251248", true},
            {"U20250016", "何建国", "18401234582", "通州区运河商务区", "110112197909151249", true},
            {"U20250017", "邓小华", "18301234583", "昌平区回龙观", "110114199201081250", true},
            {"U20250018", "韩丽丽", "18201234584", "昌平区天通苑", "110114198807201251", false},
            {"U20250019", "冯志强", "18101234585", "大兴区亦庄开发区", "110115199410121252", true},
            {"U20250020", "曹小明", "18001234586", "大兴区黄村镇", "110115197603051253", true}
        };
        
        for (Object[] data : userData) {
            Map<String, Object> user = new HashMap<>();
            user.put("userId", data[0]);
            user.put("name", data[1]);
            user.put("phone", data[2]);
            user.put("address", data[3]);
            user.put("idCard", data[4]);
            user.put("isActive", data[5]);
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
        
        // 真实的车辆配置数据
        Object[][] vehicleData = {
            {"VEH-CY-001", "京A12345", "KITCHEN", 8.0, "张师傅", "朝阳区望京SOHO附近", "COLLECTING"},
            {"VEH-CY-002", "京A12346", "RECYCLABLE", 12.0, "李师傅", "朝阳区国贸CBD", "IDLE"},
            {"VEH-HY-001", "京A12347", "KITCHEN", 8.0, "王师傅", "海淀区中关村大街", "TRANSPORTING"},
            {"VEH-HY-002", "京A12348", "OTHER", 10.0, "赵师傅", "海淀区清华大学附近", "COLLECTING"},
            {"VEH-XC-001", "京A12349", "RECYCLABLE", 12.0, "陈师傅", "西城区金融街", "IDLE"},
            {"VEH-XC-002", "京A12350", "HAZARDOUS", 5.0, "刘师傅", "西城区西单商圈", "TRANSPORTING"},
            {"VEH-DC-001", "京A12351", "KITCHEN", 8.0, "杨师傅", "东城区王府井步行街", "COLLECTING"},
            {"VEH-DC-002", "京A12352", "OTHER", 10.0, "周师傅", "东城区前门大街", "MAINTENANCE"},
            {"VEH-FT-001", "京A12353", "RECYCLABLE", 12.0, "吴师傅", "丰台区北京园博园", "IDLE"},
            {"VEH-FT-002", "京A12354", "KITCHEN", 8.0, "郑师傅", "丰台区丰台科技园", "COLLECTING"},
            {"VEH-SJS-001", "京A12355", "HAZARDOUS", 5.0, "孙师傅", "石景山区首钢园区", "TRANSPORTING"},
            {"VEH-TZ-001", "京A12356", "OTHER", 10.0, "马师傅", "通州区运河商务区", "IDLE"}
        };
        
        for (Object[] data : vehicleData) {
            Map<String, Object> vehicle = new HashMap<>();
            vehicle.put("vehicleId", data[0]);
            vehicle.put("licensePlate", data[1]);
            vehicle.put("vehicleType", data[2]);
            vehicle.put("capacity", data[3]);
            vehicle.put("driverId", data[4]);
            vehicle.put("currentLocation", data[5]);
            vehicle.put("status", data[6]);
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
        
        // 真实的积分排行榜数据
        Object[][] rankingData = {
            {"U20250001", "张建国", 4856, "环保达人", "海淀区中关村大街1号院"},
            {"U20250003", "王志强", 4623, "环保达人", "朝阳区望京SOHO T1"},
            {"U20250007", "杨丽华", 4387, "环保达人", "东城区前门大街88号"},
            {"U20250010", "郑志明", 4156, "绿色先锋", "海淀区北京大学燕园"},
            {"U20250002", "李秀英", 3924, "绿色先锋", "海淀区中关村大街1号院"},
            {"U20250008", "周建华", 3698, "绿色先锋", "东城区王府井步行街15号"},
            {"U20250004", "赵美丽", 3445, "绿色先锋", "朝阳区望京SOHO T2"},
            {"U20250011", "孙丽娟", 3187, "分类专家", "丰台区北京园博园社区"},
            {"U20250006", "刘大华", 2956, "分类专家", "西城区西单大悦城B座"},
            {"U20250009", "吴小红", 2734, "分类专家", "海淀区清华大学教师公寓"},
            {"U20250013", "朱小芳", 2512, "分类专家", "石景山区万达广场"},
            {"U20250015", "林小玲", 2298, "分类专家", "通州区万达广场"},
            {"U20250014", "胡大伟", 2087, "环保新手", "石景山区首钢园区"},
            {"U20250016", "何建国", 1876, "环保新手", "通州区运河商务区"},
            {"U20250019", "冯志强", 1654, "环保新手", "大兴区亦庄开发区"}
        };
        
        for (int i = 0; i < rankingData.length; i++) {
            Object[] data = rankingData[i];
            Map<String, Object> user = new HashMap<>();
            user.put("rank", i + 1);
            user.put("userId", data[0]);
            user.put("userName", data[1]);
            user.put("totalPoints", data[2]);
            user.put("levelName", data[3]);
            user.put("address", data[4]);
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