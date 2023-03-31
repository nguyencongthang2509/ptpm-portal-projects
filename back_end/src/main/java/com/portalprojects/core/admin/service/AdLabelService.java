package com.portalprojects.core.admin.service;

import com.portalprojects.core.admin.model.request.AdFindProjectRepuest;
import com.portalprojects.core.admin.model.response.AdLabelReponse;
import com.portalprojects.core.common.base.PageableObject;

/**
 * @author NguyenVinh
 */
public interface AdLabelService {

    PageableObject<AdLabelReponse> searchProject(final AdFindProjectRepuest rep);

}
