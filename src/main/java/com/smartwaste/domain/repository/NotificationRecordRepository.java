package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.NotificationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 通知记录数据访问接口
 * Notification Record Repository
 */
@Repository
public interface NotificationRecordRepository extends JpaRepository<NotificationRecord, String> {
    
    /**
     * 根据接收者ID查找通知记录
     */
    List<NotificationRecord> findByRecipientIdOrderBySentTimeDesc(String recipientId);
    
    /**
     * 根据接收者ID和限制数量查找通知记录
     */
    List<NotificationRecord> findTop10ByRecipientIdOrderBySentTimeDesc(String recipientId);
    
    /**
     * 查找未读通知
     */
    List<NotificationRecord> findByRecipientIdAndIsReadFalseOrderBySentTimeDesc(String recipientId);
    
    /**
     * 根据任务ID查找相关通知
     */
    List<NotificationRecord> findByRelatedTaskId(String taskId);
    
    /**
     * 根据通知类型查找通知
     */
    List<NotificationRecord> findByNotificationType(String notificationType);
    
    /**
     * 查找指定时间范围内的通知
     */
    @Query("SELECT n FROM NotificationRecord n WHERE n.sentTime BETWEEN :startTime AND :endTime")
    List<NotificationRecord> findByTimeRange(@Param("startTime") LocalDateTime startTime, 
                                           @Param("endTime") LocalDateTime endTime);
    
    /**
     * 统计总通知数
     */
    long count();
    
    /**
     * 统计已发送通知数
     */
    long countByStatus(String status);
    
    /**
     * 统计已读通知数
     */
    long countByIsReadTrue();
    
    /**
     * 统计未读通知数
     */
    long countByIsReadFalse();
    
    /**
     * 统计今日通知数
     */
    @Query("SELECT COUNT(n) FROM NotificationRecord n WHERE n.sentTime >= :startOfDay AND n.sentTime < :endOfDay")
    long countTodayNotifications(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
    
    /**
     * 统计高优先级通知数
     */
    long countByPriority(String priority);
    
    /**
     * 根据接收者类型统计通知数
     */
    @Query("SELECT n.recipientType, COUNT(n) FROM NotificationRecord n GROUP BY n.recipientType")
    List<Object[]> countByRecipientType();
    
    /**
     * 根据通知类型统计通知数
     */
    @Query("SELECT n.notificationType, COUNT(n) FROM NotificationRecord n GROUP BY n.notificationType")
    List<Object[]> countByNotificationType();
}