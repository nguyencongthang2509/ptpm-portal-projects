package com.portalprojects.core.member.model.response;

import com.portalprojects.entity.Label;
import com.portalprojects.entity.Period;
import com.portalprojects.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * @author thangncph26123
 */
@Projection(types = {Label.class})
public interface MeLabelResponse extends IsIdentified {

    @Value("#{target.code}")
    String getCode();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.color_label}")
    String getColorLabel();
}
