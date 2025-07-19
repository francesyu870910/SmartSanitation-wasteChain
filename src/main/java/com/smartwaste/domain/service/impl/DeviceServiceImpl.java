package com.smartwaste.domain.service.impl;

import com.smartwaste.domain.model.Device;
import com.smartwaste.domain.model.DeviceStatus;
import com.smartwaste.domain.model.DeviceType;
import com.smartwaste.domain.repository.DeviceRepository;
import com.smartwaste.domain.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 设备管理服务实现类
 * Device Management Service Implementation
 */
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Device registerDevice(Device device) {
        logger.info("注册新设备: {}", device.getDeviceName());
        
        if (device.getDeviceId() == null || device.getDeviceId().isEmpty()) {
            device.setDeviceId(UUID.randomUUID().toString());
        }
        
        // 验证MAC地址唯一性
        if (device.getMacAddress() != null && 
            deviceRepository.findByMacAddress(device.getMacAddress()).isPresent()) {
            throw new IllegalArgumentException("MAC地址已存在: " + device.getMacAddress());
        }
        
        // 验证IP地址唯一性
        if (device.getIpAddress() != null && 
            deviceRepository.findByIpAddress(device.getIpAddress()).isPresent()) {
            throw new IllegalArgumentException("IP地址已存在: " + device.getIpAddress());
        }
        
        // 设置初始状态
        device.setStatus(DeviceStatus.PENDING);
        device.setCreatedAt(LocalDateTime.now());
        
        Device savedDevice = deviceRepository.save(device);
        logger.info("设备注册成功: {} (ID: {})", savedDevice.getDeviceName(), savedDevice.getDeviceId());
        
        return savedDevice;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Device> findById(String deviceId) {
        return deviceRepository.findById(deviceId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Device> findByMacAddress(String macAddress) {
        return deviceRepository.findByMacAddress(macAddress);
    }

    @Override
    public Device updateDeviceStatus(String deviceId, DeviceStatus status) {
        Optional<Device> deviceOpt = deviceRepository.findById(deviceId);
        if (deviceOpt.isEmpty()) {
            throw new IllegalArgumentException("设备不存在: " + deviceId);
        }
        
        Device device = deviceOpt.get();
        DeviceStatus oldStatus = device.getStatus();
        device.setStatus(status);
        
        // 如果状态变为在线，更新心跳时间
        if (status == DeviceStatus.ONLINE) {
            device.setLastHeartbeat(LocalDateTime.now());
        }
        
        Device updatedDevice = deviceRepository.save(device);
        logger.info("设备状态更新: {} 从 {} 变为 {}", device.getDeviceName(), oldStatus, status);
        
        return updatedDevice;
    }

    @Override
    public Device updateHeartbeat(String deviceId) {
        Optional<Device> deviceOpt = deviceRepository.findById(deviceId);
        if (deviceOpt.isEmpty()) {
            throw new IllegalArgumentException("设备不存在: " + deviceId);
        }
        
        Device device = deviceOpt.get();
        device.setLastHeartbeat(LocalDateTime.now());
        
        // 如果设备之前是离线状态，自动设为在线
        if (device.getStatus() == DeviceStatus.OFFLINE) {
            device.setStatus(DeviceStatus.ONLINE);
            logger.info("设备重新上线: {}", device.getDeviceName());
        }
        
        return deviceRepository.save(device);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Device> discoverDevices(String networkRange) {
        logger.info("开始设备自动发现，网络范围: {}", networkRange);
        
        // 模拟设备发现逻辑
        // 实际实现中会通过网络扫描、SNMP等协议发现设备
        List<Device> discoveredDevices = deviceRepository.findByStatus(DeviceStatus.PENDING);
        
        logger.info("发现 {} 个待激活设备", discoveredDevices.size());
        return discoveredDevices;
    }

    @Override
    public List<Device> performHealthCheck() {
        logger.info("开始执行设备健康检查");
        
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(5); // 5分钟无心跳视为离线
        List<Device> offlineDevices = deviceRepository.findDevicesOfflineSince(threshold);
        
        // 更新离线设备状态
        for (Device device : offlineDevices) {
            if (device.getStatus() == DeviceStatus.ONLINE) {
                device.setStatus(DeviceStatus.OFFLINE);
                deviceRepository.save(device);
                logger.warn("设备离线: {} (最后心跳: {})", 
                    device.getDeviceName(), device.getLastHeartbeat());
            }
        }
        
        logger.info("健康检查完成，发现 {} 个离线设备", offlineDevices.size());
        return offlineDevices;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Device> getOfflineDevices(int timeoutMinutes) {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(timeoutMinutes);
        return deviceRepository.findDevicesOfflineSince(threshold);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Device> findByDeviceType(DeviceType deviceType) {
        return deviceRepository.findByDeviceType(deviceType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Device> getDevicesNeedingMaintenance() {
        return deviceRepository.findDevicesNeedingMaintenance();
    }

    @Override
    public Device configureDevice(String deviceId, String configuration) {
        Optional<Device> deviceOpt = deviceRepository.findById(deviceId);
        if (deviceOpt.isEmpty()) {
            throw new IllegalArgumentException("设备不存在: " + deviceId);
        }
        
        Device device = deviceOpt.get();
        
        // 这里可以解析配置参数并应用到设备
        // 实际实现中会通过设备通信协议发送配置命令
        logger.info("配置设备: {} 配置参数: {}", device.getDeviceName(), configuration);
        
        // 模拟配置成功后激活设备
        if (device.getStatus() == DeviceStatus.PENDING) {
            device.setStatus(DeviceStatus.ONLINE);
            device.setLastHeartbeat(LocalDateTime.now());
        }
        
        return deviceRepository.save(device);
    }

    @Override
    public void deactivateDevice(String deviceId) {
        Optional<Device> deviceOpt = deviceRepository.findById(deviceId);
        if (deviceOpt.isPresent()) {
            Device device = deviceOpt.get();
            device.setStatus(DeviceStatus.OFFLINE);
            deviceRepository.save(device);
            logger.info("设备已停用: {}", device.getDeviceName());
        } else {
            throw new IllegalArgumentException("设备不存在: " + deviceId);
        }
    }
}