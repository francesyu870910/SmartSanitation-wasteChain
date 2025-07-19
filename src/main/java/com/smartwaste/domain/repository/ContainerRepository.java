package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.Container;
import com.smartwaste.domain.model.WasteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 垃圾桶数据访问接口
 * Container Repository Interface
 */
@Repository
public interface ContainerRepository extends JpaRepository<Container, String> {
    
    /**
     * 根据RFID标签查找垃圾桶
     */
    Optional<Container> findByRfidTag(String rfidTag);
    
    /**
     * 根据垃圾类型查找垃圾桶
     */
    List<Container> findByWasteType(WasteType wasteType);
    
    /**
     * 根据垃圾类型查找活跃垃圾桶
     */
    List<Container> findByWasteTypeAndIsActiveTrue(WasteType wasteType);
    
    /**
     * 查找需要清运的垃圾桶（满载率超过阈值）
     */
    @Query("SELECT c FROM Container c WHERE c.currentFullness >= :threshold AND c.isActive = true")
    List<Container> findContainersNeedingCollection(@Param("threshold") Double threshold);
    
    /**
     * 根据位置模糊查询垃圾桶
     */
    @Query("SELECT c FROM Container c WHERE c.location LIKE %:location% AND c.isActive = true")
    List<Container> findByLocationContaining(@Param("location") String location);
    
    /**
     * 查找活跃的垃圾桶
     */
    List<Container> findByIsActiveTrue();
}