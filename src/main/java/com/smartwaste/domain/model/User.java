package com.smartwaste.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * 用户实体类
 * User Entity
 */
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @Column(name = "user_id", length = 64)
    private String userId;
    
    @NotBlank
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    
    @Pattern(regexp = "^1[3-9]\\d{9}$")
    @Column(name = "phone", length = 11, unique = true)
    private String phone;
    
    @Column(name = "address", length = 500)
    private String address;
    
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$")
    @Column(name = "id_card", length = 18, unique = true)
    private String idCard;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    // Constructors
    public User() {
        this.createdAt = LocalDateTime.now();
    }

    public User(String userId, String name, String phone) {
        this();
        this.userId = userId;
        this.name = name;
        this.phone = phone;
    }

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getIdCard() { return idCard; }
    public void setIdCard(String idCard) { this.idCard = idCard; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}