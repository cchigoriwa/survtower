package com.survtower.business.common;

/**
 *
 * @author Charles Chigoriwa
 */
public class SurvtowerRuntimeException extends RuntimeException{

    public SurvtowerRuntimeException() {
    }

    public SurvtowerRuntimeException(String message) {
        super(message);
    }

    public SurvtowerRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SurvtowerRuntimeException(Throwable cause) {
        super(cause);
    }

    public SurvtowerRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
    
}
