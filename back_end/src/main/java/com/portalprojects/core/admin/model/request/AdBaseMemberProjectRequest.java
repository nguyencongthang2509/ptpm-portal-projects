package com.portalprojects.core.admin.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author NguyenVinh
 */
@Setter
@Getter
public abstract class AdBaseMemberProjectRequest {

    @NotEmpty
    @NotBlank
    private String memberId;

    @NotEmpty
    @NotBlank
    private String projectId;

    @NotEmpty
    @NotBlank
    private String role;

    @NotEmpty
    @NotBlank
    private String status;

}
