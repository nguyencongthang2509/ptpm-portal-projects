package com.portalprojects.entity;

import com.portalprojects.entity.base.PrimaryEntity;
import com.portalprojects.infrastructure.constant.EntityProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

/**
 * @author thangncph26123
 */

@Entity
@Data
@ToString
@Table(name = "assign")
public class Assign extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_ID)
    private String memberId;

    @Column(length = EntityProperties.LENGTH_ID)
    private String todoId;
}
