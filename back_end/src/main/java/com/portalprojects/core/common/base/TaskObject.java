package com.portalprojects.core.common.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author thangncph26123
 */

@Getter
@Setter
@AllArgsConstructor
public class TaskObject {

    private Object data;
    private int idTask;
    private int indexTodoInTask;

}