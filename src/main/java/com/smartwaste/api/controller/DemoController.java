package com.smartwaste.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 演示控制器
 * Demo Controller
 */
@Controller
@RequestMapping("/")
public class DemoController {

    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("systemName", "智慧环卫管理后台");
        return "admin/index";
    }

    @GetMapping("/api/health")
    @ResponseBody
    public Map<String, Object> health() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", LocalDateTime.now());
        health.put("system", "智慧环卫垃圾分类全链条智能监管系统");
        health.put("version", "1.0.0");
        return health;
    }

    @GetMapping("/api/stats")
    @ResponseBody
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // 模拟统计数据
        stats.put("totalDevices", 156);
        stats.put("onlineDevices", 142);
        stats.put("todayCollection", 2847);
        stats.put("totalUsers", 12456);
        stats.put("systemUptime", "99.8%");
        stats.put("lastUpdate", LocalDateTime.now());
        
        return stats;
    }

    @GetMapping("/api/modules")
    @ResponseBody
    public Map<String, Object> getModules() {
        Map<String, Object> modules = new HashMap<>();
        
        modules.put("deviceManagement", "设备管理与身份识别");
        modules.put("collectionMonitoring", "收运监管");
        modules.put("smartDispatch", "智能调度");
        modules.put("disposalMonitoring", "末端处置监控");
        modules.put("traceability", "溯源与执法支持");
        modules.put("dataAnalysis", "数据分析与报表");
        
        return modules;
    }

    @GetMapping("/demo.html")
    public String demoPage() {
        return "forward:/demo.html";
    }

    @GetMapping("/test")
    public String testPage() {
        return "forward:/test.html";
    }
}