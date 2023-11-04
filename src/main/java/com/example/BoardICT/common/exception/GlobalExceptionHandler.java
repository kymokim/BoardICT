package com.example.BoardICT.common.exception;

import com.example.BoardICT.common.dto.ResponseMessage;
import com.example.BoardICT.common.exception.error.RegisterFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RegisterFailedException.class)
    protected ResponseEntity<ResponseMessage> handleRegisterFailedException(RegisterFailedException e) {
        ErrorCode code = ErrorCode.AUTHENTICATION_CONFLICT;

        ResponseMessage response = ResponseMessage.builder()
                .message(code.getMessage())
                .data(code.getCode())
                .build();
        return new ResponseEntity<>(response, code.getHttpStatus());
    }
}
