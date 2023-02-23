package com.portalprojects.entity.base;

import com.portalprojects.infrastructure.listener.AuditEntityListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * @author thangncph26123
 */

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditEntityListener.class)
public abstract class AuditEntity {

    @Column(updatable = false)
    private Long createdDate;

    @Column
    private Long lastModifiedDate;

}
