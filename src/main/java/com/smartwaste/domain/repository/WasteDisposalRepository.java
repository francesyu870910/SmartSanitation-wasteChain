package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.WasteDisposal;
import com.smartwaste.domain.model.WasteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 垃圾投放记录数据访问接口
 * Waste Disposal Repository Interface
 */
@Repository
public interface WasteDisposalRepository extends JpaRepository<WasteDisposal, String> {
    
    /**
     * 根据用户ID查找投放记录
     */
    List<WasteDisposal> findByUserId(String userId);
    
    /**
     * 根据垃圾桶ID查找投放记录
     */
    List<WasteDisposal> findByContainerId(String containerId);
    
    /**
     * 根据二维码查找投放记录
     */
    Optional<WasteDisposal> findByQrCode(String qrCode);
    
    /**
     * 根据时间范围查找投放记录
     */
    @Query("SELECT wd FROM WasteDisposal wd WHERE wd.disposalTime BETWEEN :startTime AND :endTime")
    List<WasteDisposal> findByDisposalTimeBetween(@Param("startTime") LocalDateTime startTime, 
                                                  @Param("endTime") LocalDateTime endTime);
    
    /**
     * 根据用户ID和时间范围查找投放记录
     */
    @Query("SELECT wd FROM WasteDisposal wd WHERE wd.userId = :userId AND wd.disposalTime BETWEEN :startTime AND :endTime")
    List<WasteDisposal> findByUserIdAndDisposalTimeBetween(@Param("userId") String userId,
                                                           @Param("startTime") LocalDateTime startTime,
                                                           @Param("endTime") LocalDateTime endTime);
    
    /**
     * 统计用户总积分
     */
    @Query("SELECT COALESCE(SUM(wd.pointsEarned), 0) FROM WasteDisposal wd WHERE wd.userId = :userId")
    Integer getTotalPointsByUserId(@Param("userId") String userId);
    
    /**
     * 根据垃圾类型统计投放量
     */
    @Query("SELECT COALESCE(SUM(wd.weight), 0.0) FROM WasteDisposal wd WHERE wd.wasteType = :wasteType AND wd.disposalTime BETWEEN :startTime AND :endTime")
    Double getTotalWeightByTypeAndTimeBetween(@Param("wasteType") WasteType wasteType,
                                              @Param("startTime") LocalDateTime startTime,
                                              @Param("endTime") LocalDateTime endTime);
    
    /**
     * 查找错误分类的投放记录
     */
    List<WasteDisposal> findByIsCorrectClassificationFalse();
}