package com.plamena.novartoapi.exception;

public class CustomException extends Exception {
    private static final long serialVersionUID = 1L;
    private final String errorCode;
    private final String internalMessage;
    private final Exception lowLevelException;

    public CustomException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
        this.internalMessage = null;
        this.lowLevelException = null;
    }

    public CustomException(String errorCode,String msg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.internalMessage = msg;
        this.lowLevelException = null;
    }

    public CustomException(String errorCode, Exception lowLevelException) {
        super(errorCode);
        this.errorCode = errorCode;
        this.internalMessage = null;
        this.lowLevelException = lowLevelException;
    }

    public final String getErrorCode() {
        return this.errorCode;
    }

    public final String getInternalMessage() {
        return this.internalMessage;
    }

    public final Exception getLowLevelException() {
        return this.lowLevelException;
    }
    @Override
    public String getMessage() {
        return this.internalMessage;
    }
}
