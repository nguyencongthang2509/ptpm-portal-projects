package com.portalprojects.core.member.service;

import com.portalprojects.core.member.model.response.MeLabelResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author thangncph26123
 */
public interface MeLabelService {

    List<MeLabelResponse> getAllLabelByIdTodo(String idTodo);
}
