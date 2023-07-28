package com.crud.exception;


import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ExceptionHandlers {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public com.crud.responses.AppResponse handleNotFoundException(final NotFoundException ex) {
        return new com.crud.responses.AppResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
                ex.getMessage(), null, ex.getMessage());
    }



}
