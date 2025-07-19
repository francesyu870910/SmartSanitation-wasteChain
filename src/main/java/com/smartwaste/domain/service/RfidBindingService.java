package com.smartwaste.domain.service;

import com.smartwaste.domain.model.RfidBinding;
import java.util.List;
import java.util.Optional;

/**
 * RFID绑定服务接口
 * RFID Binding Service Interface
 */
public interface RfidBindingService {
    
    /**
     * 创建RFID绑定
     */
    RfidBinding createBinding(String containerId, String vehicleId, String rfidTag, String operatorId);
    
    /**
     * 解除RFID绑定
     */
    RfidBinding unbindRfid(String bindingId, String operatorId);
    
    /**
     * 根据RFID标签查找绑定
     */
    Optional<RfidBinding> findByRfidTag(String rfidTag);
    
    /**
     * 根据垃圾桶ID查找绑定
     */
    List<RfidBinding> findByContainerId(String containerId);
    
    /**
     * 根据车辆ID查找绑定
     */
    List<RfidBinding> findByVehicleId(String vehicleId);
    
    /**
     * 获取活跃的绑定记录
     */
    List<RfidBinding> getActiveBindings();
    
    /**
     * 验证RFID绑定有效性
     */
    boolean validateBinding(String containerId, String vehicleId);
    
    /**
     * 批量创建绑定
     */
    List<RfidBinding> createBatchBindings(List<RfidBindingRequest> requests, String operatorId);
    
    /**
     * 更新绑定信息
     */
    RfidBinding updateBinding(String bindingId, String notes);
    
    /**
     * RFID绑定请求类
     */
    class RfidBindingRequest {
        private String containerId;
        private String vehicleId;
        private String rfidTag;

        public RfidBindingRequest() {}

        public RfidBindingRequest(String containerId, String vehicleId, String rfidTag) {
            this.containerId = containerId;
            this.vehicleId = vehicleId;
            this.rfidTag = rfidTag;
        }

        // Getters and Setters
        public String getContainerId() { return containerId; }
        public void setContainerId(String containerId) { this.containerId = containerId; }
        public String getVehicleId() { return vehicleId; }
        public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }
        public String getRfidTag() { return rfidTag; }
        public void setRfidTag(String rfidTag) { this.rfidTag = rfidTag; }
    }
}