package com.portalprojects.core.member.service;

import com.portalprojects.core.common.base.TodoObject;
import com.portalprojects.core.member.model.request.MeCreateDetailTodoRequest;
import com.portalprojects.core.member.model.request.MeDeleteDeadlineTodoRequest;
import com.portalprojects.core.member.model.request.MeDeleteDetailTodoRequest;
import com.portalprojects.core.member.model.request.MeUpdateDeTailTodoRequest;
import com.portalprojects.core.member.model.request.MeUpdateDeadlineTodoRequest;
import com.portalprojects.core.member.model.request.MeUpdateDescriptionsTodoRequest;
import com.portalprojects.core.member.model.request.MeUpdateStatusTodoRequest;
import com.portalprojects.core.member.model.request.MeUpdateTodoRequest;
import com.portalprojects.core.member.model.response.MeDetailTodoResponse;
import com.portalprojects.core.member.model.response.MeTodoResponse;
import com.portalprojects.entity.Todo;
import jakarta.validation.Valid;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

import java.util.List;

/**
 * @author thangncph26123
 */
public interface MeTodoService {

    List<MeTodoResponse> getToDoByPeriodAndPriorityLevel(String idPeriod, String idTodoList);

    Todo findById(String id);

    List<MeDetailTodoResponse> getDetailTodo(String idTodo);

    TodoObject updatePriorityLevel(@Valid MeUpdateTodoRequest request, StompHeaderAccessor headerAccessor);

    TodoObject createTodoChecklist(@Valid MeCreateDetailTodoRequest request, StompHeaderAccessor headerAccessor);

    Todo updateTodoChecklist(@Valid MeUpdateDeTailTodoRequest request, StompHeaderAccessor headerAccessor);

    TodoObject updateStatusTodo(@Valid MeUpdateStatusTodoRequest request, StompHeaderAccessor headerAccessor);

    TodoObject deleteDetailTodo(@Valid MeDeleteDetailTodoRequest request, StompHeaderAccessor headerAccessor);

    TodoObject updateDescriptionsTodo(@Valid final MeUpdateDescriptionsTodoRequest request, StompHeaderAccessor headerAccessor);

    TodoObject updateDeadlineTodo(@Valid MeUpdateDeadlineTodoRequest request, StompHeaderAccessor headerAccessor);

    TodoObject deleteDeadlineTodo(@Valid MeDeleteDeadlineTodoRequest request, StompHeaderAccessor headerAccessor);
}
