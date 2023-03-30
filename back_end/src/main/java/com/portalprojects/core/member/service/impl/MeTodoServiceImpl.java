package com.portalprojects.core.member.service.impl;

import com.portalprojects.core.common.base.TodoObject;
import com.portalprojects.core.member.model.request.MeCreateDetailTodoRequest;
import com.portalprojects.core.member.model.request.MeDeleteDetailTodoRequest;
import com.portalprojects.core.member.model.request.MeUpdateDeTailTodoRequest;
import com.portalprojects.core.member.model.request.MeUpdateDescriptionsTodoRequest;
import com.portalprojects.core.member.model.request.MeUpdateStatusTodoRequest;
import com.portalprojects.core.member.model.request.MeUpdateTodoRequest;
import com.portalprojects.core.member.model.response.MeCountTodoResponse;
import com.portalprojects.core.member.model.response.MeDetailTodoResponse;
import com.portalprojects.core.member.model.response.MeTodoResponse;
import com.portalprojects.core.member.repository.MeTodoRepository;
import com.portalprojects.core.member.service.MeTodoService;
import com.portalprojects.entity.Todo;
import com.portalprojects.infrastructure.constant.Message;
import com.portalprojects.infrastructure.constant.PriorityLevel;
import com.portalprojects.infrastructure.constant.StatusTodo;
import com.portalprojects.infrastructure.exception.rest.ErrorHandler;
import com.portalprojects.infrastructure.exception.rest.RestApiException;
import com.portalprojects.util.DateTimeUtil;
import jakarta.validation.Valid;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
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

    @Autowired
    private ErrorHandler errorHandler;

    @Override
    public List<MeTodoResponse> getToDoByPeriodAndPriorityLevel(String idPeriod, String idTodoList) {
        return meTodoRepository.getToDoByPeriodAndPriorityLevel(idPeriod, idTodoList);
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
    @Synchronized
    public TodoObject updatePriorityLevel(@Valid MeUpdateTodoRequest request) {
        Optional<Todo> todoFindById = meTodoRepository.findById(request.getIdTodo());
        if (!todoFindById.isPresent()) {
            throw new RestApiException(Message.TO_DO_NOT_EXISTS);
        }
        PriorityLevel[] priorityLevels = PriorityLevel.values();
        todoFindById.get().setPriorityLevel(priorityLevels[request.getPriorityLevel()]);
        TodoObject todoObject = TodoObject.builder().data(meTodoRepository.save(todoFindById.get())).indexTask(Integer.parseInt(request.getIndexTask())).indexTodoInTask(Integer.parseInt(request.getIndexTodoInTask())).build();
        return todoObject;
    }

    @Override
    @Synchronized
    public TodoObject createTodoChecklist(@Valid MeCreateDetailTodoRequest request) {
        Todo todo = new Todo();
        todo.setCode("todo_" + DateTimeUtil.convertDateToTimeStampSecond());
        todo.setName(request.getName());
        todo.setTodoId(request.getIdTodo());
        todo.setStatusTodo(StatusTodo.CHUA_HOAN_THANH);
        Todo todoSave = meTodoRepository.save(todo);
        MeCountTodoResponse meCountTodoResponse = updateProgress(request.getIdTodo());
        return new TodoObject(todoSave, Integer.parseInt(request.getIndexTask()), Integer.parseInt(request.getIndexTodoInTask()), meCountTodoResponse.getNumberTodoComplete(), meCountTodoResponse.getNumberTodo());

    }

    @Override
    @Synchronized
    public Todo updateTodoChecklist(@Valid MeUpdateDeTailTodoRequest request) {
        Optional<Todo> todo = meTodoRepository.findById(request.getIdTodo());
        todo.get().setName(request.getName());
        Todo todoSave = meTodoRepository.save(todo.get());
        return todoSave;
    }

    @Override
    @Synchronized
    public TodoObject updateStatusTodo(@Valid MeUpdateStatusTodoRequest request) {
        Optional<Todo> todoFind = meTodoRepository.findById(request.getIdTodo());
        if (request.getStatusTodo() == 0) {
            todoFind.get().setStatusTodo(StatusTodo.DA_HOAN_THANH);
        } else {
            todoFind.get().setStatusTodo(StatusTodo.CHUA_HOAN_THANH);
        }
        Todo todoInCheckList = meTodoRepository.save(todoFind.get());
        MeCountTodoResponse meCountTodoResponse = updateProgress(request.getTodoId());
        return new TodoObject(todoInCheckList, Integer.parseInt(request.getIndexTask()), Integer.parseInt(request.getIndexTodoInTask()), meCountTodoResponse.getNumberTodoComplete(), meCountTodoResponse.getNumberTodo());
    }

    @Override
    @Synchronized
    public TodoObject deleteDetailTodo(@Valid MeDeleteDetailTodoRequest request) {
        meTodoRepository.deleteById(request.getId());
        MeCountTodoResponse meCountTodoResponse = updateProgress(request.getTodoId());
        return new TodoObject(request.getId(), Integer.parseInt(request.getIndexTask()), Integer.parseInt(request.getIndexTodoInTask()), meCountTodoResponse.getNumberTodoComplete(), meCountTodoResponse.getNumberTodo());
    }

    @Override
    @Synchronized
    public TodoObject updateDescriptionsTodo(@Valid MeUpdateDescriptionsTodoRequest request, StompHeaderAccessor headerAccessor) {
        if (request.getDescriptions().length() > 1000) {
            errorHandler.handleRestApiException(new RestApiException(Message.DESCRIPTIONS_TOO_LONG), headerAccessor);
            throw new RestApiException(Message.DESCRIPTIONS_TOO_LONG);
        }
        Optional<Todo> todoFind = meTodoRepository.findById(request.getIdTodo());
        todoFind.get().setDescriptions(request.getDescriptions());
        TodoObject todoObject = TodoObject.builder().data(meTodoRepository.save(todoFind.get())).indexTask(Integer.parseInt(request.getIndexTask())).indexTodoInTask(Integer.parseInt(request.getIndexTodoInTask())).build();
        return todoObject;
    }

    public MeCountTodoResponse updateProgress(String todoId) {
        Short countTodoComplete = meTodoRepository.countTodoComplete(todoId);
        Short countTodoInCheckList = meTodoRepository.countTodoInCheckList(todoId);
        Optional<Todo> todo = meTodoRepository.findById(todoId);
        if(countTodoInCheckList > 0) {
            short progress = (short) (countTodoComplete * 100 / countTodoInCheckList);
            todo.get().setProgress(progress);
        }
        if(countTodoInCheckList == 0){
            todo.get().setProgress((short) 0);
        }
        meTodoRepository.save(todo.get());
        return new MeCountTodoResponse(countTodoComplete, countTodoInCheckList);
    }
}
