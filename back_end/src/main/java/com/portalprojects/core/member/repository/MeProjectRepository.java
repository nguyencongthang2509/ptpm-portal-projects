package com.portalprojects.core.member.repository;

import com.portalprojects.core.member.model.request.MeFindProjectRequest;
import com.portalprojects.core.member.model.response.MeProjectResponse;
import com.portalprojects.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author thangncph26123
 */
public interface MeProjectRepository extends ProjectRepository {

    @Query(value = """
            SELECT a.id, a.name, a.descriptions, a.start_time, a.status_project as status, a.progress 
            FROM project a JOIN member_project b ON a.id = b.project_id 
            WHERE b.member_id = :idUser ORDER BY a.created_date DESC
            """, countQuery = """
            SELECT COUNT(1) 
            FROM project a JOIN member_project b ON a.id = b.project_id 
            WHERE b.member_id = :idUser ORDER BY a.created_date DESC
            """, nativeQuery = true)
    Page<MeProjectResponse> getAllProjectById(Pageable page, @Param("idUser") String idUser);
}
