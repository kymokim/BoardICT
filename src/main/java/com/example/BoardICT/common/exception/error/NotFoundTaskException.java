package com.example.BoardICT.common.exception.error;

import com.example.BoardICT.common.exception.ErrorCode;

public class NotFoundTaskException extends RuntimeException{

    public NotFoundTaskException() { super(ErrorCode.NOT_FOUND_TASK.getMessage());}
}
