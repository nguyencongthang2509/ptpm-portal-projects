package com.portalprojects.infrastructure.constant;

import com.portalprojects.util.PropertiesReader;

/**
 * @author thangncph26123
 */

public enum Message {

    SUCCESS("Success"),

    //    ERROR_UNKNOWN("Error Unknown"),
    ERROR_UNKNOWN("Error Unknown"),
    PROJECT_NOT_EXISTS(PropertiesReader.getProperty(PropertyKeys.PROJECT_NOT_EXISTS)),
    INVALID_DATE(PropertiesReader.getProperty(PropertyKeys.INVALID_DATE)),
    INVALID_END_TIME(PropertiesReader.getProperty(PropertyKeys.INVALID_END_TIME)),
    PERIOD_OVERLAP(PropertiesReader.getProperty(PropertyKeys.PERIOD_OVERLAP)),
    PERIOD_NOT_EXISTS(PropertiesReader.getProperty(PropertyKeys.PERIOD_NOT_EXISTS)) ,
    MEMBER_PROJECT_DELETE(PropertiesReader.getProperty(PropertyKeys.MEMBER_PROJECT_DELETE)) ,
    CODE_PROJECT_ALREADY_EXISTS(PropertiesReader.getProperty(PropertyKeys.CODE_PROJECT_ALREADY_EXISTS)) ,
    CODE_MENBER_PROJECT_ALREADY_EXISTS(PropertiesReader.getProperty(PropertyKeys.CODE_MENBER_PROJECT_ALREADY_EXISTS)) ,
    MEMBER_PROJECT_NOT_EXISTS(PropertiesReader.getProperty(PropertyKeys.MEMBER_PROJECT_NOT_EXISTS)) ;

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
