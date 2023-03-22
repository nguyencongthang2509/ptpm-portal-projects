package com.portalprojects.core.member.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author thangncph26123
 */
@Getter
@Setter
public class MeCreateOrDeleteAssignRequest {

    private String idMember;
    private String idTodo;
    private String idTask;
    private String indexTodoInTask;
}
