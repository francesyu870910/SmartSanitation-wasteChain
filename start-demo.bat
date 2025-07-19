@echo off
echo ========================================
echo 智慧环卫垃圾分类全链条智能监管系统
echo Smart Waste Management System Demo
echo ========================================
echo.

echo 正在启动系统演示...
echo.

REM 检查Java环境
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo 错误: 未找到Java环境，请先安装Java 17或更高版本
    pause
    exit /b 1
)

REM 检查Maven环境
call mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo 警告: 未找到Maven，使用内置Maven...
    set MAVEN_CMD=apache-maven-3.9.6\bin\mvn.cmd
) else (
    set MAVEN_CMD=mvn
)

echo 1. 编译项目...
call %MAVEN_CMD% clean compile -q
if %errorlevel% neq 0 (
    echo 编译失败，请检查代码
    pause
    exit /b 1
)

echo 2. 启动Spring Boot应用...
echo.
echo 系统启动中，请稍候...
echo 启动完成后可以通过以下地址访问：
echo.
echo 管理后台: http://localhost:8080/admin/
echo API文档:  http://localhost:8080/swagger-ui.html
echo 健康检查: http://localhost:8080/actuator/health
echo.
echo 按 Ctrl+C 停止系统
echo.

REM 启动应用
call %MAVEN_CMD% spring-boot:run -Dspring-boot.run.profiles=demo

pause