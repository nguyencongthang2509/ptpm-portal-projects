package com.portalprojects.infrastructure.constant;

import com.portalprojects.util.PropertiesReader;

/**
 * @author thangncph26123
 */

public enum Message {

    SUCCESS("Success"),

    //    ERROR_UNKNOWN("Error Unknown"),
    ERROR_UNKNOWN("Error Unknown"),
    PROJECT_NOT_EXISTS(PropertiesReader.getProperty(PropertyKeys.PROJECT_NOT_EXISTS));

    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
