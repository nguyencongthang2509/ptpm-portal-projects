package com.portalprojects.entity;

import com.portalprojects.entity.base.PrimaryEntity;
import com.portalprojects.infrastructure.constant.EntityProperties;
import com.portalprojects.infrastructure.constant.RoleStakeholderProject;
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
@Table(name = "stakeholder_project")
public class StakeholderProject extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_ID)
    private String stakeholderId;

    @Column(length = EntityProperties.LENGTH_ID)
    private String projectId;

    @Column(nullable = false)
    private RoleStakeholderProject role;
}
