package com.portalprojects.core.admin.service;

import com.portalprojects.core.admin.model.request.AdCearteProjectRepuest;
import com.portalprojects.core.admin.model.request.AdFindProjectRepuest;
import com.portalprojects.core.admin.model.request.AdUpdateProjectRequest;
import com.portalprojects.core.admin.model.response.AdProjectReponse;
import com.portalprojects.core.common.base.PageableObject;
import com.portalprojects.entity.Project;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;

/**
 * @author NguyenVinh
 */

public interface AdProjectService {

    List<Project> findAllProject (Pageable pageable);

    Project createProject (@Valid final AdCearteProjectRepuest command);

    PageableObject<AdProjectReponse> searchProject(final AdFindProjectRepuest rep);

    Project findProjectById(final String id);

    Boolean removeProject (final String id);

    Project updateProject (final AdUpdateProjectRequest comand);
}
