package com.smartwaste.domain.repository;

import com.smartwaste.domain.model.EducationContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 教育内容数据访问接口
 */
@Repository
public interface EducationContentRepository extends JpaRepository<EducationContent, Long> {
    
    /**
     * 根据内容ID查找
     */
    Optional<EducationContent> findByContentId(String contentId);
    
    /**
     * 根据分类查找激活的内容
     */
    List<EducationContent> findByCategoryAndIsActiveTrueOrderByPriorityDescCreatedAtDesc(String category);
    
    /**
     * 根据垃圾类型查找相关内容
     */
    List<EducationContent> findByWasteTypeAndIsActiveTrueOrderByPriorityDescCreatedAtDesc(String wasteType);
    
    /**
     * 根据违规类型查找相关内容
     */
    List<EducationContent> findByViolationTypeAndIsActiveTrueOrderByPriorityDescCreatedAtDesc(String violationType);
    
    /**
     * 根据目标受众查找内容
     */
    List<EducationContent> findByTargetAudienceAndIsActiveTrueOrderByPriorityDescCreatedAtDesc(String targetAudience);
    
    /**
     * 根据审核状态查找
     */
    List<EducationContent> findByReviewStatusOrderByCreatedAtDesc(String reviewStatus);
    
    /**
     * 查找高效果评分的内容
     */
    @Query("SELECT c FROM EducationContent c WHERE c.effectivenessScore >= :minScore " +
           "AND c.isActive = true ORDER BY c.effectivenessScore DESC")
    List<EducationContent> findHighEffectivenessContent(@Param("minScore") Double minScore);
    
    /**
     * 根据标签搜索内容
     */
    @Query("SELECT c FROM EducationContent c WHERE c.tags LIKE %:tag% " +
           "AND c.isActive = true ORDER BY c.priority DESC")
    List<EducationContent> findByTagsContaining(@Param("tag") String tag);
}