package com.portalprojects.repository;

import com.portalprojects.entity.Assign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author thangncph26123
 */
@Repository(AssignRepository.NAME)
public interface AssignRepository extends JpaRepository<Assign, String> {

    public static final String NAME = "BaseAssignRepository";
}
