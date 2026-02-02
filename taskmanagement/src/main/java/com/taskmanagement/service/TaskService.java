package com.taskmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taskmanagement.config.TenantContext;
import com.taskmanagement.entity.Task;
import com.taskmanagement.entity.TenantConfig;
import com.taskmanagement.repository.TaskRepository;
import com.taskmanagement.repository.TenantConfigRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TenantConfigRepository tenantConfigRepository;

    public Task create(Task task) {
        String tenantId = TenantContext.getTenant();

        TenantConfig config = tenantConfigRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant config not found"));

        long taskCount = taskRepository.countByTenantId(tenantId);

        if (taskCount >= config.getMaxTasks()) {
            throw new RuntimeException("Task quota exceeded for tenant " + tenantId);
        }

        return taskRepository.save(task);
    }

    public List<Task> list() {
        return taskRepository.findByTenantId(TenantContext.getTenant());
    }
}
