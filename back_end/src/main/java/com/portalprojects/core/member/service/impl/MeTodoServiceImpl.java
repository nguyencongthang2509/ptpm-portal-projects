package com.portalprojects.core.member.service.impl;

import com.portalprojects.core.member.model.response.MeDetailTodoResponse;
import com.portalprojects.core.member.model.response.MeTodoResponse;
import com.portalprojects.core.member.repository.MeTodoRepository;
import com.portalprojects.core.member.service.MeTodoService;
import com.portalprojects.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
