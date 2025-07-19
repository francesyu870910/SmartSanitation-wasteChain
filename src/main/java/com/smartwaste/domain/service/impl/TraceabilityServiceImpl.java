package com.smartwaste.domain.service.impl;

import com.smartwaste.domain.model.TraceabilityRecord;
import com.smartwaste.domain.model.TraceabilityResult;
import com.smartwaste.domain.model.User;
import com.smartwaste.domain.repository.TraceabilityRecordRepository;
import com.smartwaste.domain.service.TraceabilityService;
import com.smartwaste.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 溯源服务实现类
 */
@Service
@Transactional
public class TraceabilityServiceImpl implements TraceabilityService {
    
    @Autowired
    private TraceabilityRecordRepository traceabilityRecordRepository;
    
    @Autowired
    private UserService userService;
    
    @Override
    public TraceabilityRecord createTraceabilityRecord(String userId, String wasteId, String wasteType, 
                                                     Double weight, String containerId, String disposalLocation,
                                                     String qrCodeData, String rfidTag) {
        TraceabilityRecord record = new TraceabilityRecord();
        record.setWasteId(wasteId);
        record.setUserId(userId);
        record.setWasteType(wasteType);
        record.setWeight(weight);
        record.setContainerId(containerId);
        record.setDisposalLocation(disposalLocation);
        record.setQrCodeData(qrCodeData);
        record.setRfidTag(rfidTag);
        record.setDisposalTime(LocalDateTime.now());
        record.setStatus("DISPOSED");
        
        // 获取用户信息并填充
        Optional<User> userOpt = userService.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            record.setUserName(user.getName());
            record.setUserAddress(user.getAddress());
            record.setUserPhone(user.getPhone());
        }
        
        return traceabilityRecordRepository.save(record);
    }
    
    @Override
    public TraceabilityRecord updateCollectionInfo(String wasteId, String vehicleId, String driverId, String collectionRoute) {
        Optional<TraceabilityRecord> recordOpt = traceabilityRecordRepository.findByWasteId(wasteId);
        if (recordOpt.isPresent()) {
            TraceabilityRecord record = recordOpt.get();
            record.setVehicleId(vehicleId);
            record.setDriverId(driverId);
            record.setCollectionRoute(collectionRoute);
            record.setCollectionTime(LocalDateTime.now());
            record.setStatus("COLLECTED");
            return traceabilityRecordRepository.save(record);
        }
        throw new RuntimeException("未找到垃圾ID为 " + wasteId + " 的溯源记录");
    }
    
    @Override
    public TraceabilityRecord updateDisposalInfo(String wasteId, String disposalFacilityId, String disposalMethod) {
        Optional<TraceabilityRecord> recordOpt = traceabilityRecordRepository.findByWasteId(wasteId);
        if (recordOpt.isPresent()) {
            TraceabilityRecord record = recordOpt.get();
            record.setDisposalFacilityId(disposalFacilityId);
            record.setDisposalMethod(disposalMethod);
            record.setFinalDisposalTime(LocalDateTime.now());
            record.setStatus("COMPLETED");
            return traceabilityRecordRepository.save(record);
        }
        throw new RuntimeException("未找到垃圾ID为 " + wasteId + " 的溯源记录");
    }    
    @
Override
    public TraceabilityResult traceWasteToSource(String wasteId) {
        Optional<TraceabilityRecord> recordOpt = traceabilityRecordRepository.findByWasteId(wasteId);
        if (recordOpt.isEmpty()) {
            return new TraceabilityResult(wasteId, false, "未找到该垃圾的溯源记录");
        }
        
        TraceabilityRecord record = recordOpt.get();
        return buildTraceabilityResult(record);
    }
    
    @Override
    public TraceabilityResult traceByQRCode(String qrCodeData) {
        Optional<TraceabilityRecord> recordOpt = traceabilityRecordRepository.findByQrCodeData(qrCodeData);
        if (recordOpt.isEmpty()) {
            return new TraceabilityResult(qrCodeData, false, "未找到该二维码对应的溯源记录");
        }
        
        TraceabilityRecord record = recordOpt.get();
        return buildTraceabilityResult(record);
    }
    
    @Override
    public TraceabilityResult traceByRFID(String rfidTag) {
        Optional<TraceabilityRecord> recordOpt = traceabilityRecordRepository.findByRfidTag(rfidTag);
        if (recordOpt.isEmpty()) {
            return new TraceabilityResult(rfidTag, false, "未找到该RFID标签对应的溯源记录");
        }
        
        TraceabilityRecord record = recordOpt.get();
        return buildTraceabilityResult(record);
    }
    
    @Override
    public List<TraceabilityRecord> getUserTraceabilityRecords(String userId) {
        return traceabilityRecordRepository.findByUserIdOrderByDisposalTimeDesc(userId);
    }
    
    @Override
    public List<TraceabilityRecord> getUserTraceabilityRecords(String userId, LocalDateTime startTime, LocalDateTime endTime) {
        return traceabilityRecordRepository.findByUserIdAndTimeRange(userId, startTime, endTime);
    }
    
    @Override
    public List<TraceabilityRecord> findRecordsByAddress(String address) {
        return traceabilityRecordRepository.findByUserAddressContaining(address);
    }
    
    @Override
    public List<TraceabilityRecord> getRecordsByWasteType(String wasteType) {
        return traceabilityRecordRepository.findByWasteTypeOrderByDisposalTimeDesc(wasteType);
    }
    
    @Override
    public List<TraceabilityRecord> getRecordsByVehicle(String vehicleId) {
        return traceabilityRecordRepository.findByVehicleIdOrderByCollectionTimeDesc(vehicleId);
    }
    
    @Override
    public List<TraceabilityRecord> getRecordsByFacility(String facilityId) {
        return traceabilityRecordRepository.findByDisposalFacilityIdOrderByFinalDisposalTimeDesc(facilityId);
    }
    
    @Override
    public List<TraceabilityRecord> getIncompleteRecords(int cutoffHours) {
        LocalDateTime cutoffTime = LocalDateTime.now().minusHours(cutoffHours);
        return traceabilityRecordRepository.findIncompleteRecords(cutoffTime);
    }
    
    @Override
    public Long getUserDisposalCount(String userId) {
        return traceabilityRecordRepository.countByUserId(userId);
    }
    
    @Override
    public Long getWasteTypeCount(String wasteType) {
        return traceabilityRecordRepository.countByWasteType(wasteType);
    }
    
    @Override
    public String generateWasteId() {
        // 生成格式：WS + 年月日 + 6位随机数
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomStr = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
        return "WS" + dateStr + randomStr;
    }   
 
    /**
     * 构建溯源结果对象
     */
    private TraceabilityResult buildTraceabilityResult(TraceabilityRecord record) {
        TraceabilityResult result = new TraceabilityResult(record.getWasteId(), true, "溯源成功");
        
        // 构建溯源链条
        TraceabilityResult.TraceabilityChain chain = new TraceabilityResult.TraceabilityChain();
        
        // 投放信息
        TraceabilityResult.DisposalInfo disposalInfo = new TraceabilityResult.DisposalInfo();
        disposalInfo.setUserId(record.getUserId());
        disposalInfo.setUserName(record.getUserName());
        disposalInfo.setUserAddress(record.getUserAddress());
        disposalInfo.setUserPhone(record.getUserPhone());
        disposalInfo.setDisposalTime(record.getDisposalTime());
        disposalInfo.setDisposalLocation(record.getDisposalLocation());
        disposalInfo.setWasteType(record.getWasteType());
        disposalInfo.setWeight(record.getWeight());
        disposalInfo.setContainerId(record.getContainerId());
        chain.setDisposalInfo(disposalInfo);
        
        // 收运信息
        if (record.getCollectionTime() != null) {
            TraceabilityResult.CollectionInfo collectionInfo = new TraceabilityResult.CollectionInfo();
            collectionInfo.setCollectionTime(record.getCollectionTime());
            collectionInfo.setVehicleId(record.getVehicleId());
            collectionInfo.setDriverId(record.getDriverId());
            collectionInfo.setCollectionRoute(record.getCollectionRoute());
            chain.setCollectionInfo(collectionInfo);
        }
        
        // 处置信息
        if (record.getFinalDisposalTime() != null) {
            TraceabilityResult.ProcessingInfo processingInfo = new TraceabilityResult.ProcessingInfo();
            processingInfo.setDisposalFacilityId(record.getDisposalFacilityId());
            processingInfo.setDisposalMethod(record.getDisposalMethod());
            processingInfo.setFinalDisposalTime(record.getFinalDisposalTime());
            chain.setProcessingInfo(processingInfo);
        }
        
        // 构建步骤列表
        List<TraceabilityResult.TraceabilityStep> steps = new ArrayList<>();
        
        // 投放步骤
        steps.add(new TraceabilityResult.TraceabilityStep(
                "垃圾投放",
                "用户投放" + record.getWasteType() + "，重量：" + record.getWeight() + "kg",
                record.getDisposalTime(),
                record.getDisposalLocation(),
                record.getUserName()
        ));
        
        // 收运步骤
        if (record.getCollectionTime() != null) {
            steps.add(new TraceabilityResult.TraceabilityStep(
                    "垃圾收运",
                    "车辆" + record.getVehicleId() + "完成收运，司机：" + record.getDriverId(),
                    record.getCollectionTime(),
                    record.getCollectionRoute(),
                    record.getDriverId()
            ));
        }
        
        // 处置步骤
        if (record.getFinalDisposalTime() != null) {
            steps.add(new TraceabilityResult.TraceabilityStep(
                    "垃圾处置",
                    "在" + record.getDisposalFacilityId() + "完成" + record.getDisposalMethod(),
                    record.getFinalDisposalTime(),
                    record.getDisposalFacilityId(),
                    "处置场工作人员"
            ));
        }
        
        chain.setSteps(steps);
        result.setTraceabilityChain(chain);
        
        return result;
    }
}