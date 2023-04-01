package com.portalprojects.core.admin.model.request;

import com.portalprojects.core.common.base.PageableRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author NguyenVinh
 */
@Setter
@Getter
public class AdFindLabelRequest extends PageableRequest {

    private String name;

}
