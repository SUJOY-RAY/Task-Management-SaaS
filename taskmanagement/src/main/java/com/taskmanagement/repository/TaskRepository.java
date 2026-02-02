package com.taskmanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagement.entity.Task;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByTenantId(String tenantId);

    long countByTenantId(String tenantId);
}
