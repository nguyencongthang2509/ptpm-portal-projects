package com.portalprojects.core.member.service;

import com.portalprojects.core.member.model.response.MeTodoResponse;
import com.portalprojects.entity.Todo;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author thangncph26123
 */
public interface MeTodoService {

    List<MeTodoResponse> getToDoByPeriodAndPriorityLevel(String idPeriod, Integer statusTodo);

    Todo findById(String id);
}
