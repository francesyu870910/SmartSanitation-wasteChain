package com.smartwaste.domain.service.impl;

import com.smartwaste.domain.model.Device;
import com.smartwaste.domain.model.DeviceStatus;
import com.smartwaste.domain.model.DeviceType;
import com.smartwaste.domain.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * 设备服务实现类单元测试
 * Device Service Implementation Unit Tests
 */
@ExtendWith(MockitoExtension.class)
class DeviceServiceImplTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceServiceImpl deviceService;

    private Device testDevice;

    @BeforeEach
    void setUp() {
        testDevice = new Device();
        testDevice.setDeviceId("test-device-001");
        testDevice.setDeviceName("测试智能垃圾桶");
        testDevice.setDeviceType(DeviceType.SMART_BIN);
        testDevice.setLocation("测试地点");
        testDevice.setMacAddress("AA:BB:CC:DD:EE:FF");
        testDevice.setIpAddress("192.168.1.100");
        testDevice.setStatus(DeviceStatus.ONLINE);
        testDevice.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void testRegisterDevice_Success() {
        // Given
        Device newDevice = new Device();
        newDevice.setDeviceName("新设备");
        newDevice.setDeviceType(DeviceType.SMART_BIN);
        newDevice.setMacAddress("11:22:33:44:55:66");
        
        when(deviceRepository.findByMacAddress(anyString())).thenReturn(Optional.empty());
        when(deviceRepository.save(any(Device.class))).thenReturn(testDevice);

        // When
        Device result = deviceService.registerDevice(newDevice);

        // Then
        assertNotNull(result);
        assertEquals(DeviceStatus.PENDING, newDevice.getStatus());
        verify(deviceRepository).save(any(Device.class));
    }

    @Test
    void testRegisterDevice_DuplicateMacAddress() {
        // Given
        Device newDevice = new Device();
        newDevice.setMacAddress("AA:BB:CC:DD:EE:FF");
        
        when(deviceRepository.findByMacAddress(anyString())).thenReturn(Optional.of(testDevice));

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> deviceService.registerDevice(newDevice)
        );
        
        assertTrue(exception.getMessage().contains("MAC地址已存在"));
        verify(deviceRepository, never()).save(any(Device.class));
    }

    @Test
    void testUpdateDeviceStatus_Success() {
        // Given
        String deviceId = "test-device-001";
        DeviceStatus newStatus = DeviceStatus.MAINTENANCE;
        
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(testDevice));
        when(deviceRepository.save(any(Device.class))).thenReturn(testDevice);

        // When
        Device result = deviceService.updateDeviceStatus(deviceId, newStatus);

        // Then
        assertNotNull(result);
        assertEquals(newStatus, testDevice.getStatus());
        verify(deviceRepository).save(testDevice);
    }

    @Test
    void testUpdateDeviceStatus_DeviceNotFound() {
        // Given
        String deviceId = "non-existent-device";
        
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.empty());

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> deviceService.updateDeviceStatus(deviceId, DeviceStatus.ONLINE)
        );
        
        assertTrue(exception.getMessage().contains("设备不存在"));
        verify(deviceRepository, never()).save(any(Device.class));
    }

    @Test
    void testUpdateHeartbeat_Success() {
        // Given
        String deviceId = "test-device-001";
        testDevice.setStatus(DeviceStatus.OFFLINE);
        
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(testDevice));
        when(deviceRepository.save(any(Device.class))).thenReturn(testDevice);

        // When
        Device result = deviceService.updateHeartbeat(deviceId);

        // Then
        assertNotNull(result);
        assertEquals(DeviceStatus.ONLINE, testDevice.getStatus());
        assertNotNull(testDevice.getLastHeartbeat());
        verify(deviceRepository).save(testDevice);
    }

    @Test
    void testPerformHealthCheck() {
        // Given
        Device offlineDevice = new Device();
        offlineDevice.setDeviceId("offline-device");
        offlineDevice.setStatus(DeviceStatus.ONLINE);
        offlineDevice.setLastHeartbeat(LocalDateTime.now().minusMinutes(10));
        
        List<Device> offlineDevices = Arrays.asList(offlineDevice);
        
        when(deviceRepository.findDevicesOfflineSince(any(LocalDateTime.class)))
            .thenReturn(offlineDevices);
        when(deviceRepository.save(any(Device.class))).thenReturn(offlineDevice);

        // When
        List<Device> result = deviceService.performHealthCheck();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(DeviceStatus.OFFLINE, offlineDevice.getStatus());
        verify(deviceRepository).save(offlineDevice);
    }

    @Test
    void testFindByDeviceType() {
        // Given
        DeviceType deviceType = DeviceType.SMART_BIN;
        List<Device> expectedDevices = Arrays.asList(testDevice);
        
        when(deviceRepository.findByDeviceType(deviceType)).thenReturn(expectedDevices);

        // When
        List<Device> result = deviceService.findByDeviceType(deviceType);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testDevice, result.get(0));
        verify(deviceRepository).findByDeviceType(deviceType);
    }

    @Test
    void testConfigureDevice_Success() {
        // Given
        String deviceId = "test-device-001";
        String configuration = "{\"threshold\": 80, \"interval\": 300}";
        testDevice.setStatus(DeviceStatus.PENDING);
        
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(testDevice));
        when(deviceRepository.save(any(Device.class))).thenReturn(testDevice);

        // When
        Device result = deviceService.configureDevice(deviceId, configuration);

        // Then
        assertNotNull(result);
        assertEquals(DeviceStatus.ONLINE, testDevice.getStatus());
        assertNotNull(testDevice.getLastHeartbeat());
        verify(deviceRepository).save(testDevice);
    }

    @Test
    void testDeactivateDevice_Success() {
        // Given
        String deviceId = "test-device-001";
        
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(testDevice));
        when(deviceRepository.save(any(Device.class))).thenReturn(testDevice);

        // When
        deviceService.deactivateDevice(deviceId);

        // Then
        assertEquals(DeviceStatus.OFFLINE, testDevice.getStatus());
        verify(deviceRepository).save(testDevice);
    }
}