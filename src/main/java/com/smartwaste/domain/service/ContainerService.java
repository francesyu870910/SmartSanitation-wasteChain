package com.smartwaste.domain.service;

import com.smartwaste.domain.model.Container;
import com.smartwaste.domain.model.WasteType;
import java.util.List;
import java.util.Optional;

/**
 * 垃圾桶服务接口
 * Container Service Interface
 */
public interface ContainerService {
    
    /**
     * 创建垃圾桶
     */
    Container createContainer(Container container);
    
    /**
     * 根据垃圾桶ID查找垃圾桶
     */
    Optional<Container> findById(String containerId);
    
    /**
     * 根据RFID标签查找垃圾桶
     */
    Optional<Container> findByRfidTag(String rfidTag);
    
    /**
     * 更新垃圾桶满载率
     */
    Container updateFullness(String containerId, Double fullness);
    
    /**
     * 获取需要清运的垃圾桶
     */
    List<Container> getContainersNeedingCollection(Double threshold);
    
    /**
     * 根据垃圾类型查找垃圾桶
     */
    List<Container> findByWasteType(WasteType wasteType);
    
    /**
     * 根据位置查找垃圾桶
     */
    List<Container> findByLocation(String location);
    
    /**
     * 停用垃圾桶
     */
    void deactivateContainer(String containerId);
}