package com.smartwaste.domain.service;

import com.smartwaste.domain.model.ViolationDetectionResult;
import com.smartwaste.domain.model.ViolationEvidence;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 违规检测服务接口
 */
public interface ViolationDetectionService {
    
    /**
     * 检测垃圾分类违规行为
     * @param deviceId 设备ID
     * @param userId 用户ID（如果已识别）
     * @param containerId 垃圾桶ID
     * @param expectedWasteType 期望的垃圾类型
     * @param actualWasteType 实际投放的垃圾类型
     * @param weight 重量
     * @param photoUrls 照片URL列表
     * @param videoUrl 视频URL
     * @param location 地点
     * @return 检测结果
     */
    ViolationDetectionResult detectViolation(String deviceId, String userId, String containerId,
                                           String expectedWasteType, String actualWasteType, 
                                           Double weight, List<String> photoUrls, String videoUrl, String location);
    
    /**
     * 保存违规证据
     * @param detectionResult 检测结果
     * @param userId 用户ID
     * @return 保存的违规证据记录
     */
    ViolationEvidence saveViolationEvidence(ViolationDetectionResult detectionResult, String userId);
    
    /**
     * 审核违规证据
     * @param violationId 违规ID
     * @param reviewerId 审核人员ID
     * @param confirmed 是否确认违规
     * @param reviewNotes 审核备注
     * @param enforcementAction 执法措施
     * @param fineAmount 罚款金额
     * @return 更新后的违规证据
     */
    ViolationEvidence reviewViolationEvidence(String violationId, String reviewerId, boolean confirmed, 
                                            String reviewNotes, String enforcementAction, Double fineAmount);
    
    /**
     * 获取违规证据详情
     * @param violationId 违规ID
     * @return 违规证据
     */
    ViolationEvidence getViolationEvidence(String violationId);
    
    /**
     * 查询用户的违规记录
     * @param userId 用户ID
     * @return 违规记录列表
     */
    List<ViolationEvidence> getUserViolations(String userId);
    
    /**
     * 查询用户在指定时间范围内的违规记录
     * @param userId 用户ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 违规记录列表
     */
    List<ViolationEvidence> getUserViolations(String userId, LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 根据地点查询违规记录
     * @param location 地点关键词
     * @return 违规记录列表
     */
    List<ViolationEvidence> getViolationsByLocation(String location);
    
    /**
     * 根据违规类型查询记录
     * @param violationType 违规类型
     * @return 违规记录列表
     */
    List<ViolationEvidence> getViolationsByType(String violationType);
    
    /**
     * 获取待审核的违规记录
     * @return 待审核的违规记录列表
     */
    List<ViolationEvidence> getPendingReviewViolations();
    
    /**
     * 获取高置信度的违规记录
     * @param minConfidence 最小置信度
     * @return 高置信度违规记录列表
     */
    List<ViolationEvidence> getHighConfidenceViolations(Double minConfidence);
    
    /**
     * 统计用户违规次数
     * @param userId 用户ID
     * @return 违规次数
     */
    Long getUserViolationCount(String userId);
    
    /**
     * 统计违规类型次数
     * @param violationType 违规类型
     * @return 违规次数
     */
    Long getViolationTypeCount(String violationType);
    
    /**
     * 获取需要发送教育内容的违规记录
     * @return 需要教育的违规记录列表
     */
    List<ViolationEvidence> getViolationsNeedingEducation();
    
    /**
     * 标记教育内容已发送
     * @param violationId 违规ID
     */
    void markEducationSent(String violationId);
    
    /**
     * 生成违规ID
     * @return 唯一的违规ID
     */
    String generateViolationId();
    
    /**
     * 自动检测图像中的违规行为（AI分析）
     * @param imageUrls 图像URL列表
     * @param expectedWasteType 期望的垃圾类型
     * @return 检测结果
     */
    ViolationDetectionResult analyzeImagesForViolation(List<String> imageUrls, String expectedWasteType);
    
    /**
     * 批量处理违规检测
     * @param detectionRequests 检测请求列表
     * @return 检测结果列表
     */
    List<ViolationDetectionResult> batchDetectViolations(List<ViolationDetectionRequest> detectionRequests);
    
    /**
     * 违规检测请求
     */
    class ViolationDetectionRequest {
        private String deviceId;
        private String userId;
        private String containerId;
        private String expectedWasteType;
        private String actualWasteType;
        private Double weight;
        private List<String> photoUrls;
        private String videoUrl;
        private String location;
        
        // Getters and Setters
        public String getDeviceId() {
            return deviceId;
        }
        
        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }
        
        public String getUserId() {
            return userId;
        }
        
        public void setUserId(String userId) {
            this.userId = userId;
        }
        
        public String getContainerId() {
            return containerId;
        }
        
        public void setContainerId(String containerId) {
            this.containerId = containerId;
        }
        
        public String getExpectedWasteType() {
            return expectedWasteType;
        }
        
        public void setExpectedWasteType(String expectedWasteType) {
            this.expectedWasteType = expectedWasteType;
        }
        
        public String getActualWasteType() {
            return actualWasteType;
        }
        
        public void setActualWasteType(String actualWasteType) {
            this.actualWasteType = actualWasteType;
        }
        
        public Double getWeight() {
            return weight;
        }
        
        public void setWeight(Double weight) {
            this.weight = weight;
        }
        
        public List<String> getPhotoUrls() {
            return photoUrls;
        }
        
        public void setPhotoUrls(List<String> photoUrls) {
            this.photoUrls = photoUrls;
        }
        
        public String getVideoUrl() {
            return videoUrl;
        }
        
        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }
        
        public String getLocation() {
            return location;
        }
        
        public void setLocation(String location) {
            this.location = location;
        }
    }
}