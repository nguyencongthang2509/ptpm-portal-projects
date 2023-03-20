package com.portalprojects.core.member.service.impl;

import com.portalprojects.core.member.model.response.MeLabelResponse;
import com.portalprojects.core.member.repository.MeLabelRepository;
import com.portalprojects.core.member.service.MeLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author thangncph26123
 */
@Service
public class MeLabelServiceImpl implements MeLabelService {

    @Autowired
    private MeLabelRepository meLabelRepository;

    @Override
    public List<MeLabelResponse> getAllLabelByIdTodo(String idTodo) {
        return meLabelRepository.getAllLabelByIdTodo(idTodo);
    }
}
