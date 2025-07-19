package com.smartwaste.domain.model;

public enum TaskStatus {
    PENDING("待处理"),
    ASSIGNED("已分配"),
    IN_PROGRESS("进行中"),
    COMPLETED("已完成"),
    CANCELLED("已取消"),
    FAILED("失败"),
    CONFIRMED("已确认"),
    REJECTED("已拒绝"),
    CHANGE_REQUESTED("请求修改");

    private final String description;

    TaskStatus(String description) {
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