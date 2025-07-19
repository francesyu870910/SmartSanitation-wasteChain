package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.UserPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户积分数据访问接口
 * User Points Repository Interface
 */
@Repository
public interface UserPointsRepository extends JpaRepository<UserPoints, String> {
    
    /**
     * 根据总积分倒序查找前N名用户
     */
    @Query("SELECT up FROM UserPoints up ORDER BY up.totalPoints DESC LIMIT :limit")
    List<UserPoints> findTopByOrderByTotalPointsDesc(@Param("limit") int limit);
    
    /**
     * 根据可用积分倒序查找前N名用户
     */
    @Query("SELECT up FROM UserPoints up ORDER BY up.availablePoints DESC LIMIT :limit")
    List<UserPoints> findTopByOrderByAvailablePointsDesc(@Param("limit") int limit);
    
    /**
     * 根据等级查找用户
     */
    List<UserPoints> findByLevel(Integer level);
    
    /**
     * 根据等级范围查找用户
     */
    @Query("SELECT up FROM UserPoints up WHERE up.level BETWEEN :minLevel AND :maxLevel")
    List<UserPoints> findByLevelBetween(@Param("minLevel") Integer minLevel, 
                                       @Param("maxLevel") Integer maxLevel);
    
    /**
     * 根据总积分范围查找用户
     */
    @Query("SELECT up FROM UserPoints up WHERE up.totalPoints BETWEEN :minPoints AND :maxPoints")
    List<UserPoints> findByTotalPointsBetween(@Param("minPoints") Integer minPoints, 
                                             @Param("maxPoints") Integer maxPoints);
    
    /**
     * 统计总用户数
     */
    @Query("SELECT COUNT(up) FROM UserPoints up")
    Long countTotalUsers();
    
    /**
     * 统计各等级用户数
     */
    @Query("SELECT up.level, COUNT(up) FROM UserPoints up GROUP BY up.level ORDER BY up.level")
    List<Object[]> countUsersByLevel();
    
    /**
     * 计算平均积分
     */
    @Query("SELECT AVG(up.totalPoints) FROM UserPoints up")
    Double calculateAveragePoints();
    
    /**
     * 查找积分大于指定值的用户
     */
    @Query("SELECT up FROM UserPoints up WHERE up.totalPoints > :points ORDER BY up.totalPoints DESC")
    List<UserPoints> findUsersWithPointsGreaterThan(@Param("points") Integer points);
    
    /**
     * 查找可用积分大于指定值的用户
     */
    @Query("SELECT up FROM UserPoints up WHERE up.availablePoints > :points ORDER BY up.availablePoints DESC")
    List<UserPoints> findUsersWithAvailablePointsGreaterThan(@Param("points") Integer points);
}