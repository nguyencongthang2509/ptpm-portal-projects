package com.portalprojects.core.admin.model.response;

import com.portalprojects.entity.Project;
import com.portalprojects.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * @author NguyenVinh
 */
@Projection(types = {Project.class})
public interface AdProjectReponse extends IsIdentified {

    Integer getSTT();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.code}")
    String getCode();

    @Value("#{target.descriptions}")
    String getDescriptions();

    @Value("#{target.start_time}")
    Long getStartTime();

    @Value("#{target.end_time}")
    Long getEndTimne();

    @Value("#{target.progress}")
    Short  getProgress();

    @Value("#{target.created_date}")
    Long  getCreateDate();




}
