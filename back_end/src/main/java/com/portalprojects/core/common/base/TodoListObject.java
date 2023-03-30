package com.portalprojects.core.common.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author thangncph26123
 */
@Getter
@Setter
@AllArgsConstructor
public class TodoListObject {

    private Object data;
    private int indexBefore;
    private int indexAfter;
}
