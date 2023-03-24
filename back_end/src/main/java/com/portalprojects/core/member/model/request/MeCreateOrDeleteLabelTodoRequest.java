package com.portalprojects.core.member.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author thangncph26123
 */
@Getter
@Setter
public class MeCreateOrDeleteLabelTodoRequest {

    private String idLabel;
    private String idTodo;
    private String indexTask;
    private String indexTodoInTask;
}
