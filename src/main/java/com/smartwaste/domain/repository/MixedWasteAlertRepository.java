package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.MixedWasteAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 混装混运预警数据访问接口
 * Mixed Waste Alert Repository
 */
@Repository
public interface MixedWasteAlertRepository extends JpaRepository<MixedWasteAlert, String> {
    
    /**
     * 根据车辆ID查找预警
     */
    List<MixedWasteAlert> findByVehicleId(String vehicleId);
    
    /**
     * 根据任务ID查找预警
     */
    List<MixedWasteAlert> findByTaskId(String taskId);
    
    /**
     * 查找未解决的预警
     */
    List<MixedWasteAlert> findByIsResolvedFalse();
    
    /**
     * 根据车辆ID查找未解决的预警
     */
    List<MixedWasteAlert> findByVehicleIdAndIsResolvedFalse(String vehicleId);
    
    /**
     * 根据预警类型查找预警
     */
    List<MixedWasteAlert> findByAlertType(MixedWasteAlert.AlertType alertType);
    
    /**
     * 根据严重程度查找预警
     */
    List<MixedWasteAlert> findBySeverity(MixedWasteAlert.AlertSeverity severity);
    
    /**
     * 查找指定时间范围内的预警
     */
    @Query("SELECT a FROM MixedWasteAlert a WHERE a.alertTime BETWEEN :startTime AND :endTime")
    List<MixedWasteAlert> findAlertsInTimeRange(@Param("startTime") LocalDateTime startTime, 
                                               @Param("endTime") LocalDateTime endTime);
    
    /**
     * 统计各类型预警数量
     */
    @Query("SELECT a.alertType, COUNT(a) FROM MixedWasteAlert a GROUP BY a.alertType")
    List<Object[]> countAlertsByType();
    
    /**
     * 统计各严重程度预警数量
     */
    @Query("SELECT a.severity, COUNT(a) FROM MixedWasteAlert a GROUP BY a.severity")
    List<Object[]> countAlertsBySeverity();
    
    /**
     * 查找高优先级未解决预警
     */
    @Query("SELECT a FROM MixedWasteAlert a WHERE a.isResolved = false AND a.severity IN :severities ORDER BY a.alertTime DESC")
    List<MixedWasteAlert> findHighPriorityUnresolvedAlerts(@Param("severities") List<MixedWasteAlert.AlertSeverity> severities);
    
    /**
     * 查找最近的预警
     */
    @Query("SELECT a FROM MixedWasteAlert a ORDER BY a.alertTime DESC")
    List<MixedWasteAlert> findRecentAlerts();
}