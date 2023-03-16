package com.portalprojects.core.member.repository;

import com.portalprojects.core.member.model.request.MeFindPeriodRequest;
import com.portalprojects.core.member.model.response.MePeriodResponse;
import com.portalprojects.entity.Period;
import com.portalprojects.repository.PeriodRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author thangncph26123
 */
public interface MePeriodRepository extends PeriodRepository {

    @Query(value = """
            SELECT a.id, ROW_NUMBER() OVER(ORDER BY a.created_date DESC) AS stt, a.code, a.name, a.progress, a.target, a.start_time, a.end_time, a.descriptions, a.status_period FROM period a
            WHERE a.project_id = :idProject ORDER BY a.created_date DESC
            """, nativeQuery = true)
    List<MePeriodResponse> getAllPeriodByIdProject(@Param("idProject") String idProject);

    @Query(value = """
            SELECT * FROM period a
            WHERE a.project_id = :idProject
            """, nativeQuery = true)
    List<Period> getAllEntityPeriodByIdProject(@Param("idProject") String idProject);

    @Query(value = """
            SELECT a.id, ROW_NUMBER() OVER(ORDER BY a.created_date DESC) AS stt, a.code, a.name, a.progress, a.target, a.start_time, a.end_time, a.descriptions, a.status_period FROM period a
            WHERE a.project_id = :idProject AND
            (:#{#req.namePeriod} IS NULL OR :#{#req.namePeriod} LIKE ''
            OR a.name LIKE %:#{#req.namePeriod}%) 
            ORDER BY a.created_date DESC
            """, countQuery = """
            SELECT COUNT(1) FROM period a
            WHERE a.project_id = :idProject AND
            (:#{#req.namePeriod} IS NULL OR :#{#req.namePeriod} LIKE ''
            OR a.name LIKE %:#{#req.namePeriod}%) 
            ORDER BY a.created_date DESC
            """, nativeQuery = true)
    Page<MePeriodResponse> getAllPeriod(@Param("req") MeFindPeriodRequest req, Pageable pageable, @Param("idProject") String idProject);

}
