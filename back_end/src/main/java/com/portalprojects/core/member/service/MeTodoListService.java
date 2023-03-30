package com.portalprojects.core.member.service;

import com.portalprojects.core.common.base.TodoListObject;
import com.portalprojects.core.member.model.request.MeUpdateTodoListRequest;
import com.portalprojects.core.member.model.response.MeTodoListResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author thangncph26123
 */

public interface MeTodoListService {

    List<MeTodoListResponse> getAllTodoList(String idProject);

    TodoListObject updateIndexTodoList(MeUpdateTodoListRequest request);
}
