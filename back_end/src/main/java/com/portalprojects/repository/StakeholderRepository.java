package com.portalprojects.repository;

import com.portalprojects.entity.Stakeholder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author thangncph26123
 */
@Repository(StakeholderRepository.NAME)
public interface StakeholderRepository  extends JpaRepository<Stakeholder, String> {

    public static final String NAME = "BaseStakeholderRepository";
}
