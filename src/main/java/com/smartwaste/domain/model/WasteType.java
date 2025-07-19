package com.smartwaste.domain.model;

/**
 * 垃圾类型枚举
 * Waste Type Enumeration
 */
public enum WasteType {
    /**
     * 厨余垃圾
     */
    KITCHEN("厨余垃圾", 2.0),
    
    /**
     * 有害垃圾
     */
    HAZARDOUS("有害垃圾", 5.0),
    
    /**
     * 可回收垃圾
     */
    RECYCLABLE("可回收垃圾", 3.0),
    
    /**
     * 其他垃圾
     */
    OTHER("其他垃圾", 1.0);

    private final String displayName;
    private final Double pointsCoefficient;

    WasteType(String displayName, Double pointsCoefficient) {
        this.displayName = displayName;
        this.pointsCoefficient = pointsCoefficient;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Double getPointsCoefficient() {
        return pointsCoefficient;
    }
}