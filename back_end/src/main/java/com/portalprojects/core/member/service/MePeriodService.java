package com.portalprojects.core.member.service;

import com.portalprojects.core.common.base.PageableObject;
import com.portalprojects.core.member.model.request.MeBasePeriodRequest;
import com.portalprojects.core.member.model.request.MeFindPeriodRequest;
import com.portalprojects.core.member.model.request.MeUpdatePeriodRequest;
import com.portalprojects.core.member.model.response.MePeriodResponse;
import com.portalprojects.entity.Period;
import com.portalprojects.infrastructure.projection.SimpleEntityProj;
import jakarta.validation.Valid;

import java.util.List;

/**
 * @author thangncph26123
 */
public interface MePeriodService {

    List<SimpleEntityProj> findAllSimpleEntity();

    List<MePeriodResponse> getAllPeriodByIdProject(MeFindPeriodRequest req,String idProject);

    PageableObject<MePeriodResponse> getAllPeriod(MeFindPeriodRequest req, String idProject);

    Period findById(String id);

    Period create(@Valid final MeBasePeriodRequest request);

    Period update(@Valid final MeUpdatePeriodRequest meUpdatePeriodRequest);

    Period checkDatePeriod(MeBasePeriodRequest request, String id);
}
