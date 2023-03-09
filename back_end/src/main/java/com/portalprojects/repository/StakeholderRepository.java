package com.portalprojects.repository;

import com.portalprojects.entity.Stakeholder;
import com.portalprojects.infrastructure.projection.SimpleEntityProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author thangncph26123
 */
@Repository(StakeholderRepository.NAME)
public interface StakeholderRepository extends JpaRepository<Stakeholder, String> {

    public static final String NAME = "BaseStakeholderRepository";

    @Query(value = """
            SELECT id, name FROM stakeholder
            """, nativeQuery = true)
    List<SimpleEntityProj> findAllSimpleEntity();
}
