package com.smartwaste.domain.service.impl;

import com.smartwaste.domain.model.Container;
import com.smartwaste.domain.model.WasteType;
import com.smartwaste.domain.repository.ContainerRepository;
import com.smartwaste.domain.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 垃圾桶服务实现类
 * Container Service Implementation
 */
@Service
@Transactional
public class ContainerServiceImpl implements ContainerService {

    private final ContainerRepository containerRepository;

    @Autowired
    public ContainerServiceImpl(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    @Override
    public Container createContainer(Container container) {
        if (container.getContainerId() == null || container.getContainerId().isEmpty()) {
            container.setContainerId(UUID.randomUUID().toString());
        }
        
        // 验证RFID标签唯一性
        if (container.getRfidTag() != null && 
            containerRepository.findByRfidTag(container.getRfidTag()).isPresent()) {
            throw new IllegalArgumentException("RFID标签已存在");
        }
        
        return containerRepository.save(container);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Container> findById(String containerId) {
        return containerRepository.findById(containerId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Container> findByRfidTag(String rfidTag) {
        return containerRepository.findByRfidTag(rfidTag);
    }

    @Override
    public Container updateFullness(String containerId, Double fullness) {
        Optional<Container> containerOpt = containerRepository.findById(containerId);
        if (containerOpt.isEmpty()) {
            throw new IllegalArgumentException("垃圾桶不存在");
        }
        
        Container container = containerOpt.get();
        container.setCurrentFullness(fullness);
        return containerRepository.save(container);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Container> getContainersNeedingCollection(Double threshold) {
        return containerRepository.findContainersNeedingCollection(threshold);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Container> findByWasteType(WasteType wasteType) {
        return containerRepository.findByWasteType(wasteType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Container> findByLocation(String location) {
        return containerRepository.findByLocationContaining(location);
    }

    @Override
    public void deactivateContainer(String containerId) {
        Optional<Container> containerOpt = containerRepository.findById(containerId);
        if (containerOpt.isEmpty()) {
            throw new IllegalArgumentException("垃圾桶不存在");
        }
        
        Container container = containerOpt.get();
        container.setIsActive(false);
        containerRepository.save(container);
    }
}