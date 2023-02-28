package com.portalprojects.repository;

import com.portalprojects.entity.MemberProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author thangncph26123
 */
@Repository(MemberProjectRepository.NAME)
public interface MemberProjectRepository  extends JpaRepository<MemberProject, String> {

    public static final String NAME = "BaseMemberProjectRepository";
}
