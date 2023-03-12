package com.portalprojects.core.admin.model.response;

import com.portalprojects.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author NguyenVinh
 */
public interface AdProjectReponse extends IsIdentified {

    Integer getSTT();

    @Value("#{target.created_date}")
    String getName();

    @Value("#{target.code}")
    String getCode();

    @Value("#{target.descriptions}")
    String getDescriptions();

    @Value("#{target.start_time}")
    Long getStartTime();

    @Value("#{target.end_time}")
    Long getEndTimne();

    @Value("#{target.created_date}")
    Long  getCreateDate();



}
