package com.portalprojects.repository;

import com.portalprojects.entity.PeriodProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author thangncph26123
 */
@Repository(PeriodProjectRepository.NAME)
public interface PeriodProjectRepository  extends JpaRepository<PeriodProject, String> {

    public static final String NAME = "BasePeriodProjectRepository";
}
