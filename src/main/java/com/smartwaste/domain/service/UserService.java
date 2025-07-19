package com.smartwaste.domain.service;

import com.smartwaste.domain.model.User;
import java.util.List;
import java.util.Optional;

/**
 * 用户服务接口
 * User Service Interface
 */
public interface UserService {
    
    /**
     * 创建用户
     */
    User createUser(User user);
    
    /**
     * 根据用户ID查找用户
     */
    Optional<User> findById(String userId);
    
    /**
     * 根据手机号查找用户
     */
    Optional<User> findByPhone(String phone);
    
    /**
     * 更新用户信息
     */
    User updateUser(User user);
    
    /**
     * 停用用户
     */
    void deactivateUser(String userId);
    
    /**
     * 获取所有活跃用户
     */
    List<User> findActiveUsers();
    
    /**
     * 根据地址查找用户
     */
    List<User> findUsersByAddress(String address);
    
    /**
     * 根据身份证号查找用户
     */
    Optional<User> findByIdCard(String idCard);
}