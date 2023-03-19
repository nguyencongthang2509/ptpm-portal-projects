package com.portalprojects.core.member.service.impl;

import com.portalprojects.core.member.repository.MeAssignRepository;
import com.portalprojects.core.member.service.MeAssignService;
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
}
