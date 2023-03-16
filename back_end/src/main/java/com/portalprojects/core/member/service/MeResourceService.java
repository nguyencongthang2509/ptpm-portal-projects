package com.portalprojects.core.member.service;

import com.portalprojects.core.common.base.PageableObject;
import com.portalprojects.core.member.model.request.MeFindResourceRequest;
import com.portalprojects.core.member.model.response.MeResourceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author thangncph26123
 */
public interface MeResourceService {

    List<MeResourceResponse> getAllResourceByIdProject(MeFindResourceRequest request, String idProject);
}
