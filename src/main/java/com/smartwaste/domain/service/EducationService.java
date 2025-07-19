package com.smartwaste.domain.service;

import com.smartwaste.domain.model.EducationContent;
import com.smartwaste.domain.model.EducationRecord;
import com.smartwaste.domain.model.ViolationEvidence;

import java.util.List;

/**
 * 教育服务接口
 */
public interface EducationService {
    
    /**
     * 生成个性化分类指导内容
     * @param userId 用户ID
     * @param violationEvidence 违规证据（可选）
     * @return 推荐的教育内容列表
     */
    List<EducationContent> generatePersonalizedGuidance(String userId, ViolationEvidence violationEvidence);
    
    /**
     * 推送教育内容给用户
     * @param userId 用户ID
     * @param contentId 内容ID
     * @param deliveryMethod 推送方式
     * @param violationId 关联的违规ID（可选）
     * @return 教育记录
     */
    EducationRecord pushEducationContent(String userId, String contentId, String deliveryMethod, String violationId);
    
    /**
     * 批量推送教育内容
     * @param userIds 用户ID列表
     * @param contentId 内容ID
     * @param deliveryMethod 推送方式
     * @return 教育记录列表
     */
    List<EducationRecord> batchPushEducationContent(List<String> userIds, String contentId, String deliveryMethod);
    
    /**
     * 记录用户交互行为
     * @param recordId 教育记录ID
     * @param interactionType 交互类型
     * @param viewDuration 查看时长
     * @param completionRate 完成率
     * @param quizScore 测验得分
     */
    void recordUserInteraction(String recordId, String interactionType, Integer viewDuration, 
                             Double completionRate, Integer quizScore);
    
    /**
     * 记录用户反馈
     * @param recordId 教育记录ID
     * @param rating 评分
     * @param comment 评论
     */
    void recordUserFeedback(String recordId, Integer rating, String comment);
    
    /**
     * 获取用户的教育记录
     * @param userId 用户ID
     * @return 教育记录列表
     */
    List<EducationRecord> getUserEducationRecords(String userId);
    
    /**
     * 获取内容的推送记录
     * @param contentId 内容ID
     * @return 教育记录列表
     */
    List<EducationRecord> getContentDeliveryRecords(String contentId);
    
    /**
     * 评估教育效果
     * @param userId 用户ID
     * @param contentId 内容ID
     * @return 效果评分
     */
    Double evaluateEducationEffectiveness(String userId, String contentId);
    
    /**
     * 创建教育内容
     * @param title 标题
     * @param content 内容
     * @param contentType 内容类型
     * @param category 分类
     * @param wasteType 垃圾类型
     * @param violationType 违规类型
     * @param targetAudience 目标受众
     * @param author 作者
     * @return 创建的教育内容
     */
    EducationContent createEducationContent(String title, String content, String contentType, 
                                          String category, String wasteType, String violationType,
                                          String targetAudience, String author);
    
    /**
     * 更新教育内容
     * @param contentId 内容ID
     * @param title 标题
     * @param content 内容
     * @param tags 标签
     * @param priority 优先级
     * @return 更新后的内容
     */
    EducationContent updateEducationContent(String contentId, String title, String content, 
                                          String tags, Integer priority);
    
    /**
     * 审核教育内容
     * @param contentId 内容ID
     * @param reviewer 审核人
     * @param approved 是否通过
     * @return 更新后的内容
     */
    EducationContent reviewEducationContent(String contentId, String reviewer, boolean approved);
    
    /**
     * 发布教育内容
     * @param contentId 内容ID
     * @return 发布后的内容
     */
    EducationContent publishEducationContent(String contentId);
    
    /**
     * 根据分类获取教育内容
     * @param category 分类
     * @return 教育内容列表
     */
    List<EducationContent> getEducationContentByCategory(String category);
    
    /**
     * 根据垃圾类型获取相关教育内容
     * @param wasteType 垃圾类型
     * @return 教育内容列表
     */
    List<EducationContent> getEducationContentByWasteType(String wasteType);
    
    /**
     * 根据违规类型获取相关教育内容
     * @param violationType 违规类型
     * @return 教育内容列表
     */
    List<EducationContent> getEducationContentByViolationType(String violationType);
    
    /**
     * 获取高效果的教育内容
     * @param minScore 最小效果评分
     * @return 教育内容列表
     */
    List<EducationContent> getHighEffectivenessContent(Double minScore);
    
    /**
     * 搜索教育内容
     * @param keyword 关键词
     * @return 教育内容列表
     */
    List<EducationContent> searchEducationContent(String keyword);
    
    /**
     * 统计用户的教育记录数量
     * @param userId 用户ID
     * @return 记录数量
     */
    Long getUserEducationCount(String userId);
    
    /**
     * 统计内容的推送次数
     * @param contentId 内容ID
     * @return 推送次数
     */
    Long getContentDeliveryCount(String contentId);
    
    /**
     * 计算内容的平均效果评分
     * @param contentId 内容ID
     * @return 平均效果评分
     */
    Double getContentAverageEffectiveness(String contentId);
    
    /**
     * 生成教育记录ID
     * @return 唯一的记录ID
     */
    String generateEducationRecordId();
    
    /**
     * 生成教育内容ID
     * @return 唯一的内容ID
     */
    String generateEducationContentId();
}