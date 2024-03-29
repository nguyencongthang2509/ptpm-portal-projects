package com.portalprojects.core.member.model.response;

import com.portalprojects.entity.Todo;
import com.portalprojects.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * @author thangncph26123
 */
@Projection(types = {Todo.class})
public interface MeTodoResponse extends IsIdentified {

    @Value("#{target.code}")
    String getCode();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.priority_level}")
    String getPriorityLevel();

    @Value("#{target.descriptions}")
    String getDescriptions();

    @Value("#{target.deadline}")
    Long getDeadline();

    @Value("#{target.number_todo_complete}")
    Short getNumberTodoComplete();

    @Value("#{target.number_todo}")
    Short getNumberTodo();
}
