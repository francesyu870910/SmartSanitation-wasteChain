package com.smartwaste.domain.model;

/**
 * 设备状态枚举
 * Device Status Enumeration
 */
public enum DeviceStatus {
    /**
     * 在线
     */
    ONLINE("在线"),
    
    /**
     * 离线
     */
    OFFLINE("离线"),
    
    /**
     * 故障
     */
    ERROR("故障"),
    
    /**
     * 维护中
     */
    MAINTENANCE("维护中"),
    
    /**
     * 待激活
     */
    PENDING("待激活");

    private final String displayName;

    DeviceStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}