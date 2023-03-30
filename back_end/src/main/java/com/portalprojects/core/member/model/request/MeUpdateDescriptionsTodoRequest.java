package com.portalprojects.core.member.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * @author thangncph26123
 */
@Getter
@Setter
public class MeUpdateDescriptionsTodoRequest {

    @NotNull
    private String idTodo;

    @NotEmpty
    @Length(max = 1000, message = "Mô tả tối đa 1000 ký tự")
    private String descriptions;

    @NotNull
    private String indexTask;

    @NotNull
    private String indexTodoInTask;
}
