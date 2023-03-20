package com.portalprojects.entity;

import com.portalprojects.entity.base.PrimaryEntity;
import com.portalprojects.infrastructure.constant.EntityProperties;
import com.portalprojects.infrastructure.constant.StatusProject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Nationalized;

/**
 * @author thangncph26123
 */

@Entity
@Data
@ToString
@Table(name = "project")
public class Project extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_CODE, nullable = false)
    @Index(name = "idx_code")
    private String code;

    @Column(length = EntityProperties.LENGTH_NAME)
    @Nationalized
    @Index(name = "idx_name")
    private String name;

    @Column(length = EntityProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private String descriptions;

    @Column(nullable = false)
    private Long startTime;

    @Column(nullable = false)
    private Long endTime;

    private Short progress;

    @Column(nullable = false)
    @Index(name = "idx_status_project")
    private StatusProject statusProject;

}
