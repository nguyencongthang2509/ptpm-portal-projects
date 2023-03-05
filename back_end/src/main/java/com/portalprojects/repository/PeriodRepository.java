package com.portalprojects.repository;

import com.portalprojects.entity.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author thangncph26123
 */
@Repository(PeriodRepository.NAME)
public interface PeriodRepository  extends JpaRepository<Period, String> {

    public static final String NAME = "BasePeriodRepository";
}
