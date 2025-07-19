package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.DispatchTask;
import com.smartwaste.domain.model.DispatchStatus;
import com.smartwaste.domain.model.WasteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调度任务数据访问接口
 * Dispatch Task Repository
 */
@Repository
public interface DispatchTaskRepository extends JpaRepository<DispatchTask, String> {
    
    /**
     * 根据状态查找调度任务
     */
    List<DispatchTask> findByStatus(DispatchStatus status);
    
    /**
     * 根据垃圾类型查找调度任务
     */
    List<DispatchTask> findByWasteType(WasteType wasteType);
    
    /**
     * 根据分配的车辆ID查找调度任务
     */
    List<DispatchTask> findByAssignedVehicleId(String vehicleId);
    
    /**
     * 根据分配的车辆ID和状态查找调度任务
     */
    List<DispatchTask> findByAssignedVehicleIdAndStatus(String vehicleId, DispatchStatus status);
    
    /**
     * 根据状态和垃圾类型查找调度任务
     */
    List<DispatchTask> findByStatusAndWasteType(DispatchStatus status, WasteType wasteType);
    
    /**
     * 查找待分配的任务，按优先级和创建时间排序
     */
    @Query("SELECT dt FROM DispatchTask dt WHERE dt.status = :status ORDER BY dt.priority DESC, dt.createdAt ASC")
    List<DispatchTask> findPendingTasksOrderByPriorityAndTime(@Param("status") DispatchStatus status);
    
    /**
     * 查找指定时间范围内的任务
     */
    @Query("SELECT dt FROM DispatchTask dt WHERE dt.createdAt BETWEEN :startTime AND :endTime")
    List<DispatchTask> findTasksInTimeRange(@Param("startTime") LocalDateTime startTime, 
                                           @Param("endTime") LocalDateTime endTime);
    
    /**
     * 统计各状态的任务数量
     */
    @Query("SELECT dt.status, COUNT(dt) FROM DispatchTask dt GROUP BY dt.status")
    List<Object[]> countTasksByStatus();
    
    /**
     * 查找高优先级的待处理任务
     */
    @Query("SELECT dt FROM DispatchTask dt WHERE dt.status IN :statuses AND dt.priority >= :minPriority ORDER BY dt.priority DESC")
    List<DispatchTask> findHighPriorityTasks(@Param("statuses") List<DispatchStatus> statuses, 
                                            @Param("minPriority") Integer minPriority);
}