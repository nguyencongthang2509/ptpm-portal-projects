package com.portalprojects.core.member.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * @author thangncph26123
 */
@Getter
@Setter
public abstract class MeBaseDetailTodoRequest {

    @NotEmpty
    @NotBlank
    @Length(max = 100)
    private String name;

    private String idTodo;
}
