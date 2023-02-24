package com.portalprojects.repository;

import com.portalprojects.entity.DuAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author thangncph26123
 */

@Repository(DuAnRepository.NAME)
public interface DuAnRepository extends JpaRepository<DuAn, String> {

    public static final String NAME = "BaseDuAnRepository";

}
