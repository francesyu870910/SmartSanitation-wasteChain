package com.smartwaste.domain.model;

/**
 * 设备类型枚举
 * Device Type Enumeration
 */
public enum DeviceType {
    /**
     * 智能投放点
     */
    SMART_BIN("智能投放点"),
    
    /**
     * 人脸识别设备
     */
    FACE_RECOGNITION("人脸识别设备"),
    
    /**
     * 二维码扫描器
     */
    QR_SCANNER("二维码扫描器"),
    
    /**
     * IC卡读卡器
     */
    IC_CARD_READER("IC卡读卡器"),
    
    /**
     * 称重传感器
     */
    WEIGHT_SENSOR("称重传感器"),
    
    /**
     * GPS定位器
     */
    GPS_TRACKER("GPS定位器"),
    
    /**
     * 摄像头
     */
    CAMERA("摄像头"),
    
    /**
     * 满溢检测器
     */
    FULLNESS_SENSOR("满溢检测器");

    private final String displayName;

    DeviceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}