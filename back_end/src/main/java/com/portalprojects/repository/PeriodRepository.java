package com.portalprojects.repository;

import com.portalprojects.entity.Period;
import com.portalprojects.infrastructure.projection.SimpleEntityProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author thangncph26123
 */
@Repository(PeriodRepository.NAME)
public interface PeriodRepository extends JpaRepository<Period, String> {

    public static final String NAME = "BasePeriodRepository";

    @Query(value = """
            SELECT id, name FROM period
            """, nativeQuery = true)
    List<SimpleEntityProj> findAllSimpleEntity();
}
