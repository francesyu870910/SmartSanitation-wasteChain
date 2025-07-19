package com.smartwaste.domain.model;

/**
 * 设备状态枚举
 * Equipment Status Enum
 */
public enum EquipmentStatus {
    ONLINE("在线"),
    OFFLINE("离线"),
    MAINTENANCE("维护中"),
    ERROR("故障"),
    IDLE("空闲"),
    WORKING("工作中");

    private final String description;

    EquipmentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}