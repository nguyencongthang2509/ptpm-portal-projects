package com.portalprojects.core.admin.service.impl;

import com.portalprojects.core.admin.model.request.AdBaseProjectRepuest;
import com.portalprojects.core.admin.model.request.AdCreateProjectRepuest;
import com.portalprojects.core.admin.model.request.AdFindProjectRepuest;
import com.portalprojects.core.admin.model.request.AdUpdateProjectRepuest;
import com.portalprojects.core.admin.model.response.AdProjectReponse;
import com.portalprojects.core.admin.repository.AdProjectRepository;
import com.portalprojects.core.admin.service.AdProjectService;
import com.portalprojects.core.common.base.PageableObject;
import com.portalprojects.entity.Project;
import com.portalprojects.infrastructure.constant.Message;
import com.portalprojects.infrastructure.exception.rest.RestApiException;
import com.portalprojects.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

/**
 * @author NguyenVinh
 */
@Service
public class AdProjectServiceImpl implements AdProjectService {

    @Autowired
    private AdProjectRepository adProjectRepository;

    private FormUtils formUtils = new FormUtils();

    private List<AdProjectReponse> listProject;

    @Override
    public List<Project> findAllProject(Pageable pageable) {
        return adProjectRepository.findAllProject(pageable);
    }

    @Override
    public Project createProject(@Valid AdCreateProjectRepuest command) {
        Project project = formUtils.convertToObject(Project.class, command);
        return adProjectRepository.save(project);
    }

    @Override
    public PageableObject<AdProjectReponse> searchProject(final AdFindProjectRepuest rep) {
        Pageable pageable = PageRequest.of(rep.getPage(), rep.getSize());
        Page<AdProjectReponse> reponses = adProjectRepository.findByNameProject(rep, pageable);
        listProject = reponses.stream().toList();
        return new PageableObject<>(reponses);
    }

    // lấy project theo id
    @Override
    public Project findProjectById(String id) {
        Optional<Project> projectCheck = adProjectRepository.findById(id);
        if (!projectCheck.isPresent()) {
            throw new RestApiException(Message.PROJECT_NOT_EXIST);
        }
        return projectCheck.get();
    }

    @Override
    public Boolean removeProject(String id) {
        Optional<Project> projectCheck = adProjectRepository.findById(id);
        if (!projectCheck.isPresent()) {
            throw new RestApiException(Message.PROJECT_NOT_EXIST);
        }
        adProjectRepository.delete(projectCheck.get());
        return true;
    }

    @Override
    public Project updateProject(AdUpdateProjectRepuest comand) {
        Optional<Project> projectCheck = adProjectRepository.findById(comand.getId());
        if (!projectCheck.isPresent()) {
            throw new RestApiException(Message.PROJECT_NOT_EXIST);
        }
        Project project = projectCheck.get();
        project.setStatusProject(comand.getStatusProject());
        project.setCode(comand.getCode());
        project.setDescriptions(comand.getDescriptions());
        project.setName(comand.getName());
        project.setProgress(comand.getProgress());
        project.setStartTime(comand.getStartTime());
        project.setEndTime(comand.getEndTime());
        return adProjectRepository.save(project);
    }
}
