package com.smartwaste.domain.service;

import com.smartwaste.domain.model.Device;
import com.smartwaste.domain.model.DeviceStatus;
import com.smartwaste.domain.model.DeviceType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 设备管理服务接口
 * Device Management Service Interface
 */
public interface DeviceService {
    
    /**
     * 注册新设备
     */
    Device registerDevice(Device device);
    
    /**
     * 根据设备ID查找设备
     */
    Optional<Device> findById(String deviceId);
    
    /**
     * 根据MAC地址查找设备
     */
    Optional<Device> findByMacAddress(String macAddress);
    
    /**
     * 更新设备状态
     */
    Device updateDeviceStatus(String deviceId, DeviceStatus status);
    
    /**
     * 更新设备心跳
     */
    Device updateHeartbeat(String deviceId);
    
    /**
     * 设备自动发现
     */
    List<Device> discoverDevices(String networkRange);
    
    /**
     * 健康检查
     */
    List<Device> performHealthCheck();
    
    /**
     * 获取离线设备
     */
    List<Device> getOfflineDevices(int timeoutMinutes);
    
    /**
     * 根据设备类型查找设备
     */
    List<Device> findByDeviceType(DeviceType deviceType);
    
    /**
     * 获取需要维护的设备
     */
    List<Device> getDevicesNeedingMaintenance();
    
    /**
     * 配置设备参数
     */
    Device configureDevice(String deviceId, String configuration);
    
    /**
     * 停用设备
     */
    void deactivateDevice(String deviceId);
}