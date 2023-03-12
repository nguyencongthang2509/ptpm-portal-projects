package com.portalprojects.core.admin.service;

import com.portalprojects.entity.Project;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author NguyenVinh
 */

public interface AdProjectService {

    List<Project> findAllProject (Pageable pageable);
}
