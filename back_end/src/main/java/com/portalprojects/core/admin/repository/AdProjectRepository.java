package com.portalprojects.core.admin.repository;

import com.portalprojects.entity.Project;
import com.portalprojects.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author NguyenVinh
 */

@Repository
public interface AdProjectRepository extends ProjectRepository {

    @Query(" SELECT  pro FROM Project pro")
    List<Project> findAllProject(Pageable pageable);


//    Page<>


}
