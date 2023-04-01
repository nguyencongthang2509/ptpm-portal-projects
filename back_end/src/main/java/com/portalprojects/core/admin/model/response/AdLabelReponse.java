package com.portalprojects.core.admin.model.response;

import com.portalprojects.entity.Label;
import com.portalprojects.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * @author NguyenVinh
 */
@Projection(types = {Label.class})
public interface AdLabelReponse extends IsIdentified {

    Integer getSTT();

    @Value("#{target.code}")
    String getCode();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.color_label}")
    String getColorLabel();

}
