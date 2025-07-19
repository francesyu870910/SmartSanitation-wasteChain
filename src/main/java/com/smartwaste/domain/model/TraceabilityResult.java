package com.smartwaste.domain.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 溯源查询结果
 */
public class TraceabilityResult {
    
    private String wasteId;
    private boolean found;
    private String message;
    private TraceabilityChain traceabilityChain;
    
    // 构造函数
    public TraceabilityResult() {}
    
    public TraceabilityResult(String wasteId, boolean found, String message) {
        this.wasteId = wasteId;
        this.found = found;
        this.message = message;
    }
    
    // 溯源链条信息
    public static class TraceabilityChain {
        private DisposalInfo disposalInfo;
        private CollectionInfo collectionInfo;
        private ProcessingInfo processingInfo;
        private List<TraceabilityStep> steps;
        
        // Getters and Setters
        public DisposalInfo getDisposalInfo() {
            return disposalInfo;
        }
        
        public void setDisposalInfo(DisposalInfo disposalInfo) {
            this.disposalInfo = disposalInfo;
        }
        
        public CollectionInfo getCollectionInfo() {
            return collectionInfo;
        }
        
        public void setCollectionInfo(CollectionInfo collectionInfo) {
            this.collectionInfo = collectionInfo;
        }
        
        public ProcessingInfo getProcessingInfo() {
            return processingInfo;
        }
        
        public void setProcessingInfo(ProcessingInfo processingInfo) {
            this.processingInfo = processingInfo;
        }
        
        public List<TraceabilityStep> getSteps() {
            return steps;
        }
        
        public void setSteps(List<TraceabilityStep> steps) {
            this.steps = steps;
        }
    }
    
    // 投放信息
    public static class DisposalInfo {
        private String userId;
        private String userName;
        private String userAddress;
        private String userPhone;
        private LocalDateTime disposalTime;
        private String disposalLocation;
        private String wasteType;
        private Double weight;
        private String containerId;
        
        // Getters and Setters
        public String getUserId() {
            return userId;
        }
        
        public void setUserId(String userId) {
            this.userId = userId;
        }
        
        public String getUserName() {
            return userName;
        }
        
        public void setUserName(String userName) {
            this.userName = userName;
        }
        
        public String getUserAddress() {
            return userAddress;
        }
        
        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }
        
        public String getUserPhone() {
            return userPhone;
        }
        
        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }
        
        public LocalDateTime getDisposalTime() {
            return disposalTime;
        }
        
        public void setDisposalTime(LocalDateTime disposalTime) {
            this.disposalTime = disposalTime;
        }
        
        public String getDisposalLocation() {
            return disposalLocation;
        }
        
        public void setDisposalLocation(String disposalLocation) {
            this.disposalLocation = disposalLocation;
        }
        
        public String getWasteType() {
            return wasteType;
        }
        
        public void setWasteType(String wasteType) {
            this.wasteType = wasteType;
        }
        
        public Double getWeight() {
            return weight;
        }
        
        public void setWeight(Double weight) {
            this.weight = weight;
        }
        
        public String getContainerId() {
            return containerId;
        }
        
        public void setContainerId(String containerId) {
            this.containerId = containerId;
        }
    }
    
    // 收运信息
    public static class CollectionInfo {
        private LocalDateTime collectionTime;
        private String vehicleId;
        private String driverId;
        private String collectionRoute;
        
        // Getters and Setters
        public LocalDateTime getCollectionTime() {
            return collectionTime;
        }
        
        public void setCollectionTime(LocalDateTime collectionTime) {
            this.collectionTime = collectionTime;
        }
        
        public String getVehicleId() {
            return vehicleId;
        }
        
        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }
        
        public String getDriverId() {
            return driverId;
        }
        
        public void setDriverId(String driverId) {
            this.driverId = driverId;
        }
        
        public String getCollectionRoute() {
            return collectionRoute;
        }
        
        public void setCollectionRoute(String collectionRoute) {
            this.collectionRoute = collectionRoute;
        }
    }
    
    // 处置信息
    public static class ProcessingInfo {
        private String disposalFacilityId;
        private String disposalMethod;
        private LocalDateTime finalDisposalTime;
        
        // Getters and Setters
        public String getDisposalFacilityId() {
            return disposalFacilityId;
        }
        
        public void setDisposalFacilityId(String disposalFacilityId) {
            this.disposalFacilityId = disposalFacilityId;
        }
        
        public String getDisposalMethod() {
            return disposalMethod;
        }
        
        public void setDisposalMethod(String disposalMethod) {
            this.disposalMethod = disposalMethod;
        }
        
        public LocalDateTime getFinalDisposalTime() {
            return finalDisposalTime;
        }
        
        public void setFinalDisposalTime(LocalDateTime finalDisposalTime) {
            this.finalDisposalTime = finalDisposalTime;
        }
    }
    
    // 溯源步骤
    public static class TraceabilityStep {
        private String stepName;
        private String description;
        private LocalDateTime timestamp;
        private String location;
        private String operator;
        
        public TraceabilityStep(String stepName, String description, LocalDateTime timestamp, String location, String operator) {
            this.stepName = stepName;
            this.description = description;
            this.timestamp = timestamp;
            this.location = location;
            this.operator = operator;
        }
        
        // Getters and Setters
        public String getStepName() {
            return stepName;
        }
        
        public void setStepName(String stepName) {
            this.stepName = stepName;
        }
        
        public String getDescription() {
            return description;
        }
        
        public void setDescription(String description) {
            this.description = description;
        }
        
        public LocalDateTime getTimestamp() {
            return timestamp;
        }
        
        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }
        
        public String getLocation() {
            return location;
        }
        
        public void setLocation(String location) {
            this.location = location;
        }
        
        public String getOperator() {
            return operator;
        }
        
        public void setOperator(String operator) {
            this.operator = operator;
        }
    }
    
    // Getters and Setters
    public String getWasteId() {
        return wasteId;
    }
    
    public void setWasteId(String wasteId) {
        this.wasteId = wasteId;
    }
    
    public boolean isFound() {
        return found;
    }
    
    public void setFound(boolean found) {
        this.found = found;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public TraceabilityChain getTraceabilityChain() {
        return traceabilityChain;
    }
    
    public void setTraceabilityChain(TraceabilityChain traceabilityChain) {
        this.traceabilityChain = traceabilityChain;
    }
}