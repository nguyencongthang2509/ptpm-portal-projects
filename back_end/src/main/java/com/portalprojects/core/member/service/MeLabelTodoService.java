package com.portalprojects.core.member.service;

import com.portalprojects.core.common.base.TaskObject;
import com.portalprojects.core.member.model.request.MeCreateOrDeleteLabelTodoRequest;
import com.portalprojects.entity.LabelTodo;

/**
 * @author thangncph26123
 */
public interface MeLabelTodoService {

    TaskObject create(MeCreateOrDeleteLabelTodoRequest request);

    TaskObject delete(MeCreateOrDeleteLabelTodoRequest request);
}
