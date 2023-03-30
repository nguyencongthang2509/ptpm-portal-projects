package com.portalprojects.core.member.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author thangncph26123
 */
@Getter
@Setter
public class MeUpdateStatusTodoRequest {

    private String idTodo;

    private Integer statusTodo;

    private String todoId;

    private String indexTask;

    private String indexTodoInTask;
}
