package com.smartwaste.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 垃圾溯源记录实体
 * 记录垃圾从投放到处置的完整链条信息
 */
@Entity
@Table(name = "traceability_records")
public class TraceabilityRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "waste_id", unique = true, nullable = false)
    private String wasteId; // 垃圾唯一标识（二维码/RFID）
    
    @Column(name = "user_id", nullable = false)
    private String userId; // 投放用户ID
    
    @Column(name = "user_name")
    private String userName; // 用户姓名
    
    @Column(name = "user_address")
    private String userAddress; // 用户地址
    
    @Column(name = "user_phone")
    private String userPhone; // 用户电话
    
    @Column(name = "disposal_time", nullable = false)
    private LocalDateTime disposalTime; // 投放时间
    
    @Column(name = "disposal_location")
    private String disposalLocation; // 投放地点
    
    @Column(name = "waste_type", nullable = false)
    private String wasteType; // 垃圾类型
    
    @Column(name = "weight")
    private Double weight; // 重量
    
    @Column(name = "container_id")
    private String containerId; // 垃圾桶ID
    
    @Column(name = "collection_time")
    private LocalDateTime collectionTime; // 收运时间
    
    @Column(name = "vehicle_id")
    private String vehicleId; // 收运车辆ID
    
    @Column(name = "driver_id")
    private String driverId; // 司机ID
    
    @Column(name = "collection_route")
    private String collectionRoute; // 收运路线
    
    @Column(name = "disposal_facility_id")
    private String disposalFacilityId; // 处置场所ID
    
    @Column(name = "disposal_method")
    private String disposalMethod; // 处置方式
    
    @Column(name = "final_disposal_time")
    private LocalDateTime finalDisposalTime; // 最终处置时间
    
    @Column(name = "status")
    private String status; // 状态：DISPOSED, COLLECTED, PROCESSED, COMPLETED
    
    @Column(name = "qr_code_data")
    private String qrCodeData; // 二维码数据
    
    @Column(name = "rfid_tag")
    private String rfidTag; // RFID标签
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // 构造函数
    public TraceabilityRecord() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getWasteId() {
        return wasteId;
    }
    
    public void setWasteId(String wasteId) {
        this.wasteId = wasteId;
    }
    
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
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getQrCodeData() {
        return qrCodeData;
    }
    
    public void setQrCodeData(String qrCodeData) {
        this.qrCodeData = qrCodeData;
    }
    
    public String getRfidTag() {
        return rfidTag;
    }
    
    public void setRfidTag(String rfidTag) {
        this.rfidTag = rfidTag;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}