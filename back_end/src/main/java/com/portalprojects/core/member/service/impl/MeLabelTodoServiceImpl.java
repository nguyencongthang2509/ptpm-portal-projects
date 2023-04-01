package com.portalprojects.core.member.service.impl;

import com.portalprojects.core.common.base.TodoObject;
import com.portalprojects.core.member.model.request.MeCreateOrDeleteLabelTodoRequest;
import com.portalprojects.core.member.repository.MeLabelTodoRepository;
import com.portalprojects.core.member.service.MeLabelTodoService;
import com.portalprojects.entity.LabelTodo;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author thangncph26123
 */
@Service
@Validated
public class MeLabelTodoServiceImpl implements MeLabelTodoService {

    @Autowired
    private MeLabelTodoRepository meLabelTodoRepository;

    @Override
    @Synchronized
    public TodoObject create(MeCreateOrDeleteLabelTodoRequest request) {
        LabelTodo labelTodo = new LabelTodo();
        labelTodo.setTodoId(request.getIdTodo());
        labelTodo.setLabelId(request.getIdLabel());
        TodoObject todoObject = TodoObject.builder().data(meLabelTodoRepository.save(labelTodo)).indexTask(Integer.parseInt(request.getIndexTask())).indexTodoInTask(Integer.parseInt(request.getIndexTodoInTask())).build();
        return todoObject;
    }

    @Override
    @Synchronized
    public TodoObject delete(MeCreateOrDeleteLabelTodoRequest request) {
        meLabelTodoRepository.delete(request.getIdLabel(), request.getIdTodo());
        TodoObject todoObject = TodoObject.builder().data(request.getIdTodo()).indexTask(Integer.parseInt(request.getIndexTask())).indexTodoInTask(Integer.parseInt(request.getIndexTodoInTask())).build();
        return todoObject;
    }
}
