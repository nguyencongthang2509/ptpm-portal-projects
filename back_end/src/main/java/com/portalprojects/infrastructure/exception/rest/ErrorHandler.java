package com.portalprojects.infrastructure.exception.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;

/**
 * @author thangncph26123
 */
@Component
public class ErrorHandler {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public ResponseEntity<?> handleRestApiException(RestApiException e, StompHeaderAccessor headerAccessor) {
        ErrorObject errorObject = new ErrorObject(e.getMessage());
        String sessionId = headerAccessor.getSessionId();
        simpMessagingTemplate.convertAndSend("/portal-projects/error/" + sessionId, errorObject);
        ApiError apiError = new ApiError(e.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}


