package com.plamena.novartoapi.dto;

import java.util.Date;

public class ServiceResponse<T> {
    public enum Status {SUCCESS, FAILURE};

    private Status statusCode;

    private T data;

    private String message;

    private String errorCode;

    private Long currentTime;

    public ServiceResponse(Status statusCode) {
        this.statusCode = statusCode;
        this.currentTime = new Date().getTime();
    }

    public ServiceResponse(Status statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
        this.currentTime = new Date().getTime();
    }

    public ServiceResponse(Status statusCode, T data, String message) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
        this.currentTime = new Date().getTime();
    }

    public ServiceResponse(Status statusCode, T data, String message, String errorCode) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
        this.errorCode = errorCode;
        this.currentTime = new Date().getTime();
    }

    public Status getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Status statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getCurrentTime() {
        return this.currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }
}