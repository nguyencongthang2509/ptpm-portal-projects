package com.portalprojects.core.member.model.response;

import com.portalprojects.entity.Project;
import com.portalprojects.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * @author thangncph26123
 */
@Projection(types = {Project.class})
public interface MeProjectResponse extends IsIdentified {

    @Value("#{target.name}")
    String getName();

    @Value("#{target.descriptions}")
    String getDescriptions();

    @Value("#{target.start_time}")
    Long getStartTime();

    @Value("#{target.status}")
    Integer getStatus();

    @Value("#{target.progress}")
    Short getProgress();
}