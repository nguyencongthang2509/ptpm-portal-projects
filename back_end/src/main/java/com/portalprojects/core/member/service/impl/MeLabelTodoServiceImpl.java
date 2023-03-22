package com.portalprojects.core.member.service.impl;

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
    public LabelTodo create(String idLabel, String idTodo) {
        LabelTodo labelTodo = new LabelTodo();
        labelTodo.setTodoId(idTodo);
        labelTodo.setLabelId(idLabel);
        return meLabelTodoRepository.save(labelTodo);
    }

    @Override
    @Synchronized
    public Boolean delete(String idLabel, String idTodo) {
        meLabelTodoRepository.delete(idLabel, idTodo);
        return true;
    }
}
