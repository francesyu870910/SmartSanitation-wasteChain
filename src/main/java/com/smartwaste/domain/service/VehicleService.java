package com.smartwaste.domain.service;

import com.smartwaste.domain.model.Vehicle;
import com.smartwaste.domain.model.VehicleStatus;
import com.smartwaste.domain.model.WasteType;
import java.util.List;
import java.util.Optional;

/**
 * 收运车辆服务接口
 * Vehicle Service Interface
 */
public interface VehicleService {
    
    /**
     * 创建车辆
     */
    Vehicle createVehicle(Vehicle vehicle);
    
    /**
     * 根据车辆ID查找车辆
     */
    Optional<Vehicle> findById(String vehicleId);
    
    /**
     * 根据车牌号查找车辆
     */
    Optional<Vehicle> findByLicensePlate(String licensePlate);
    
    /**
     * 更新车辆状态
     */
    Vehicle updateVehicleStatus(String vehicleId, VehicleStatus status);
    
    /**
     * 更新车辆位置
     */
    Vehicle updateVehicleLocation(String vehicleId, String location);
    
    /**
     * 分配司机到车辆
     */
    Vehicle assignDriver(String vehicleId, String driverId);
    
    /**
     * 获取可用车辆
     */
    List<Vehicle> getAvailableVehicles();
    
    /**
     * 根据垃圾类型获取可用车辆
     */
    List<Vehicle> getAvailableVehiclesByType(WasteType wasteType);
    
    /**
     * 根据司机ID查找车辆
     */
    List<Vehicle> findByDriverId(String driverId);
}