package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.DispatchPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DispatchPlanRepository extends JpaRepository<DispatchPlan, Long> {
    
    List<DispatchPlan> findByVehicleIdOrderByScheduledDateDesc(String vehicleId);
    
    List<DispatchPlan> findByDriverIdOrderByScheduledDateDesc(String driverId);
    
    List<DispatchPlan> findByStatusOrderByScheduledDateDesc(DispatchPlan.PlanStatus status);
    
    List<DispatchPlan> findByWasteTypeAndStatusOrderByScheduledDateDesc(
        String wasteType, DispatchPlan.PlanStatus status);
    
    @Query("SELECT dp FROM DispatchPlan dp WHERE dp.scheduledDate BETWEEN :startDate AND :endDate " +
           "ORDER BY dp.scheduledDate ASC")
    List<DispatchPlan> findByScheduledDateBetween(
        @Param("startDate") LocalDateTime startDate, 
        @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT dp FROM DispatchPlan dp WHERE dp.vehicleId = :vehicleId " +
           "AND dp.status IN ('SCHEDULED', 'IN_PROGRESS') ORDER BY dp.scheduledDate ASC")
    List<DispatchPlan> findActiveDispatchPlansByVehicle(@Param("vehicleId") String vehicleId);
}