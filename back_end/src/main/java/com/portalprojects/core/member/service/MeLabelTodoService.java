package com.portalprojects.core.member.service;

import com.portalprojects.entity.LabelTodo;

/**
 * @author thangncph26123
 */
public interface MeLabelTodoService {

    LabelTodo create(String idLabel, String idTodo);

    Boolean delete(String idLabel, String idTodo);
}
