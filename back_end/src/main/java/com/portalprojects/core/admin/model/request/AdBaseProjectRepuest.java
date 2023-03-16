package com.portalprojects.core.admin.model.request;

import com.portalprojects.infrastructure.constant.StatusProject;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author NguyenVinh
 */
@Getter
@Setter
public abstract class AdBaseProjectRepuest {

    @NotEmpty
    @NotBlank
    private String code;

    @NotEmpty
    @NotBlank
    private String name;

    @NotEmpty
    @NotBlank
    private String descriptions;

    @NotEmpty
    @NotBlank
    private Long startTime;

    @NotEmpty
    @NotBlank
    private Long endTime;

    @NotEmpty
    @NotBlank
    private Short progress;

    @NotEmpty
    @NotBlank
    private StatusProject statusProject;
}
