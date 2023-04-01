package com.portalprojects.core.member.service.impl;

import com.portalprojects.core.member.repository.MeMemberProjectRepository;
import com.portalprojects.core.member.service.MeMemberProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author thangncph26123
 */
@Service
@Validated
public class MeMemberProjectServiceImpl implements MeMemberProjectService {

    @Autowired
    private MeMemberProjectRepository meMemberProjectRepository;

    @Override
    public List<String> getAllMemberProject(String idProject) {
        return meMemberProjectRepository.getAllMemberProject(idProject);
    }
}
