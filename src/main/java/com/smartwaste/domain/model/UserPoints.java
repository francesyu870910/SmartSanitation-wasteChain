package com.smartwaste.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 用户积分账户实体类
 * User Points Account Entity
 */
@Entity
@Table(name = "user_points")
public class UserPoints {
    
    @Id
    @Column(name = "user_id", length = 64)
    private String userId;
    
    @Column(name = "total_points", nullable = false)
    private Integer totalPoints = 0;
    
    @Column(name = "available_points", nullable = false)
    private Integer availablePoints = 0;
    
    @Column(name = "used_points", nullable = false)
    private Integer usedPoints = 0;
    
    @Column(name = "level", nullable = false)
    private Integer level = 1;
    
    @Column(name = "level_name", length = 50)
    private String levelName = "新手";
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Constructors
    public UserPoints() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public UserPoints(String userId) {
        this();
        this.userId = userId;
    }

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public Integer getTotalPoints() { return totalPoints; }
    public void setTotalPoints(Integer totalPoints) { this.totalPoints = totalPoints; }

    public Integer getAvailablePoints() { return availablePoints; }
    public void setAvailablePoints(Integer availablePoints) { this.availablePoints = availablePoints; }

    public Integer getUsedPoints() { return usedPoints; }
    public void setUsedPoints(Integer usedPoints) { this.usedPoints = usedPoints; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    public String getLevelName() { return levelName; }
    public void setLevelName(String levelName) { this.levelName = levelName; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}