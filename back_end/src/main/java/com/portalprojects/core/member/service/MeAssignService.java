package com.portalprojects.core.member.service;

import com.portalprojects.entity.Assign;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author thangncph26123
 */
public interface MeAssignService {

    List<String> getAllMemberByIdTodo(String idTodo);

    Assign create(String idMember, String idTodo);

    Boolean delete(String idMember, String idTodo);
}
