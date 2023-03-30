package com.portalprojects.entity;

import com.portalprojects.entity.base.PrimaryEntity;
import com.portalprojects.infrastructure.constant.EntityProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import org.hibernate.annotations.Index;

/**
 * @author thangncph26123
 */

@Entity
@Getter
@Setter
@ToString
@Table(name = "label_todo")
public class LabelTodo extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_ID)
    @Index(name = "idx_label_id")
    private String labelId;

    @Column(length = EntityProperties.LENGTH_ID)
    @Index(name = "idx_todo_id")
    private String todoId;
}
