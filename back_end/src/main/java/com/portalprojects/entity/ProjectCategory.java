package com.portalprojects.entity;

import com.portalprojects.entity.base.PrimaryEntity;
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
@Table(name = "project_category")
public class ProjectCategory extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_ID)
    private String projectId;

    @Column(length = EntityProperties.LENGTH_ID)
    private String categoryId;
}
