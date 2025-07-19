package com.smartwaste.domain.service;

import com.smartwaste.domain.model.IdentificationMethod;
import com.smartwaste.domain.model.User;
import com.smartwaste.domain.model.UserIdentification;
import java.util.List;
import java.util.Optional;

/**
 * 身份识别服务接口
 * Identification Service Interface
 */
public interface IdentificationService {
    
    /**
     * 人脸识别
     */
    Optional<User> recognizeFace(String faceImageData, String deviceId, String location);
    
    /**
     * 二维码识别
     */
    Optional<User> recognizeQrCode(String qrCodeData, String deviceId, String location);
    
    /**
     * IC卡识别
     */
    Optional<User> recognizeIcCard(String cardData, String deviceId, String location);
    
    /**
     * 手机号验证
     */
    Optional<User> verifyPhone(String phoneNumber, String verificationCode, String deviceId, String location);
    
    /**
     * 身份证识别
     */
    Optional<User> recognizeIdCard(String idCardData, String deviceId, String location);
    
    /**
     * 活体检测
     */
    boolean performLivenessDetection(String faceImageData);
    
    /**
     * 记录识别结果
     */
    UserIdentification recordIdentification(UserIdentification identification);
    
    /**
     * 获取用户识别历史
     */
    List<UserIdentification> getUserIdentificationHistory(String userId);
    
    /**
     * 获取设备识别统计
     */
    List<UserIdentification> getDeviceIdentificationStats(String deviceId);
    
    /**
     * 验证识别准确性
     */
    double calculateIdentificationAccuracy(String deviceId, IdentificationMethod method);
}