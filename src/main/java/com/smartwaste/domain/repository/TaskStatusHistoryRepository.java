package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.TaskStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 任务状态历史数据访问接口
 * Task Status History Repository
 */
@Repository
public interface TaskStatusHistoryRepository extends JpaRepository<TaskStatusHistory, Long> {
    
    /**
     * 根据任务ID查找状态历史
     */
    List<TaskStatusHistory> findByTaskIdOrderByUpdateTimeDesc(String taskId);
    
    /**
     * 根据更新人查找状态历史
     */
    List<TaskStatusHistory> findByUpdateByOrderByUpdateTimeDesc(String updateBy);
    
    /**
     * 根据操作类型查找状态历史
     */
    List<TaskStatusHistory> findByOperationType(String operationType);
    
    /**
     * 查找指定时间范围内的状态变更
     */
    @Query("SELECT h FROM TaskStatusHistory h WHERE h.updateTime BETWEEN :startTime AND :endTime ORDER BY h.updateTime DESC")
    List<TaskStatusHistory> findByTimeRange(@Param("startTime") LocalDateTime startTime, 
                                          @Param("endTime") LocalDateTime endTime);
    
    /**
     * 根据任务ID和操作类型查找历史
     */
    List<TaskStatusHistory> findByTaskIdAndOperationType(String taskId, String operationType);
    
    /**
     * 查找最近的状态变更记录
     */
    @Query("SELECT h FROM TaskStatusHistory h WHERE h.taskId = :taskId ORDER BY h.updateTime DESC")
    List<TaskStatusHistory> findLatestByTaskId(@Param("taskId") String taskId);
    
    /**
     * 统计任务状态变更次数
     */
    @Query("SELECT COUNT(h) FROM TaskStatusHistory h WHERE h.taskId = :taskId")
    long countByTaskId(@Param("taskId") String taskId);
    
    /**
     * 根据操作类型统计数量
     */
    @Query("SELECT h.operationType, COUNT(h) FROM TaskStatusHistory h GROUP BY h.operationType")
    List<Object[]> countByOperationType();
}