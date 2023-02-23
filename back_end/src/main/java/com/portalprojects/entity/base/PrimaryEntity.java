package com.portalprojects.entity.base;

import com.portalprojects.infrastructure.constant.EntityProperties;
import com.portalprojects.infrastructure.listener.CreatePrimaryEntityListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author thangncph26123
 */

@Getter
@Setter
@MappedSuperclass
@EntityListeners(CreatePrimaryEntityListener.class)
public abstract class PrimaryEntity extends AuditEntity
    implements IsIdentified {

    @Id
    @Column(length = EntityProperties.LENGTH_ID, updatable = false)
    private String id;

}
