package com.portalprojects.core.member.service.impl;

import com.portalprojects.core.common.base.TaskObject;
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
    public TaskObject create(MeCreateOrDeleteAssignRequest request) {
        Assign assign = new Assign();
        assign.setTodoId(request.getIdTodo());
        assign.setMemberId(request.getIdMember());
        return new TaskObject(meAssignRepository.save(assign), Integer.parseInt(request.getIndexTask()), Integer.parseInt(request.getIndexTodoInTask()));
    }

    @Override
    @Synchronized
    public TaskObject delete(MeCreateOrDeleteAssignRequest request) {
        meAssignRepository.delete(request.getIdMember(), request.getIdTodo());
        return new TaskObject(request.getIdTodo(), Integer.parseInt(request.getIndexTask()), Integer.parseInt(request.getIndexTodoInTask()));
    }
}
