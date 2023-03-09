package com.portalprojects.repository;

import com.portalprojects.entity.Project;
import com.portalprojects.infrastructure.projection.SimpleEntityProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author thangncph26123
 */

@Repository(ProjectRepository.NAME)
public interface ProjectRepository extends JpaRepository<Project, String> {

    public static final String NAME = "BaseProjectRepository";

    @Query(value = """
            SELECT id, name FROM project
            """, nativeQuery = true)
    List<SimpleEntityProj> findAllSimpleEntity();

}
