package com.smartwaste.domain.service;

import com.smartwaste.domain.model.CollectionTask;
import com.smartwaste.domain.model.CollectionVideo;
import com.smartwaste.domain.model.GpsTrack;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 收运监控服务接口
 * Collection Monitoring Service Interface
 */
public interface CollectionMonitoringService {
    
    /**
     * 记录GPS轨迹
     */
    GpsTrack recordGpsTrack(String vehicleId, Double latitude, Double longitude, 
                           Double speed, Double direction);
    
    /**
     * 开始视频录制
     */
    CollectionVideo startVideoRecording(String vehicleId, String containerId, 
                                      CollectionVideo.VideoType videoType);
    
    /**
     * 停止视频录制
     */
    CollectionVideo stopVideoRecording(String videoId);
    
    /**
     * 同步视频到云端
     */
    boolean synchronizeVideo(String videoId);
    
    /**
     * 创建收运任务
     */
    CollectionTask createCollectionTask(String vehicleId, String driverId, String routeName);
    
    /**
     * 开始执行任务
     */
    CollectionTask startTask(String taskId);
    
    /**
     * 完成任务
     */
    CollectionTask completeTask(String taskId);
    
    /**
     * 更新任务进度
     */
    CollectionTask updateTaskProgress(String taskId, Integer completedContainers, Double totalWeight);
    
    /**
     * 获取车辆实时位置
     */
    Optional<GpsTrack> getVehicleCurrentLocation(String vehicleId);
    
    /**
     * 获取车辆轨迹历史
     */
    List<GpsTrack> getVehicleTrackHistory(String vehicleId, LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 获取收运视频列表
     */
    List<CollectionVideo> getCollectionVideos(String vehicleId, LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 获取活跃收运任务
     */
    List<CollectionTask> getActiveTasks();
    
    /**
     * 获取车辆当前任务
     */
    Optional<CollectionTask> getVehicleCurrentTask(String vehicleId);
    
    /**
     * 计算行驶距离
     */
    Double calculateTravelDistance(String vehicleId, LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 验证轨迹精度
     */
    boolean validateTrackAccuracy(GpsTrack track);
    
    /**
     * 验证视频质量
     */
    boolean validateVideoQuality(CollectionVideo video);
    
    /**
     * 获取收运统计信息
     */
    CollectionStatistics getCollectionStatistics(LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 收运统计信息类
     */
    class CollectionStatistics {
        private long totalTasks;
        private long completedTasks;
        private long activeTasks;
        private double totalDistance;
        private double averageSpeed;
        private long totalVideos;
        private long totalVideoSize;
        private double completionRate;

        // Constructors
        public CollectionStatistics() {}

        public CollectionStatistics(long totalTasks, long completedTasks, long activeTasks,
                                  double totalDistance, double averageSpeed, long totalVideos,
                                  long totalVideoSize, double completionRate) {
            this.totalTasks = totalTasks;
            this.completedTasks = completedTasks;
            this.activeTasks = activeTasks;
            this.totalDistance = totalDistance;
            this.averageSpeed = averageSpeed;
            this.totalVideos = totalVideos;
            this.totalVideoSize = totalVideoSize;
            this.completionRate = completionRate;
        }

        // Getters and Setters
        public long getTotalTasks() { return totalTasks; }
        public void setTotalTasks(long totalTasks) { this.totalTasks = totalTasks; }
        public long getCompletedTasks() { return completedTasks; }
        public void setCompletedTasks(long completedTasks) { this.completedTasks = completedTasks; }
        public long getActiveTasks() { return activeTasks; }
        public void setActiveTasks(long activeTasks) { this.activeTasks = activeTasks; }
        public double getTotalDistance() { return totalDistance; }
        public void setTotalDistance(double totalDistance) { this.totalDistance = totalDistance; }
        public double getAverageSpeed() { return averageSpeed; }
        public void setAverageSpeed(double averageSpeed) { this.averageSpeed = averageSpeed; }
        public long getTotalVideos() { return totalVideos; }
        public void setTotalVideos(long totalVideos) { this.totalVideos = totalVideos; }
        public long getTotalVideoSize() { return totalVideoSize; }
        public void setTotalVideoSize(long totalVideoSize) { this.totalVideoSize = totalVideoSize; }
        public double getCompletionRate() { return completionRate; }
        public void setCompletionRate(double completionRate) { this.completionRate = completionRate; }
    }
}