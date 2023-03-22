package com.portalprojects.core.member.service;

import com.portalprojects.core.common.base.TaskObject;
import com.portalprojects.core.member.model.request.MeCreateOrDeleteAssignRequest;

import java.util.List;

/**
 * @author thangncph26123
 */
public interface MeAssignService {

    List<String> getAllMemberByIdTodo(String idTodo);

    TaskObject create(MeCreateOrDeleteAssignRequest request);

    TaskObject delete(MeCreateOrDeleteAssignRequest request);
}
