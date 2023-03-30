package com.portalprojects.entity;

import com.portalprojects.entity.base.PrimaryEntity;
import com.portalprojects.infrastructure.constant.EntityProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Index;

/**
 * @author thangncph26123
 */

@Entity
@Getter
@Setter
@ToString
@Table(name = "assign")
public class Assign extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_ID)
    @Index(name = "idx_member_id")
    private String memberId;

    @Column(length = EntityProperties.LENGTH_ID)
    @Index(name = "idx_todo_id")
    private String todoId;
}
