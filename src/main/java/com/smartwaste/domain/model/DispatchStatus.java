package com.smartwaste.domain.model;

/**
 * 调度状态枚举
 * Dispatch Status Enumeration
 */
public enum DispatchStatus {
    /**
     * 待分配
     */
    PENDING("待分配"),
    
    /**
     * 已分配
     */
    ASSIGNED("已分配"),
    
    /**
     * 执行中
     */
    IN_PROGRESS("执行中"),
    
    /**
     * 已完成
     */
    COMPLETED("已完成"),
    
    /**
     * 已取消
     */
    CANCELLED("已取消"),
    
    /**
     * 失败
     */
    FAILED("失败");

    private final String displayName;

    DispatchStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}