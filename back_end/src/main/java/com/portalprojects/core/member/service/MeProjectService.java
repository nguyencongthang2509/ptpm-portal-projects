package com.portalprojects.core.member.service;

import com.portalprojects.core.common.base.PageableObject;
import com.portalprojects.core.common.base.PageableRequest;
import com.portalprojects.core.member.model.request.MeFindProjectRequest;
import com.portalprojects.core.member.model.response.MeProjectResponse;
import com.portalprojects.entity.Project;

/**
 * @author thangncph26123
 */
public interface MeProjectService {

    PageableObject<MeProjectResponse> getAllProjectByIdUser(MeFindProjectRequest request, String idUser);

    Project findById(String id);
}
