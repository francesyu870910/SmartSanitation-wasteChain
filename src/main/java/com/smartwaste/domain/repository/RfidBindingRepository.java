package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.RfidBinding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * RFID绑定数据访问接口
 * RFID Binding Repository Interface
 */
@Repository
public interface RfidBindingRepository extends JpaRepository<RfidBinding, String> {
    
    /**
     * 根据RFID标签查找绑定
     */
    Optional<RfidBinding> findByRfidTagAndIsActiveTrue(String rfidTag);
    
    /**
     * 根据垃圾桶ID查找绑定
     */
    List<RfidBinding> findByContainerIdAndIsActiveTrue(String containerId);
    
    /**
     * 根据车辆ID查找绑定
     */
    List<RfidBinding> findByVehicleIdAndIsActiveTrue(String vehicleId);
    
    /**
     * 获取所有活跃绑定
     */
    List<RfidBinding> findByIsActiveTrueOrderByBindingTimeDesc();
    
    /**
     * 根据操作员查找绑定记录
     */
    List<RfidBinding> findByOperatorIdOrderByBindingTimeDesc(String operatorId);
    
    /**
     * 根据时间范围查找绑定记录
     */
    @Query("SELECT rb FROM RfidBinding rb WHERE rb.bindingTime BETWEEN :startTime AND :endTime")
    List<RfidBinding> findByBindingTimeBetween(@Param("startTime") LocalDateTime startTime,
                                              @Param("endTime") LocalDateTime endTime);
    
    /**
     * 检查垃圾桶和车辆是否已绑定
     */
    @Query("SELECT rb FROM RfidBinding rb WHERE rb.containerId = :containerId AND rb.vehicleId = :vehicleId AND rb.isActive = true")
    Optional<RfidBinding> findActiveBindingByContainerAndVehicle(@Param("containerId") String containerId,
                                                                @Param("vehicleId") String vehicleId);
    
    /**
     * 统计活跃绑定数量
     */
    @Query("SELECT COUNT(rb) FROM RfidBinding rb WHERE rb.isActive = true")
    Long countActiveBindings();
    
    /**
     * 根据垃圾桶ID统计绑定历史
     */
    @Query("SELECT COUNT(rb) FROM RfidBinding rb WHERE rb.containerId = :containerId")
    Long countBindingHistoryByContainer(@Param("containerId") String containerId);
}