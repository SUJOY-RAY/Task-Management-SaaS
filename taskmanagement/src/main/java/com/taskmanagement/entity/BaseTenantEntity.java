package com.taskmanagement.entity;

import com.taskmanagement.config.TenantContext;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseTenantEntity {

    @Column(nullable = false, updatable = false)
    private String tenantId;

    @PrePersist
    public void assignTenant() {
        this.tenantId = TenantContext.getTenant();
    }
}
