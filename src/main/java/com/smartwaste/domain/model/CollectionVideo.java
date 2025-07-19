package com.smartwaste.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 收运视频记录实体类
 * Collection Video Record Entity
 */
@Entity
@Table(name = "collection_videos")
public class CollectionVideo {
    
    @Id
    @Column(name = "video_id", length = 64)
    private String videoId;
    
    @NotBlank
    @Column(name = "vehicle_id", length = 64, nullable = false)
    private String vehicleId;
    
    @Column(name = "container_id", length = 64)
    private String containerId;
    
    @NotBlank
    @Column(name = "file_path", length = 1000, nullable = false)
    private String filePath;
    
    @Column(name = "file_name", length = 255)
    private String fileName;
    
    @Column(name = "file_size")
    private Long fileSize;
    
    @Column(name = "duration")
    private Integer duration; // 视频时长（秒）
    
    @Column(name = "resolution", length = 20)
    private String resolution;
    
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    
    @Column(name = "end_time")
    private LocalDateTime endTime;
    
    @Column(name = "upload_time")
    private LocalDateTime uploadTime;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "video_type", nullable = false)
    private VideoType videoType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "quality", nullable = false)
    private VideoQuality quality = VideoQuality.NORMAL;
    
    @Column(name = "is_synchronized", nullable = false)
    private Boolean isSynchronized = false;
    
    @Column(name = "sync_time")
    private LocalDateTime syncTime;
    
    @Column(name = "notes", length = 1000)
    private String notes;

    // Constructors
    public CollectionVideo() {
        this.startTime = LocalDateTime.now();
    }

    public CollectionVideo(String vehicleId, String filePath, VideoType videoType) {
        this();
        this.vehicleId = vehicleId;
        this.filePath = filePath;
        this.videoType = videoType;
    }

    // Getters and Setters
    public String getVideoId() { return videoId; }
    public void setVideoId(String videoId) { this.videoId = videoId; }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public String getContainerId() { return containerId; }
    public void setContainerId(String containerId) { this.containerId = containerId; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public LocalDateTime getUploadTime() { return uploadTime; }
    public void setUploadTime(LocalDateTime uploadTime) { this.uploadTime = uploadTime; }

    public VideoType getVideoType() { return videoType; }
    public void setVideoType(VideoType videoType) { this.videoType = videoType; }

    public VideoQuality getQuality() { return quality; }
    public void setQuality(VideoQuality quality) { this.quality = quality; }

    public Boolean getIsSynchronized() { return isSynchronized; }
    public void setIsSynchronized(Boolean isSynchronized) { this.isSynchronized = isSynchronized; }

    public LocalDateTime getSyncTime() { return syncTime; }
    public void setSyncTime(LocalDateTime syncTime) { this.syncTime = syncTime; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    /**
     * 视频类型枚举
     */
    public enum VideoType {
        /**
         * 收运过程录像
         */
        COLLECTION("收运过程"),
        
        /**
         * 装载过程录像
         */
        LOADING("装载过程"),
        
        /**
         * 卸载过程录像
         */
        UNLOADING("卸载过程"),
        
        /**
         * 运输过程录像
         */
        TRANSPORT("运输过程"),
        
        /**
         * 异常事件录像
         */
        INCIDENT("异常事件");

        private final String displayName;

        VideoType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    /**
     * 视频质量枚举
     */
    public enum VideoQuality {
        /**
         * 高清
         */
        HIGH("高清"),
        
        /**
         * 标清
         */
        NORMAL("标清"),
        
        /**
         * 低清
         */
        LOW("低清");

        private final String displayName;

        VideoQuality(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}