package com.portalprojects.core.admin.repository;

import com.portalprojects.core.admin.model.request.AdFindProjectRepuest;
import com.portalprojects.core.admin.model.response.AdProjectReponse;
import com.portalprojects.entity.Project;
import com.portalprojects.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author NguyenVinh
 */

@Repository
public interface AdProjectRepository extends ProjectRepository {

    @Query(" SELECT  pro FROM Project pro")
    List<Project> findAllProject(Pageable pageable);


    @Query(value = """
             SELECT ROW_NUMBER() OVER(ORDER BY pro.last_modified_date DESC ) AS STT ,
                     pro.id,
                     pro.name,
                     pro.code,
                     pro.descriptions,
                     pro.status_project,
                     pro.start_time,
                     pro.end_time,
                     pro.progress,
                     pro.created_date
             FROM project pro 
             WHERE  
             ( :#{#rep.name} IS NULL 
                OR :#{#rep.name} LIKE '' 
                OR pro.name LIKE %:#{#rep.name}% )          
            """, nativeQuery = true)
    Page<AdProjectReponse> findByNameProject(@Param("rep") AdFindProjectRepuest rep, Pageable page);


}
