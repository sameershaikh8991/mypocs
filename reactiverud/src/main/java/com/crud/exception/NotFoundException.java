package com.crud.exception;

public class NotFoundException  extends  Exception{

    public NotFoundException(String message, Exception cause) {
        super(message, cause);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
