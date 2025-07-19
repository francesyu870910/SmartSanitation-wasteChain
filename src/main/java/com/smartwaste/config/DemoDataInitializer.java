package com.smartwaste.config;

import com.smartwaste.domain.model.*;
import com.smartwaste.domain.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 演示数据初始化器
 * Demo Data Initializer
 */
@Component
public class DemoDataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DemoDataInitializer.class);

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ContainerRepository containerRepository;
    
    @Autowired
    private VehicleRepository vehicleRepository;
    
    @Autowired
    private DeviceRepository deviceRepository;
    
    @Autowired
    private UserPointsRepository userPointsRepository;
    
    @Autowired
    private FullnessAlertRepository fullnessAlertRepository;
    
    @Autowired
    private DispatchTaskRepository dispatchTaskRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("开始初始化演示数据...");
        
        initUsers();
        initContainers();
        initVehicles();
        initDevices();
        initUserPoints();
        initAlerts();
        initDispatchTasks();
        
        logger.info("演示数据初始化完成！");
    }

    private void initUsers() {
        if (userRepository.count() > 0) return;
        
        User user1 = new User("user-001", "张三", "13800138001");
        user1.setAddress("智慧小区A栋101室");
        user1.setIdCard("110101199001011234");
        userRepository.save(user1);

        User user2 = new User("user-002", "李四", "13800138002");
        user2.setAddress("智慧小区B栋201室");
        user2.setIdCard("110101199002022345");
        userRepository.save(user2);

        User user3 = new User("user-003", "王五", "13800138003");
        user3.setAddress("智慧小区C栋301室");
        user3.setIdCard("110101199003033456");
        userRepository.save(user3);

        logger.info("初始化用户数据完成");
    }

    private void initContainers() {
        if (containerRepository.count() > 0) return;
        
        Container container1 = new Container("container-001", "智慧小区A栋东侧", WasteType.KITCHEN, 100.0);
        container1.setCurrentFullness(0.96);
        container1.setRfidTag("RFID-001");
        containerRepository.save(container1);

        Container container2 = new Container("container-002", "智慧小区A栋西侧", WasteType.RECYCLABLE, 120.0);
        container2.setCurrentFullness(0.65);
        container2.setRfidTag("RFID-002");
        containerRepository.save(container2);

        Container container3 = new Container("container-003", "智慧小区B栋南侧", WasteType.HAZARDOUS, 80.0);
        container3.setCurrentFullness(0.85);
        container3.setRfidTag("RFID-003");
        containerRepository.save(container3);

        Container container4 = new Container("container-004", "智慧小区B栋北侧", WasteType.OTHER, 100.0);
        container4.setCurrentFullness(0.72);
        container4.setRfidTag("RFID-004");
        containerRepository.save(container4);

        logger.info("初始化垃圾桶数据完成");
    }

    private void initVehicles() {
        if (vehicleRepository.count() > 0) return;
        
        Vehicle vehicle1 = new Vehicle("vehicle-001", "京A12345", WasteType.KITCHEN, 5.0);
        vehicle1.setDriverId("driver-001");
        vehicle1.setCurrentLocation("智慧小区A区");
        vehicle1.setStatus(VehicleStatus.IDLE);
        vehicleRepository.save(vehicle1);

        Vehicle vehicle2 = new Vehicle("vehicle-002", "京B67890", WasteType.RECYCLABLE, 8.0);
        vehicle2.setDriverId("driver-002");
        vehicle2.setCurrentLocation("收运路线1");
        vehicle2.setStatus(VehicleStatus.COLLECTING);
        vehicleRepository.save(vehicle2);

        Vehicle vehicle3 = new Vehicle("vehicle-003", "京C11111", WasteType.OTHER, 6.0);
        vehicle3.setDriverId("driver-003");
        vehicle3.setCurrentLocation("维修站");
        vehicle3.setStatus(VehicleStatus.MAINTENANCE);
        vehicleRepository.save(vehicle3);

        logger.info("初始化车辆数据完成");
    }

    private void initDevices() {
        if (deviceRepository.count() > 0) return;
        
        Device device1 = new Device("智能投放点-001", DeviceType.SMART_BIN, "智慧小区A栋");
        device1.setDeviceId("device-001");
        device1.setStatus(DeviceStatus.ONLINE);
        device1.setIpAddress("192.168.1.101");
        device1.setMacAddress("AA:BB:CC:DD:EE:01");
        device1.setLastHeartbeat(LocalDateTime.now().minusMinutes(2));
        deviceRepository.save(device1);

        Device device2 = new Device("人脸识别设备-001", DeviceType.FACE_RECOGNITION, "智慧小区B栋");
        device2.setDeviceId("device-002");
        device2.setStatus(DeviceStatus.ONLINE);
        device2.setIpAddress("192.168.1.102");
        device2.setMacAddress("AA:BB:CC:DD:EE:02");
        device2.setLastHeartbeat(LocalDateTime.now().minusMinutes(1));
        deviceRepository.save(device2);

        Device device3 = new Device("称重传感器-001", DeviceType.WEIGHT_SENSOR, "智慧小区C栋");
        device3.setDeviceId("device-003");
        device3.setStatus(DeviceStatus.OFFLINE);
        device3.setIpAddress("192.168.1.103");
        device3.setMacAddress("AA:BB:CC:DD:EE:03");
        device3.setLastHeartbeat(LocalDateTime.now().minusMinutes(30));
        deviceRepository.save(device3);

        logger.info("初始化设备数据完成");
    }

    private void initUserPoints() {
        if (userPointsRepository.count() > 0) return;
        
        UserPoints points1 = new UserPoints("user-001");
        points1.setTotalPoints(2100);
        points1.setAvailablePoints(1850);
        points1.setUsedPoints(250);
        points1.setLevel(3);
        points1.setLevelName("高级");
        userPointsRepository.save(points1);

        UserPoints points2 = new UserPoints("user-002");
        points2.setTotalPoints(1890);
        points2.setAvailablePoints(1650);
        points2.setUsedPoints(240);
        points2.setLevel(2);
        points2.setLevelName("中级");
        userPointsRepository.save(points2);

        UserPoints points3 = new UserPoints("user-003");
        points3.setTotalPoints(1200);
        points3.setAvailablePoints(1000);
        points3.setUsedPoints(200);
        points3.setLevel(2);
        points3.setLevelName("中级");
        userPointsRepository.save(points3);

        logger.info("初始化积分数据完成");
    }

    private void initAlerts() {
        if (fullnessAlertRepository.count() > 0) return;
        
        FullnessAlert alert1 = new FullnessAlert("container-001", 0.96, 0.95, FullnessAlert.AlertLevel.CRITICAL);
        alert1.setAlertId("alert-001");
        alert1.setLocation("智慧小区A栋东侧");
        alert1.setMessage("垃圾桶 container-001 满载率达到 96.0%，触发 紧急报警 级报警，请及时处理！");
        alert1.setAlertTime(LocalDateTime.now().minusMinutes(2));
        fullnessAlertRepository.save(alert1);

        FullnessAlert alert2 = new FullnessAlert("container-003", 0.85, 0.8, FullnessAlert.AlertLevel.MEDIUM);
        alert2.setAlertId("alert-002");
        alert2.setLocation("智慧小区B栋南侧");
        alert2.setMessage("垃圾桶 container-003 满载率达到 85.0%，触发 中级报警 级报警，请及时处理！");
        alert2.setAlertTime(LocalDateTime.now().minusHours(1));
        alert2.setIsResolved(true);
        alert2.setResolvedTime(LocalDateTime.now().minusMinutes(30));
        alert2.setResolvedBy("admin");
        fullnessAlertRepository.save(alert2);

        logger.info("初始化报警数据完成");
    }

    private void initDispatchTasks() {
        if (dispatchTaskRepository.count() > 0) return;
        
        // 创建待分配的厨余垃圾收运任务
        DispatchTask task1 = new DispatchTask("task-001", WasteType.KITCHEN, "container-001");
        task1.setPriority(5); // 高优先级，因为满载率96%
        task1.setEstimatedWeight(28.8); // 100L * 0.96 * 0.3密度系数
        task1.setStatus(DispatchStatus.PENDING);
        task1.setCreatedAt(LocalDateTime.now().minusMinutes(5));
        dispatchTaskRepository.save(task1);

        // 创建已分配的可回收垃圾收运任务
        DispatchTask task2 = new DispatchTask("task-002", WasteType.RECYCLABLE, "container-002");
        task2.setPriority(3);
        task2.setEstimatedWeight(23.4); // 120L * 0.65 * 0.3密度系数
        task2.setStatus(DispatchStatus.ASSIGNED);
        task2.setAssignedVehicleId("vehicle-002");
        task2.setCreatedAt(LocalDateTime.now().minusHours(2));
        task2.setScheduledTime(LocalDateTime.now().minusHours(1));
        dispatchTaskRepository.save(task2);

        // 创建执行中的有害垃圾收运任务
        DispatchTask task3 = new DispatchTask("task-003", WasteType.HAZARDOUS, "container-003");
        task3.setPriority(4);
        task3.setEstimatedWeight(20.4); // 80L * 0.85 * 0.3密度系数
        task3.setStatus(DispatchStatus.IN_PROGRESS);
        task3.setAssignedVehicleId("vehicle-001");
        task3.setCreatedAt(LocalDateTime.now().minusHours(3));
        task3.setScheduledTime(LocalDateTime.now().minusHours(2));
        dispatchTaskRepository.save(task3);

        // 创建已完成的其他垃圾收运任务
        DispatchTask task4 = new DispatchTask("task-004", WasteType.OTHER, "container-004");
        task4.setPriority(2);
        task4.setEstimatedWeight(21.6); // 100L * 0.72 * 0.3密度系数
        task4.setStatus(DispatchStatus.COMPLETED);
        task4.setAssignedVehicleId("vehicle-003");
        task4.setCreatedAt(LocalDateTime.now().minusHours(6));
        task4.setScheduledTime(LocalDateTime.now().minusHours(5));
        task4.setCompletedAt(LocalDateTime.now().minusHours(4));
        dispatchTaskRepository.save(task4);

        // 创建多容器的组合任务
        DispatchTask task5 = new DispatchTask("task-005", WasteType.KITCHEN, "container-001,container-005");
        task5.setPriority(3);
        task5.setEstimatedWeight(45.0);
        task5.setStatus(DispatchStatus.PENDING);
        task5.setCreatedAt(LocalDateTime.now().minusMinutes(10));
        task5.setNotes("多个厨余垃圾桶的组合收运任务");
        dispatchTaskRepository.save(task5);

        logger.info("初始化调度任务数据完成");
    }
}