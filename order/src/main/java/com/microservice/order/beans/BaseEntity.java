package com.microservice.order.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@Data
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "deleted = false")
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdOn;

    @Column(name = "last_update_on")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date lastUpdateOn;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    protected boolean deleted;

    @Column(name = "deleted_at")
    @LastModifiedDate
    protected LocalDateTime deletedAt;

    @Basic(optional = false)
    @Column(name = "status", nullable = false)
    protected short status;

    @PrePersist
    public void onCreate() {
        this.createdOn = new Date();
        this.lastUpdateOn = new Date();
    }

    @PreUpdate
    public void onUpdate() {
        this.lastUpdateOn = new Date();
    }

    public BaseEntity() {
        super();
        this.createdOn = new Date();
    }

    public BaseEntity(Long id) {
        this.id = id;
    }

    public BaseEntity(Long id, Date createdOn) {
        this.id = id;
        this.createdOn = createdOn;
    }

}

