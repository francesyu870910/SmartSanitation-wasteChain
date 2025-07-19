package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 用户数据访问接口
 * User Repository Interface
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
    /**
     * 根据手机号查找用户
     */
    Optional<User> findByPhone(String phone);
    
    /**
     * 根据身份证号查找用户
     */
    Optional<User> findByIdCard(String idCard);
    
    /**
     * 查找活跃用户
     */
    @Query("SELECT u FROM User u WHERE u.isActive = true")
    java.util.List<User> findActiveUsers();
    
    /**
     * 根据地址模糊查询用户
     */
    @Query("SELECT u FROM User u WHERE u.address LIKE %:address% AND u.isActive = true")
    java.util.List<User> findByAddressContaining(@Param("address") String address);
}