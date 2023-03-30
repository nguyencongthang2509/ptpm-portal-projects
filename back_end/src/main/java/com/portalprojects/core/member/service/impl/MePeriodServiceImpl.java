package com.portalprojects.core.member.service.impl;

import com.portalprojects.core.common.base.PageableObject;
import com.portalprojects.core.member.model.request.MeBasePeriodRequest;
import com.portalprojects.core.member.model.request.MeFindPeriodRequest;
import com.portalprojects.core.member.model.request.MeUpdatePeriodRequest;
import com.portalprojects.core.member.model.response.MePeriodResponse;
import com.portalprojects.core.member.repository.MePeriodRepository;
import com.portalprojects.core.member.repository.MeProjectRepository;
import com.portalprojects.core.member.service.MePeriodService;
import com.portalprojects.entity.Period;
import com.portalprojects.entity.Project;
import com.portalprojects.infrastructure.constant.Message;
import com.portalprojects.infrastructure.constant.StatusPeriod;
import com.portalprojects.infrastructure.exception.rest.RestApiException;
import com.portalprojects.infrastructure.projection.SimpleEntityProj;
import com.portalprojects.util.PeriodHelper;
import jakarta.validation.Valid;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author thangncph26123
 */
@Service
@Validated
public class MePeriodServiceImpl implements MePeriodService {

    @Autowired
    private MePeriodRepository mePeriodRepository;

    @Autowired
    private MeProjectRepository meProjectRepository;

    @Autowired
    private PeriodHelper periodHelper;

    @Override
    public List<SimpleEntityProj> findAllSimpleEntity() {
        return mePeriodRepository.findAllSimpleEntity();
    }

    @Override
    public List<MePeriodResponse> getAllPeriodByIdProject(MeFindPeriodRequest req, String idProject) {
        return mePeriodRepository.getAllPeriodByIdProject(idProject);
    }

    @Override
    public PageableObject<MePeriodResponse> getAllPeriod(MeFindPeriodRequest request, String idProject) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<MePeriodResponse> res = mePeriodRepository.getAllPeriod(request, pageable, idProject);
        return new PageableObject(res);
    }

    @Override
    public Period findById(String id) {
        return mePeriodRepository.findById(id).get();
    }

    @Override
    @Synchronized
    public Period create(@Valid final MeBasePeriodRequest meCreatePeriodRequest) {
        MeBasePeriodRequest request = new MeBasePeriodRequest();
        request.setStartTime(meCreatePeriodRequest.getStartTime());
        request.setEndTime(meCreatePeriodRequest.getEndTime());
        request.setProjectId(meCreatePeriodRequest.getProjectId());
        Optional<Project> projectCheck = meProjectRepository.findById(meCreatePeriodRequest.getProjectId());
        if (!projectCheck.isPresent()) {
            throw new RestApiException(Message.PROJECT_NOT_EXISTS);
        }
        Period result = checkDatePeriod(request, null);
        Period period = new Period();
        period.setCode(periodHelper.genCodePeriod(meCreatePeriodRequest.getProjectId()));
        period.setName(meCreatePeriodRequest.getName());
        period.setDescriptions(meCreatePeriodRequest.getDescriptions());
        period.setTarget(meCreatePeriodRequest.getTarget());
        period.setProgress((short) 0);
        period.setStartTime(result.getStartTime());
        period.setEndTime(result.getEndTime());
        period.setProjectId(meCreatePeriodRequest.getProjectId());
        Long currentTime = new Date().getTime();
        if (currentTime < result.getStartTime()) {
            period.setStatusPeriod(StatusPeriod.CHUA_DIEN_RA);
        }
        if (result.getStartTime() <= currentTime && currentTime <= result.getEndTime()) {
            period.setStatusPeriod(StatusPeriod.DANG_DIEN_RA);
        }
        return mePeriodRepository.save(period);
    }

    @Override
    @Synchronized
    public Period update(@Valid final MeUpdatePeriodRequest meUpdatePeriodRequest) {
        Optional<Period> periodCheck = mePeriodRepository.findById(meUpdatePeriodRequest.getId());

        if (!periodCheck.isPresent()) {
            throw new RestApiException(Message.PERIOD_NOT_EXISTS);
        }
        MeUpdatePeriodRequest request = new MeUpdatePeriodRequest();
        request.setStartTime(meUpdatePeriodRequest.getStartTime());
        request.setEndTime(meUpdatePeriodRequest.getEndTime());
        request.setProjectId(meUpdatePeriodRequest.getProjectId());

        Period result = checkDatePeriod(request, meUpdatePeriodRequest.getId());
        Period period = periodCheck.get();
        period.setCode(periodCheck.get().getCode());
        period.setName(meUpdatePeriodRequest.getName());
        period.setDescriptions(meUpdatePeriodRequest.getDescriptions());
        period.setTarget(meUpdatePeriodRequest.getTarget());
        period.setProgress(periodCheck.get().getProgress());
        period.setStartTime(result.getStartTime());
        period.setEndTime(result.getEndTime());
        period.setProjectId(meUpdatePeriodRequest.getProjectId());
        return mePeriodRepository.save(period);
    }

    @Override
    public Period checkDatePeriod(MeBasePeriodRequest request, String id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = sdf.parse(request.getStartTime());
            endTime = sdf.parse(request.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RestApiException(Message.INVALID_DATE);
        }
        Long startTimeLong = startTime.getTime();
        Long endTimeLong = endTime.getTime();
        if (endTimeLong < startTimeLong) {
            throw new RestApiException((Message.INVALID_END_TIME));
        }
        List<Period> listPeriod = mePeriodRepository.getAllEntityPeriodByIdProject(request.getProjectId());
        for (Period period : listPeriod) {
            if (id != null) {
                if (period.getId().equals(id)) {
                    continue;
                }
            }
            if (startTimeLong < period.getEndTime()
                    && endTimeLong > period.getStartTime()) {
                throw new RestApiException(Message.PERIOD_OVERLAP);
            }
        }
        Period result = new Period();
        result.setStartTime(startTimeLong);
        result.setEndTime(endTimeLong);
        return result;
    }


}
