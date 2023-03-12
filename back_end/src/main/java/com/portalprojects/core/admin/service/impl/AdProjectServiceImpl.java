package com.portalprojects.core.admin.service.impl;

import com.portalprojects.core.admin.repository.AdProjectRepository;
import com.portalprojects.core.admin.service.AdProjectService;
import com.portalprojects.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author NguyenVinh
 */
@Service
public class AdProjectServiceImpl implements AdProjectService {

    @Autowired
    private AdProjectRepository adProjectRepository;

    @Override
    public List<Project> findAllProject(Pageable pageable) {
        return adProjectRepository.findAllProject(pageable);
    }
}
