package com.portalprojects.core.member.service.impl;

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
    public Assign create(String idMember, String idTodo) {
        Assign assign = new Assign();
        assign.setTodoId(idTodo);
        assign.setMemberId(idMember);
        return meAssignRepository.save(assign);
    }

    @Override
    @Synchronized
    public Boolean delete(String idMember, String idTodo) {
        meAssignRepository.delete(idMember, idTodo);
        return true;
    }
}
