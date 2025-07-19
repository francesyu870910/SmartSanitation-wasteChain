package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.IdentificationMethod;
import com.smartwaste.domain.model.UserIdentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户身份识别记录数据访问接口
 * User Identification Repository Interface
 */
@Repository
public interface UserIdentificationRepository extends JpaRepository<UserIdentification, String> {
    
    /**
     * 根据用户ID查找识别记录，按时间倒序
     */
    List<UserIdentification> findByUserIdOrderByIdentificationTimeDesc(String userId);
    
    /**
     * 根据设备ID查找识别记录，按时间倒序
     */
    List<UserIdentification> findByDeviceIdOrderByIdentificationTimeDesc(String deviceId);
    
    /**
     * 根据识别方式查找记录
     */
    List<UserIdentification> findByIdentificationMethod(IdentificationMethod method);
    
    /**
     * 根据设备ID和识别方式查找记录
     */
    List<UserIdentification> findByDeviceIdAndIdentificationMethod(String deviceId, IdentificationMethod method);
    
    /**
     * 查找成功的识别记录
     */
    List<UserIdentification> findByIsSuccessfulTrue();
    
    /**
     * 查找失败的识别记录
     */
    List<UserIdentification> findByIsSuccessfulFalse();
    
    /**
     * 根据时间范围查找识别记录
     */
    @Query("SELECT ui FROM UserIdentification ui WHERE ui.identificationTime BETWEEN :startTime AND :endTime")
    List<UserIdentification> findByIdentificationTimeBetween(@Param("startTime") LocalDateTime startTime,
                                                            @Param("endTime") LocalDateTime endTime);
    
    /**
     * 统计设备识别成功率
     */
    @Query("SELECT COUNT(ui) FROM UserIdentification ui WHERE ui.deviceId = :deviceId AND ui.isSuccessful = true")
    Long countSuccessfulIdentificationsByDevice(@Param("deviceId") String deviceId);
    
    /**
     * 统计设备识别总数
     */
    @Query("SELECT COUNT(ui) FROM UserIdentification ui WHERE ui.deviceId = :deviceId")
    Long countTotalIdentificationsByDevice(@Param("deviceId") String deviceId);
    
    /**
     * 根据置信度范围查找记录
     */
    @Query("SELECT ui FROM UserIdentification ui WHERE ui.confidenceScore BETWEEN :minScore AND :maxScore")
    List<UserIdentification> findByConfidenceScoreBetween(@Param("minScore") Double minScore,
                                                         @Param("maxScore") Double maxScore);
    
    /**
     * 查找用户最近的识别记录
     */
    @Query("SELECT ui FROM UserIdentification ui WHERE ui.userId = :userId ORDER BY ui.identificationTime DESC LIMIT 1")
    UserIdentification findLatestIdentificationByUser(@Param("userId") String userId);
}