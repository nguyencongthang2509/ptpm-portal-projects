package com.portalprojects.util;

import com.portalprojects.repository.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author thangncph26123
 */
@Component
public class PeriodHelper {

    @Autowired
    @Qualifier(PeriodRepository.NAME)
    private PeriodRepository periodRepository;

    public String genCodePeriod(String projectId) {
        Integer countPeriod = periodRepository.countSimpleEntityByIdProject(projectId);
        Integer newCountPeriod = ++countPeriod;
        return "GD_" + newCountPeriod;
    }
}
