package com.portalprojects.core.admin.service.impl;

import com.portalprojects.core.admin.model.request.AdCearteMemberProjectRequest;
import com.portalprojects.core.admin.model.request.AdFindProjectRepuest;
import com.portalprojects.core.admin.model.request.AdUpdateMemberProjectRequest;
import com.portalprojects.core.admin.model.response.AdMemberProjectReponse;
import com.portalprojects.core.admin.repository.AdMemberProjectRepository;
import com.portalprojects.core.admin.service.AdMemberProjectService;
import com.portalprojects.entity.MemberProject;
import com.portalprojects.infrastructure.constant.Message;
import com.portalprojects.infrastructure.exception.rest.RestApiException;
import com.portalprojects.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author NguyenVinh
 */
@Service
public class AdMemberProjectServiceImpl implements AdMemberProjectService {

    @Autowired
    private AdMemberProjectRepository adMemberProjectRepository;

    private FormUtils formUtils = new FormUtils();


    @Override
    public List<AdMemberProjectReponse> searchProject(AdFindProjectRepuest rep) {
        return adMemberProjectRepository.findByName(rep);
    }

    @Override
    public List<AdMemberProjectReponse> findAllMemberJoinProject(String idProject) {
        return adMemberProjectRepository.findAllMemberJoinProject(idProject);
    }

    @Override
    public List<MemberProject> getAll() {
        return adMemberProjectRepository.findAll();
    }

    @Override
    public MemberProject createMemberProject(AdCearteMemberProjectRequest command) {
        AdMemberProjectReponse reponse = adMemberProjectRepository.getOne(command.getProjectId(), command.getMemberId());
        if (reponse != null) {
            throw new RestApiException(Message.CODE_MENBER_PROJECT_ALREADY_EXISTS);
        }
        MemberProject memberProject = formUtils.convertToObject(MemberProject.class, command);
        return adMemberProjectRepository.save(memberProject);
    }

    @Override
    public MemberProject updateMemberProject(AdUpdateMemberProjectRequest command) {
        Optional<MemberProject> optional = adMemberProjectRepository.findById(command.getId());
        if (!optional.isPresent()) {
            throw new RestApiException(Message.MEMBER_PROJECT_NOT_EXISTS);
        }
        return optional.get();
    }

    @Override
    public AdMemberProjectReponse getOne(final String idMember, final String idProject) {
        AdMemberProjectReponse reponse = adMemberProjectRepository.getOne(idProject, idMember);
        return reponse;
    }

    @Override
    public Boolean delete(String id) {
        Optional<MemberProject> optional = adMemberProjectRepository.findById(id);
        if (!optional.isPresent()) {
            throw new RestApiException(Message.MEMBER_PROJECT_NOT_EXISTS);
        }
        adMemberProjectRepository.delete(optional.get());
        return true;
    }
}
