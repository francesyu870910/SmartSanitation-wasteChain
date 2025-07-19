package com.smartwaste.api.controller;

import com.smartwaste.domain.model.TraceabilityRecord;
import com.smartwaste.domain.model.TraceabilityResult;
import com.smartwaste.domain.service.TraceabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 溯源管理控制器
 */
@RestController
@RequestMapping("/api/traceability")
@CrossOrigin(origins = "*")
public class TraceabilityController {
    
    @Autowired
    private TraceabilityService traceabilityService;
    
    /**
     * 创建溯源记录（垃圾投放时调用）
     */
    @PostMapping("/records")
    public ResponseEntity<TraceabilityRecord> createTraceabilityRecord(@RequestBody Map<String, Object> request) {
        try {
            String userId = (String) request.get("userId");
            String wasteId = (String) request.get("wasteId");
            String wasteType = (String) request.get("wasteType");
            Double weight = Double.valueOf(request.get("weight").toString());
            String containerId = (String) request.get("containerId");
            String disposalLocation = (String) request.get("disposalLocation");
            String qrCodeData = (String) request.get("qrCodeData");
            String rfidTag = (String) request.get("rfidTag");
            
            // 如果没有提供wasteId，自动生成一个
            if (wasteId == null || wasteId.trim().isEmpty()) {
                wasteId = traceabilityService.generateWasteId();
            }
            
            TraceabilityRecord record = traceabilityService.createTraceabilityRecord(
                    userId, wasteId, wasteType, weight, containerId, disposalLocation, qrCodeData, rfidTag);
            
            return ResponseEntity.ok(record);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 更新收运信息
     */
    @PutMapping("/records/{wasteId}/collection")
    public ResponseEntity<TraceabilityRecord> updateCollectionInfo(
            @PathVariable String wasteId,
            @RequestBody Map<String, String> request) {
        try {
            String vehicleId = request.get("vehicleId");
            String driverId = request.get("driverId");
            String collectionRoute = request.get("collectionRoute");
            
            TraceabilityRecord record = traceabilityService.updateCollectionInfo(wasteId, vehicleId, driverId, collectionRoute);
            return ResponseEntity.ok(record);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 更新处置信息
     */
    @PutMapping("/records/{wasteId}/disposal")
    public ResponseEntity<TraceabilityRecord> updateDisposalInfo(
            @PathVariable String wasteId,
            @RequestBody Map<String, String> request) {
        try {
            String disposalFacilityId = request.get("disposalFacilityId");
            String disposalMethod = request.get("disposalMethod");
            
            TraceabilityRecord record = traceabilityService.updateDisposalInfo(wasteId, disposalFacilityId, disposalMethod);
            return ResponseEntity.ok(record);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 通过垃圾ID进行溯源查询
     */
    @GetMapping("/trace/{wasteId}")
    public ResponseEntity<TraceabilityResult> traceWasteToSource(@PathVariable String wasteId) {
        TraceabilityResult result = traceabilityService.traceWasteToSource(wasteId);
        return ResponseEntity.ok(result);
    }
    
    /**
     * 通过二维码进行溯源查询
     */
    @GetMapping("/trace/qr/{qrCodeData}")
    public ResponseEntity<TraceabilityResult> traceByQRCode(@PathVariable String qrCodeData) {
        TraceabilityResult result = traceabilityService.traceByQRCode(qrCodeData);
        return ResponseEntity.ok(result);
    }
    
    /**
     * 通过RFID标签进行溯源查询
     */
    @GetMapping("/trace/rfid/{rfidTag}")
    public ResponseEntity<TraceabilityResult> traceByRFID(@PathVariable String rfidTag) {
        TraceabilityResult result = traceabilityService.traceByRFID(rfidTag);
        return ResponseEntity.ok(result);
    }
    
    /**
     * 查询用户的所有投放记录
     */
    @GetMapping("/users/{userId}/records")
    public ResponseEntity<List<TraceabilityRecord>> getUserTraceabilityRecords(@PathVariable String userId) {
        List<TraceabilityRecord> records = traceabilityService.getUserTraceabilityRecords(userId);
        return ResponseEntity.ok(records);
    }
    
    /**
     * 查询用户在指定时间范围内的投放记录
     */
    @GetMapping("/users/{userId}/records/range")
    public ResponseEntity<List<TraceabilityRecord>> getUserTraceabilityRecords(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        List<TraceabilityRecord> records = traceabilityService.getUserTraceabilityRecords(userId, startTime, endTime);
        return ResponseEntity.ok(records);
    }
    
    /**
     * 根据地址查找相关的投放记录（用于执法支持）
     */
    @GetMapping("/records/address")
    public ResponseEntity<List<TraceabilityRecord>> findRecordsByAddress(@RequestParam String address) {
        List<TraceabilityRecord> records = traceabilityService.findRecordsByAddress(address);
        return ResponseEntity.ok(records);
    }
    
    /**
     * 查询指定垃圾类型的投放记录
     */
    @GetMapping("/records/waste-type/{wasteType}")
    public ResponseEntity<List<TraceabilityRecord>> getRecordsByWasteType(@PathVariable String wasteType) {
        List<TraceabilityRecord> records = traceabilityService.getRecordsByWasteType(wasteType);
        return ResponseEntity.ok(records);
    }
    
    /**
     * 查询指定车辆的收运记录
     */
    @GetMapping("/records/vehicle/{vehicleId}")
    public ResponseEntity<List<TraceabilityRecord>> getRecordsByVehicle(@PathVariable String vehicleId) {
        List<TraceabilityRecord> records = traceabilityService.getRecordsByVehicle(vehicleId);
        return ResponseEntity.ok(records);
    }
    
    /**
     * 查询指定处置场所的处理记录
     */
    @GetMapping("/records/facility/{facilityId}")
    public ResponseEntity<List<TraceabilityRecord>> getRecordsByFacility(@PathVariable String facilityId) {
        List<TraceabilityRecord> records = traceabilityService.getRecordsByFacility(facilityId);
        return ResponseEntity.ok(records);
    }
    
    /**
     * 获取未完成处置的记录（用于监控和提醒）
     */
    @GetMapping("/records/incomplete")
    public ResponseEntity<List<TraceabilityRecord>> getIncompleteRecords(@RequestParam(defaultValue = "24") int cutoffHours) {
        List<TraceabilityRecord> records = traceabilityService.getIncompleteRecords(cutoffHours);
        return ResponseEntity.ok(records);
    }
    
    /**
     * 统计用户的投放次数
     */
    @GetMapping("/users/{userId}/count")
    public ResponseEntity<Long> getUserDisposalCount(@PathVariable String userId) {
        Long count = traceabilityService.getUserDisposalCount(userId);
        return ResponseEntity.ok(count);
    }
    
    /**
     * 统计指定垃圾类型的投放次数
     */
    @GetMapping("/waste-types/{wasteType}/count")
    public ResponseEntity<Long> getWasteTypeCount(@PathVariable String wasteType) {
        Long count = traceabilityService.getWasteTypeCount(wasteType);
        return ResponseEntity.ok(count);
    }
    
    /**
     * 生成垃圾唯一标识
     */
    @PostMapping("/generate-waste-id")
    public ResponseEntity<Map<String, String>> generateWasteId() {
        String wasteId = traceabilityService.generateWasteId();
        return ResponseEntity.ok(Map.of("wasteId", wasteId));
    }
    
    /**
     * 批量查询溯源信息（用于执法支持）
     */
    @PostMapping("/trace/batch")
    public ResponseEntity<List<TraceabilityResult>> batchTrace(@RequestBody Map<String, List<String>> request) {
        List<String> wasteIds = request.get("wasteIds");
        List<TraceabilityResult> results = wasteIds.stream()
                .map(wasteId -> traceabilityService.traceWasteToSource(wasteId))
                .collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok(results);
    }
    
    /**
     * 获取溯源统计信息
     */
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getTraceabilityStatistics() {
        // 这里可以添加更多统计信息
        Map<String, Object> statistics = Map.of(
                "totalRecords", traceabilityService.getWasteTypeCount("厨余垃圾") + 
                               traceabilityService.getWasteTypeCount("可回收垃圾") + 
                               traceabilityService.getWasteTypeCount("有害垃圾") + 
                               traceabilityService.getWasteTypeCount("其他垃圾"),
                "kitchenWasteCount", traceabilityService.getWasteTypeCount("厨余垃圾"),
                "recyclableWasteCount", traceabilityService.getWasteTypeCount("可回收垃圾"),
                "hazardousWasteCount", traceabilityService.getWasteTypeCount("有害垃圾"),
                "otherWasteCount", traceabilityService.getWasteTypeCount("其他垃圾")
        );
        return ResponseEntity.ok(statistics);
    }
}