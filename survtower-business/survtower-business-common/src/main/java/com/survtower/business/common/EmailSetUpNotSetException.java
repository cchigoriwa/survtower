package com.survtower.business.common;

/**
 *
 * @author Daniel Nkhoma
 */
public class EmailSetUpNotSetException extends SurvtowerRuntimeException{

    public EmailSetUpNotSetException() {
    }

    public EmailSetUpNotSetException(String message) {
        super(message);
    }

    public EmailSetUpNotSetException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailSetUpNotSetException(Throwable cause) {
        super(cause);
    }

    public EmailSetUpNotSetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
