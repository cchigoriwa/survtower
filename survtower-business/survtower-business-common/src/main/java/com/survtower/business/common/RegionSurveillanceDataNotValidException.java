package com.survtower.business.common;

/**
 *
 * @author Daniel Nkhoma
 */
public class RegionSurveillanceDataNotValidException extends SurvtowerRuntimeException{

    public RegionSurveillanceDataNotValidException() {
    }

    public RegionSurveillanceDataNotValidException(String message) {
        super(message);
    }

    public RegionSurveillanceDataNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegionSurveillanceDataNotValidException(Throwable cause) {
        super(cause);
    }

    public RegionSurveillanceDataNotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
