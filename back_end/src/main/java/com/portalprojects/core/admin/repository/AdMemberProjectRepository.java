package com.portalprojects.core.admin.repository;

import com.portalprojects.core.admin.model.request.AdFindProjectRepuest;
import com.portalprojects.core.admin.model.request.AdGetOneMemberProjectRequest;
import com.portalprojects.core.admin.model.response.AdMemberProjectReponse;
import com.portalprojects.entity.MemberProject;
import com.portalprojects.repository.MemberProjectRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author NguyenVinh
 */
@Repository
public interface AdMemberProjectRepository extends MemberProjectRepository {

    @Query(" SELECT  me FROM MemberProject me")
    List<MemberProject> findAllMember();

    @Query(value = """
             SELECT ROW_NUMBER() OVER(ORDER BY mp.last_modified_date DESC ) AS STT ,
                     mp.id,
                     mp.member_id,
                     mp.project_id,
                     mp.role,
                     mp.status,
             FROM member_project mp 
             JOIN project pro ON pro.id = mp.project_id
             WHERE  
             ( :#{#rep.name} IS NULL 
                OR :#{#rep.name} LIKE '' 
                OR pro.name LIKE %:#{#rep.name}% )          
            """ ,nativeQuery = true)
    List<AdMemberProjectReponse> findByName(@Param("rep") AdFindProjectRepuest rep);

    @Query(value = """
             SELECT ROW_NUMBER() OVER(ORDER BY mp.last_modified_date DESC ) AS STT ,
                     mp.id,
                     mp.member_id,
                     mp.project_id,
                     mp.role,
                     mp.status,
                     pro.name
             FROM member_project mp 
             JOIN project pro ON pro.id = mp.project_id
             WHERE  
                  pro.id = :idProject     
            """ ,nativeQuery = true)
    List<AdMemberProjectReponse> findAllMemberJoinProject(@Param("idProject") String idProject);

    @Query(value = """
             SELECT ROW_NUMBER() OVER(ORDER BY mp.last_modified_date DESC ) AS STT ,
                     mp.id,
                     mp.member_id,
                     mp.project_id,
                     mp.role,
                     mp.status,
                     pro.name
             FROM member_project mp 
            JOIN project pro ON pro.id = mp.project_id
             WHERE  
                  mp.member_id = :#{#rep.idMember}  AND  mp.project_id= :#{#rep.idProject}
            """ ,nativeQuery = true)
    AdMemberProjectReponse getOne (@Param("rep") AdGetOneMemberProjectRequest rep);
}
