package com.portalprojects.core.member.repository;

import com.portalprojects.repository.MemberProjectRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author thangncph26123
 */
public interface MeMemberProjectRepository extends MemberProjectRepository {

    @Query(value = """
            SELECT a.member_id FROM member_project a WHERE a.project_id = :idProject
            """, nativeQuery = true)
    List<String> getAllMemberProject(@Param("idProject") String idProject);
}
