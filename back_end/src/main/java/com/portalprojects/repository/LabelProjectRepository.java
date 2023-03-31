package com.portalprojects.repository;

import com.portalprojects.entity.LabelProject;
import com.portalprojects.infrastructure.projection.SimpleEntityProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author thangncph26123
 */
@Repository(LabelProjectRepository.NAME)
public interface LabelProjectRepository extends JpaRepository<LabelProject, String> {

    public static final String NAME = "BaseLabelProjectRepository";

}
