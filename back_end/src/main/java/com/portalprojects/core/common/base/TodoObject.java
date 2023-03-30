package com.portalprojects.core.common.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author thangncph26123
 */

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TodoObject {

    private Object data;
    private int indexTask;
    private int indexTodoInTask;
    private Short numberTodoComplete;
    private Short numberTodo;

}