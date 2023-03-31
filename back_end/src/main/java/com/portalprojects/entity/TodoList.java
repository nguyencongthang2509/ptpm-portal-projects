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
import org.hibernate.annotations.Nationalized;

/**
 * @author thangncph26123
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "todo_list")
public class TodoList extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_CODE, nullable = false)
    @Index(name = "idx_code")
    private String code;

    @Column(length = EntityProperties.LENGTH_NAME)
    @Nationalized
    @Index(name = "idx_name")
    private String name;

    @Column(nullable = false)
    private Byte indexTodoList;

    @Column(length = EntityProperties.LENGTH_ID)
    private String projectId;
}
