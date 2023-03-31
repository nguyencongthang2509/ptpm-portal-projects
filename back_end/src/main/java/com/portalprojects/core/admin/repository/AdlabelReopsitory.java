package com.portalprojects.core.admin.repository;

import com.portalprojects.core.admin.model.request.AdFindProjectRepuest;
import com.portalprojects.core.admin.model.response.AdProjectReponse;
import com.portalprojects.repository.LabelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author NguyenVinh
 */
public interface AdlabelReopsitory extends LabelRepository {

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
            """, countQuery = """
            SELECT COUNT(pro.id) FROM project pro
            WHERE 
             ( :#{#rep.name} IS NULL 
                OR :#{#rep.name} LIKE '' 
                OR pro.name LIKE %:#{#rep.name}% ) 
            ORDER BY pro.last_modified_date DESC
            """ ,nativeQuery = true)
    Page<AdProjectReponse> findByNameProject(@Param("rep") AdFindProjectRepuest rep, Pageable page);
}
