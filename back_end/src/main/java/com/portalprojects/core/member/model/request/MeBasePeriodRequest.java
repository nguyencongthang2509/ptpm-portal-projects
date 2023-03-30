package com.portalprojects.core.member.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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
