package com.portalprojects.core.admin.model.request;

import com.portalprojects.core.common.base.PageableRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author NguyenVinh
 */
@Getter
@Setter
public class AdFindProjectRepuest extends PageableRequest {

    private String name;

}
