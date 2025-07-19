package com.smartwaste.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 数据库配置类（演示版）
 * Database Configuration for Demo
 */
@Configuration
public class DatabaseConfig {
    // H2内存数据库会自动配置，无需额外配置
}