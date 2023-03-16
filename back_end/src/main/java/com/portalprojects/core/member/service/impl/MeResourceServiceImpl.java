package com.portalprojects.core.member.service.impl;

import com.portalprojects.core.common.base.PageableObject;
import com.portalprojects.core.member.model.request.MeFindResourceRequest;
import com.portalprojects.core.member.model.response.MeResourceResponse;
import com.portalprojects.core.member.repository.MeResourceRepository;
import com.portalprojects.core.member.service.MeResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author thangncph26123
 */
@Service
public class MeResourceServiceImpl implements MeResourceService {

    @Autowired
    private MeResourceRepository meResourceRepository;

    @Override
    public List<MeResourceResponse> getAllResourceByIdProject(MeFindResourceRequest request, String idProject) {
        List<MeResourceResponse> res = meResourceRepository.getAllResourceByIdProject(idProject);
        return res;
    }
}
