package com.portalprojects.core.member.service;

import com.portalprojects.core.common.base.TodoObject;
import com.portalprojects.core.member.model.request.MeCreateOrDeleteAssignRequest;

import java.util.List;

/**
 * @author thangncph26123
 */
public interface MeAssignService {

    List<String> getAllMemberByIdTodo(String idTodo);

    TodoObject create(MeCreateOrDeleteAssignRequest request);

    TodoObject delete(MeCreateOrDeleteAssignRequest request);
}
