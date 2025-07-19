package com.smartwaste.domain.service.impl;

import com.smartwaste.domain.model.User;
import com.smartwaste.domain.repository.UserRepository;
import com.smartwaste.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 用户服务实现类
 * User Service Implementation
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if (user.getUserId() == null || user.getUserId().isEmpty()) {
            user.setUserId(UUID.randomUUID().toString());
        }
        
        // 验证手机号唯一性
        if (user.getPhone() != null && userRepository.findByPhone(user.getPhone()).isPresent()) {
            throw new IllegalArgumentException("手机号已存在");
        }
        
        // 验证身份证号唯一性
        if (user.getIdCard() != null && userRepository.findByIdCard(user.getIdCard()).isPresent()) {
            throw new IllegalArgumentException("身份证号已存在");
        }
        
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getUserId());
        if (existingUser.isEmpty()) {
            throw new IllegalArgumentException("用户不存在");
        }
        
        // 检查手机号是否被其他用户使用
        if (user.getPhone() != null) {
            Optional<User> userWithPhone = userRepository.findByPhone(user.getPhone());
            if (userWithPhone.isPresent() && !userWithPhone.get().getUserId().equals(user.getUserId())) {
                throw new IllegalArgumentException("手机号已被其他用户使用");
            }
        }
        
        return userRepository.save(user);
    }

    @Override
    public void deactivateUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setIsActive(false);
            userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("用户不存在");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findActiveUsers() {
        return userRepository.findActiveUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findUsersByAddress(String address) {
        return userRepository.findByAddressContaining(address);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByIdCard(String idCard) {
        return userRepository.findByIdCard(idCard);
    }
}