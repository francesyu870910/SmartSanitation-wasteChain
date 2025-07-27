package com.smartwaste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

/**
 * 智慧环卫垃圾分类全链条智能监管系统主应用程序
 */
@SpringBootApplication
public class SmartWasteManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartWasteManagementApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        System.out.println("=================================================");
        System.out.println("智慧环卫垃圾分类全链条智能监管系统启动成功！");
        System.out.println("=================================================");
        System.out.println("🌐 Web访问地址:");
        System.out.println("   - 系统主页: http://localhost:8080");
        System.out.println("   - 管理后台: http://localhost:8080/admin.html");
        System.out.println("   - 系统演示: http://localhost:8080/demo.html");
        System.out.println("   - 简单演示: http://localhost:8080/simple-demo.html");
        System.out.println("   - 报表管理: http://localhost:8080/reports.html");
        System.out.println("   - 简化报表: http://localhost:8080/test-simple-reports.html");
        System.out.println("   - 报表测试: http://localhost:8080/test-reports.html");
        System.out.println("");
        System.out.println("🔧 管理工具:");
        System.out.println("   - H2数据库: http://localhost:8080/h2-console");
        System.out.println("     (JDBC URL: jdbc:h2:mem:smartwaste, 用户名: sa, 密码: 空)");
        System.out.println("   - 健康检查: http://localhost:8080/actuator/health");
        System.out.println("");
        System.out.println("📊 API接口:");
        System.out.println("   - 系统状态: http://localhost:8080/api/health");
        System.out.println("   - 统计数据: http://localhost:8080/api/stats");
        System.out.println("   - 高级报表: http://localhost:8080/api/advanced-reports");
        System.out.println("   - 演示数据: http://localhost:8080/api/demo");
        System.out.println("=================================================");
        System.out.println("✅ 系统已就绪，请访问上述地址体验功能！");
        System.out.println("=================================================");
    }
}