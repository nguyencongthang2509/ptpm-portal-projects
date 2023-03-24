package com.portalprojects.core.member.service.impl;

import com.portalprojects.core.common.base.TaskObject;
import com.portalprojects.core.member.model.request.MeCreateOrDeleteLabelTodoRequest;
import com.portalprojects.core.member.repository.MeLabelTodoRepository;
import com.portalprojects.core.member.service.MeLabelTodoService;
import com.portalprojects.entity.LabelTodo;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author thangncph26123
 */
@Service
public class MeLabelTodoServiceImpl implements MeLabelTodoService {

    @Autowired
    private MeLabelTodoRepository meLabelTodoRepository;

    @Override
    @Synchronized
    public TaskObject create(MeCreateOrDeleteLabelTodoRequest request) {
        LabelTodo labelTodo = new LabelTodo();
        labelTodo.setTodoId(request.getIdTodo());
        labelTodo.setLabelId(request.getIdLabel());
        return new TaskObject(meLabelTodoRepository.save(labelTodo), Integer.parseInt(request.getIndexTask()), Integer.parseInt(request.getIndexTodoInTask()));
    }

    @Override
    @Synchronized
    public TaskObject delete(MeCreateOrDeleteLabelTodoRequest request) {
        meLabelTodoRepository.delete(request.getIdLabel(), request.getIdTodo());
        return new TaskObject(request.getIdTodo(), Integer.parseInt(request.getIndexTask()), Integer.parseInt(request.getIndexTodoInTask()));
    }
}
