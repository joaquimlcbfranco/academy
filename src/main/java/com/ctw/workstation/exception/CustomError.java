package com.ctw.workstation.exception;

public class CustomError {
    private String message;

    public CustomError(String message) {
        this.message = message;
    }

    public String getError() {
        return message;
    }

    public void setError(String message) {
        this.message = message;
    }
}
