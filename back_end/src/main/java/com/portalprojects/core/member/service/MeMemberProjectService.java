package com.portalprojects.core.member.service;

import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author thangncph26123
 */
public interface MeMemberProjectService {

    List<String> getAllMemberProject(String idProject);
}
