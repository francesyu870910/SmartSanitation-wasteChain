package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.Vehicle;
import com.smartwaste.domain.model.VehicleStatus;
import com.smartwaste.domain.model.WasteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 收运车辆数据访问接口
 * Vehicle Repository Interface
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    
    /**
     * 根据车牌号查找车辆
     */
    Optional<Vehicle> findByLicensePlate(String licensePlate);
    
    /**
     * 根据司机ID查找车辆
     */
    List<Vehicle> findByDriverId(String driverId);
    
    /**
     * 根据车辆状态查找车辆
     */
    List<Vehicle> findByStatus(VehicleStatus status);
    
    /**
     * 根据车辆类型和状态查找车辆
     */
    List<Vehicle> findByVehicleTypeAndStatus(WasteType vehicleType, VehicleStatus status);
    
    /**
     * 查找可用的车辆（空闲状态）
     */
    default List<Vehicle> findAvailableVehicles() {
        return findByStatus(VehicleStatus.IDLE);
    }
    
    /**
     * 根据车辆类型查找可用车辆
     */
    default List<Vehicle> findAvailableVehiclesByType(WasteType vehicleType) {
        return findByVehicleTypeAndStatus(vehicleType, VehicleStatus.IDLE);
    }
}