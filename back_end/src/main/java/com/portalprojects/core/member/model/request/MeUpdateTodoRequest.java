package com.portalprojects.core.member.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author thangncph26123
 */
@Getter
@Setter
public class MeUpdateTodoRequest {
    private String idTodo;
    private Integer priorityLevel;
    private String indexTask;
    private String indexTodoInTask;
}
