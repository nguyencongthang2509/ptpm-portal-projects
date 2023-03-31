package com.portalprojects.core.admin.model.response;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author NguyenVinh
 */
public interface AdLabelReponse {

    Integer getSTT();

    @Value("#{target.code}")
    String getCode();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.color_label}")
    String getColorLabel();

}
