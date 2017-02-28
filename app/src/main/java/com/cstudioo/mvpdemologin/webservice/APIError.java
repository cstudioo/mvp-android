package com.cstudioo.mvpdemologin.webservice;

/**
 * class used to check general error response
 */
public class APIError {

    private int status_code;
    private String message;


    public APIError() {
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
