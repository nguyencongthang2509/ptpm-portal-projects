package com.portalprojects.core.admin.model.request;

import com.portalprojects.infrastructure.constant.StatusProject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

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
