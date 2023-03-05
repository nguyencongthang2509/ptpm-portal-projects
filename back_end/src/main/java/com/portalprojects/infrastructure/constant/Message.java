package com.portalprojects.infrastructure.constant;

/**
 * @author thangncph26123
 */

public enum Message {

    SUCCESS("Success"),

    //    ERROR_UNKNOWN("Error Unknown"),
    ERROR_UNKNOWN("Error Unknown");
//    CHUYEN_NGANH_NOT_EXIST(PropertiesReader.getProperty(PropertyKeys.CHUYEN_NGANH_NOT_EXIST)),

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
