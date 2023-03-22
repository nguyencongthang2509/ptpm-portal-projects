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
import org.hibernate.annotations.Index;
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
    @Index(name = "idx_code")
    private String code;

    @Column(length = EntityProperties.LENGTH_NAME)
    @Nationalized
    @Index(name = "idx_name")
    private String name;

    @Column(length = EntityProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private String descriptions;

    @Index(name = "idx_deadline")
    private Long deadline;

    private Long completionTime;

    @Index(name = "idx_priority_level")
    private PriorityLevel priorityLevel;

    private Short progress;

    @Column(length = EntityProperties.LENGTH_DESCRIPTION)
    @Nationalized
    private String note;

    @Column(length = EntityProperties.LENGTH_ID)
    private String todoId;

    @Index(name = "idx_status_todo")
    private StatusTodo statusTodo;
}
