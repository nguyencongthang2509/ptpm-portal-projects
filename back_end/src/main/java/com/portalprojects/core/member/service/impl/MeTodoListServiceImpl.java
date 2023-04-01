package com.portalprojects.core.member.service.impl;

import com.portalprojects.core.common.base.TodoListObject;
import com.portalprojects.core.member.model.request.MeUpdateTodoListRequest;
import com.portalprojects.core.member.model.response.MeTodoListResponse;
import com.portalprojects.core.member.repository.MeTodoListRepository;
import com.portalprojects.core.member.service.MeTodoListService;
import com.portalprojects.infrastructure.constant.Message;
import com.portalprojects.infrastructure.exception.rest.RestApiException;
import com.portalprojects.util.TodoListHelper;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author thangncph26123
 */
@Service
public class MeTodoListServiceImpl implements MeTodoListService {

    @Autowired
    private MeTodoListRepository meTodoListRepository;

    @Autowired
    private TodoListHelper todoListHelper;

    @Override
    public List<MeTodoListResponse> getAllTodoList(String idProject) {
        return meTodoListRepository.getAllTodoList(idProject);
    }

    @Override
    @Synchronized
    public TodoListObject updateIndexTodoList(MeUpdateTodoListRequest request) {
        if(Integer.parseInt(request.getIndexBefore()) == Integer.parseInt(request.getIndexAfter())){
            throw new RestApiException(Message.ERROR_UNKNOWN);
        }
        if (Integer.parseInt(request.getIndexBefore()) < Integer.parseInt(request.getIndexAfter())) {
            meTodoListRepository.updateIndexTodoListDecs(Integer.parseInt(request.getIndexBefore()), Integer.parseInt(request.getIndexAfter()));
        } else {
            meTodoListRepository.updateIndexTodoListAsc(Integer.parseInt(request.getIndexBefore()), Integer.parseInt(request.getIndexAfter()));
        }
        meTodoListRepository.updateIndexTodoList(request.getIdTodoList(), Integer.parseInt(request.getIndexAfter()));
        return new TodoListObject(request.getIdTodoList(), Integer.parseInt(request.getIndexBefore()), Integer.parseInt(request.getIndexAfter()));
    }


}
