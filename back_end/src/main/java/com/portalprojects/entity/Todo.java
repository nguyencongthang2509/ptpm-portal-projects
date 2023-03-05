package com.portalprojects.entity;

import com.portalprojects.entity.base.PrimaryEntity;
import com.portalprojects.infrastructure.constant.EntityProperties;
import com.portalprojects.infrastructure.constant.PriorityLevel;
import com.portalprojects.infrastructure.constant.StatusTodo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Nationalized;

/**
 * @author thangncph26123
 */

@Entity
@Data
@ToString
@Table(name = "to_do")
public class Todo extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_CODE, nullable = false)
    private String code;

    @Column(length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String name;

    @Column(length = EntityProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private String descriptions;

    private Long deadline;

    private Long completionTime;

    @Column(nullable = false)
    private PriorityLevel priorityLevel;

    private Short progress;

    @Column(length = EntityProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private String note;

    @Column(nullable = false)
    private StatusTodo statusTodo;
}
