package com.portalprojects.core.admin.service;

import com.portalprojects.core.admin.model.request.AdCearteMemberProjectRequest;
import com.portalprojects.core.admin.model.request.AdFindProjectRepuest;
import com.portalprojects.core.admin.model.request.AdGetOneMemberProjectRequest;
import com.portalprojects.core.admin.model.request.AdUpdateMemberProjectRequest;
import com.portalprojects.core.admin.model.response.AdMemberProjectReponse;
import com.portalprojects.entity.MemberProject;
import jakarta.validation.Valid;

import java.util.List;

/**
 * @author NguyenVinh
 */
public interface AdMemberProjectService {

    List<AdMemberProjectReponse> searchProject(final AdFindProjectRepuest rep);

    List<AdMemberProjectReponse> findAllMemberJoinProject(final String idProject);

    List<MemberProject> getAll();

    MemberProject createMemberProject (@Valid final AdCearteMemberProjectRequest command);

    MemberProject updateMemberProject (@Valid final AdUpdateMemberProjectRequest command);

    AdMemberProjectReponse getOne (final AdGetOneMemberProjectRequest command);
}
