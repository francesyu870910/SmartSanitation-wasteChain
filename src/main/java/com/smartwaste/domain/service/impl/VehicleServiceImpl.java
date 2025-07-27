package com.smartwaste.domain.service.impl;

import com.smartwaste.domain.model.Vehicle;
import com.smartwaste.domain.model.VehicleStatus;
import com.smartwaste.domain.model.WasteType;
import com.smartwaste.domain.repository.VehicleRepository;
import com.smartwaste.domain.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 收运车辆服务实现类
 * Vehicle Service Implementation
 */
@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        if (vehicle.getVehicleId() == null || vehicle.getVehicleId().isEmpty()) {
            vehicle.setVehicleId(UUID.randomUUID().toString());
        }
        
        // 验证车牌号唯一性
        if (vehicleRepository.findByLicensePlate(vehicle.getLicensePlate()).isPresent()) {
            throw new IllegalArgumentException("车牌号已存在");
        }
        
        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vehicle> findById(String vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vehicle> findByLicensePlate(String licensePlate) {
        return vehicleRepository.findByLicensePlate(licensePlate);
    }

    @Override
    public Vehicle updateVehicleStatus(String vehicleId, VehicleStatus status) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(vehicleId);
        if (vehicleOpt.isEmpty()) {
            throw new IllegalArgumentException("车辆不存在");
        }
        
        Vehicle vehicle = vehicleOpt.get();
        vehicle.setStatus(status);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicleLocation(String vehicleId, String location) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(vehicleId);
        if (vehicleOpt.isEmpty()) {
            throw new IllegalArgumentException("车辆不存在");
        }
        
        Vehicle vehicle = vehicleOpt.get();
        vehicle.setCurrentLocation(location);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle assignDriver(String vehicleId, String driverId) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(vehicleId);
        if (vehicleOpt.isEmpty()) {
            throw new IllegalArgumentException("车辆不存在");
        }
        
        Vehicle vehicle = vehicleOpt.get();
        vehicle.setDriverId(driverId);
        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findAvailableVehicles();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getAvailableVehiclesByType(WasteType wasteType) {
        return vehicleRepository.findAvailableVehiclesByType(wasteType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> findByDriverId(String driverId) {
        return vehicleRepository.findByDriverId(driverId);
    }
}