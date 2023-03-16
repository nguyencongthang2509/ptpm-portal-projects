package com.portalprojects.core.member.repository;

import com.portalprojects.core.member.model.request.MeFindResourceRequest;
import com.portalprojects.core.member.model.response.MeResourceResponse;
import com.portalprojects.repository.ResourceRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author thangncph26123
 */
public interface MeResourceRepository extends ResourceRepository {

    @Query(value = """
            SELECT a.id, a.name, a.content FROM resource a WHERE a.project_id = :idProject
            ORDER BY a.created_date DESC
            """ , nativeQuery = true)
    List<MeResourceResponse> getAllResourceByIdProject(@Param("idProject") String idProject);
}
