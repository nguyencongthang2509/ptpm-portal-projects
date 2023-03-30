package com.portalprojects.core.admin.model.response;

import com.portalprojects.entity.MemberProject;
import com.portalprojects.entity.Project;
import com.portalprojects.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * @author NguyenVinh
 */
@Projection(types = {MemberProject.class , Project.class})
public interface AdMemberProjectReponse extends IsIdentified {

     Integer getSTT();

     @Value("#{target.member_id}")
     String getMemberId();

     @Value("#{target.project_id }")
     String getProjectId();

     @Value("#{target.role}")
     String getRole();

     @Value("#{target.status_work}")
     String getStatus();

     @Value("#{target.name}")
     String getNameMember();
}
