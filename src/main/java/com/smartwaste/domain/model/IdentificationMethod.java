package com.smartwaste.domain.model;

/**
 * 身份识别方式枚举
 * Identification Method Enumeration
 */
public enum IdentificationMethod {
    /**
     * 人脸识别
     */
    FACE_RECOGNITION("人脸识别"),
    
    /**
     * 二维码扫描
     */
    QR_CODE("二维码扫描"),
    
    /**
     * IC卡读取
     */
    IC_CARD("IC卡读取"),
    
    /**
     * 手机号验证
     */
    PHONE_VERIFICATION("手机号验证"),
    
    /**
     * 身份证识别
     */
    ID_CARD("身份证识别");

    private final String displayName;

    IdentificationMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}