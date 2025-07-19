package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.Device;
import com.smartwaste.domain.model.DeviceStatus;
import com.smartwaste.domain.model.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 设备数据访问接口
 * Device Repository Interface
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {
    
    /**
     * 根据设备名称查找设备
     */
    Optional<Device> findByDeviceName(String deviceName);
    
    /**
     * 根据MAC地址查找设备
     */
    Optional<Device> findByMacAddress(String macAddress);
    
    /**
     * 根据IP地址查找设备
     */
    Optional<Device> findByIpAddress(String ipAddress);
    
    /**
     * 根据设备类型查找设备
     */
    List<Device> findByDeviceType(DeviceType deviceType);
    
    /**
     * 根据设备状态查找设备
     */
    List<Device> findByStatus(DeviceStatus status);
    
    /**
     * 根据位置模糊查询设备
     */
    @Query("SELECT d FROM Device d WHERE d.location LIKE %:location%")
    List<Device> findByLocationContaining(@Param("location") String location);
    
    /**
     * 查找在线设备
     */
    @Query("SELECT d FROM Device d WHERE d.status = 'ONLINE'")
    List<Device> findOnlineDevices();
    
    /**
     * 查找离线超过指定时间的设备
     */
    @Query("SELECT d FROM Device d WHERE d.lastHeartbeat < :threshold OR d.lastHeartbeat IS NULL")
    List<Device> findDevicesOfflineSince(@Param("threshold") LocalDateTime threshold);
    
    /**
     * 根据设备类型和状态查找设备
     */
    List<Device> findByDeviceTypeAndStatus(DeviceType deviceType, DeviceStatus status);
    
    /**
     * 查找需要维护的设备
     */
    @Query("SELECT d FROM Device d WHERE d.status IN ('ERROR', 'MAINTENANCE')")
    List<Device> findDevicesNeedingMaintenance();
}