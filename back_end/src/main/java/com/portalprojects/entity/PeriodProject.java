package com.portalprojects.entity;

import com.portalprojects.infrastructure.constant.EntityProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.Column;

/**
 * @author thangncph26123
 */
@Entity
@Data
@ToString
@Table(name = "period_project")
public class PeriodProject {

    @Id
    @Column(length = EntityProperties.LENGTH_ID, updatable = false)
    private String id;

    @Column(length = EntityProperties.LENGTH_ID)
    private String periodId;

    @Column(length = EntityProperties.LENGTH_ID)
    private String todoId;
}
