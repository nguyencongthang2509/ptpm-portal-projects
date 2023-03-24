package com.portalprojects.core.member.service.impl;

import com.portalprojects.core.common.base.TaskObject;
import com.portalprojects.core.member.model.request.MeUpdateTodoRequest;
import com.portalprojects.core.member.model.response.MeDetailTodoResponse;
import com.portalprojects.core.member.model.response.MeTodoResponse;
import com.portalprojects.core.member.repository.MeTodoRepository;
import com.portalprojects.core.member.service.MeTodoService;
import com.portalprojects.entity.Todo;
import com.portalprojects.infrastructure.constant.Message;
import com.portalprojects.infrastructure.constant.PriorityLevel;
import com.portalprojects.infrastructure.exception.rest.RestApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author thangncph26123
 */
@Service
public class MeTodoServiceImpl implements MeTodoService {

    @Autowired
    private MeTodoRepository meTodoRepository;

    @Override
    public List<MeTodoResponse> getToDoByPeriodAndPriorityLevel(String idPeriod, Integer statusTodo) {
        return meTodoRepository.getToDoByPeriodAndPriorityLevel(idPeriod, statusTodo);
    }

    @Override
    public Todo findById(String id) {
        return meTodoRepository.findById(id).get();
    }

    @Override
    public List<MeDetailTodoResponse> getDetailTodo(String idTodo) {
        return meTodoRepository.getDetailTodo(idTodo);
    }

    @Override
    public TaskObject updatePriorityLevel(MeUpdateTodoRequest request) {
        Optional<Todo> todoFindById = meTodoRepository.findById(request.getIdTodo());
        if (!todoFindById.isPresent()) {
            throw new RestApiException(Message.TO_DO_NOT_EXISTS);
        }
        PriorityLevel[] priorityLevels = PriorityLevel.values();
        todoFindById.get().setPriorityLevel(priorityLevels[request.getPriorityLevel()]);
        return new TaskObject(meTodoRepository.save(todoFindById.get()), Integer.parseInt(request.getIndexTask()), Integer.parseInt(request.getIndexTodoInTask()));
    }
}
