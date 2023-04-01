package com.portalprojects.core.admin.service;

import com.portalprojects.core.admin.model.request.AdCreatLabelRequest;
import com.portalprojects.core.admin.model.request.AdFindLabelRequest;
import com.portalprojects.core.admin.model.request.AdUpdateLabelRequest;
import com.portalprojects.core.admin.model.response.AdLabelReponse;
import com.portalprojects.core.common.base.PageableObject;
import com.portalprojects.entity.Label;
import jakarta.validation.Valid;

/**
 * @author NguyenVinh
 */
public interface AdLabelService {

    PageableObject<AdLabelReponse> searchLabel(final AdFindLabelRequest rep);

    Label creatLabel(@Valid final AdCreatLabelRequest command);

    Label upadteLabel(@Valid final AdUpdateLabelRequest command);

    boolean deleteLabel(final String id);

    Label getOneByIdLable (final String id);

}
