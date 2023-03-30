package com.portalprojects.core.member.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author thangncph26123
 */
@Getter
@Setter
public class MeUpdateTodoListRequest {

    private String idTodoList;
    private String indexBefore;
    private String indexAfter;
}
