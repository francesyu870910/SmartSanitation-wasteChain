package com.smartwaste.domain.model;

/**
 * 车辆状态枚举
 * Vehicle Status Enumeration
 */
public enum VehicleStatus {
    /**
     * 空闲
     */
    IDLE("空闲"),
    
    /**
     * 收运中
     */
    COLLECTING("收运中"),
    
    /**
     * 运输中
     */
    TRANSPORTING("运输中"),
    
    /**
     * 维护中
     */
    MAINTENANCE("维护中"),
    
    /**
     * 离线
     */
    OFFLINE("离线");

    private final String displayName;

    VehicleStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}