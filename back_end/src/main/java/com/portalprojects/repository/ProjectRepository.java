package com.portalprojects.repository;

import com.portalprojects.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author thangncph26123
 */

@Repository(ProjectRepository.NAME)
public interface ProjectRepository extends JpaRepository<Project, String> {

    public static final String NAME = "BaseProjectRepository";

}
