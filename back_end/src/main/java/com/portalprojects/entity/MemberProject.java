package com.portalprojects.entity;

import com.portalprojects.entity.base.PrimaryEntity;
import com.portalprojects.infrastructure.constant.EntityProperties;
import com.portalprojects.infrastructure.constant.RoleMemberProject;
import com.portalprojects.infrastructure.constant.StatusWork;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author thangncph26123
 */

@Entity
@Getter
@Setter
@ToString
@Table(name = "member_project")
public class MemberProject extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_ID)
    private String memberId;

    @Column(length = EntityProperties.LENGTH_ID)
    private String projectId;

    @Column(nullable = false)
    private RoleMemberProject role;

    @Column(nullable = false)
    private StatusWork statusWork;
}
