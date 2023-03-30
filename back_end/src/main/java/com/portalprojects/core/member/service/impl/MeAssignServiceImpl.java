package com.portalprojects.core.member.service.impl;

import com.portalprojects.core.common.base.TodoObject;
import com.portalprojects.core.member.model.request.MeCreateOrDeleteAssignRequest;
import com.portalprojects.core.member.repository.MeAssignRepository;
import com.portalprojects.core.member.service.MeAssignService;
import com.portalprojects.entity.Assign;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author thangncph26123
 */
@Service
public class MeAssignServiceImpl implements MeAssignService {

    @Autowired
    private MeAssignRepository meAssignRepository;

    @Override
    public List<String> getAllMemberByIdTodo(String idTodo) {
        return meAssignRepository.getAllMemberByIdTodo(idTodo);
    }

    @Override
    @Synchronized
    public TodoObject create(MeCreateOrDeleteAssignRequest request) {
        Assign assign = new Assign();
        assign.setTodoId(request.getIdTodo());
        assign.setMemberId(request.getIdMember());
        TodoObject todoObject = TodoObject.builder().data(meAssignRepository.save(assign)).indexTask(Integer.parseInt(request.getIndexTask())).indexTodoInTask(Integer.parseInt(request.getIndexTodoInTask())).build();
        return todoObject;
    }

    @Override
    @Synchronized
    public TodoObject delete(MeCreateOrDeleteAssignRequest request) {
        meAssignRepository.delete(request.getIdMember(), request.getIdTodo());
        TodoObject todoObject = TodoObject.builder().data(request.getIdTodo()).indexTask(Integer.parseInt(request.getIndexTask())).indexTodoInTask(Integer.parseInt(request.getIndexTodoInTask())).build();
        return  todoObject;
    }
}
