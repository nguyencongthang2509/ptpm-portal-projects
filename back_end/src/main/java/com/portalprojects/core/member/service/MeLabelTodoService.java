package com.portalprojects.core.member.service;

import com.portalprojects.core.common.base.TodoObject;
import com.portalprojects.core.member.model.request.MeCreateOrDeleteLabelTodoRequest;

/**
 * @author thangncph26123
 */
public interface MeLabelTodoService {

    TodoObject create(MeCreateOrDeleteLabelTodoRequest request);

    TodoObject delete(MeCreateOrDeleteLabelTodoRequest request);
}
