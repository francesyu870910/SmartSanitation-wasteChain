package com.smartwaste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 智慧环卫垃圾分类全链条智能监管系统
 * Smart Waste Management System
 */
@SpringBootApplication
public class SmartWasteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartWasteApplication.class, args);
        System.out.println("=================================");
        System.out.println("智慧环卫垃圾分类全链条智能监管系统启动成功！");
        System.out.println("访问地址: http://localhost:8080");
        System.out.println("演示页面: http://localhost:8080/demo.html");
        System.out.println("管理后台: http://localhost:8080/admin/");
        System.out.println("API文档: http://localhost:8080/swagger-ui.html");
        System.out.println("=================================");
    }
}