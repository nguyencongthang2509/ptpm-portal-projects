package com.portalprojects.core.member.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

/**
 * @author thangncph26123
 */
@Getter
@Setter
public class MeDeleteDetailTodoRequest {

    private String id;

    private String todoId;

    private String indexTask;

    private String indexTodoInTask;
}
