package com.portalprojects.core.member.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author thangncph26123
 */
@Getter
@Setter
public class MeBasePeriodRequest {

    @NotEmpty
    @NotBlank
    private String name;

    private String descriptions;

    private String startTime;

    private String endTime;

    private String target;

    @NotNull
    @NotBlank
    private String projectId;
}
