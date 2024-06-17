package com.example.daily.exception;

import com.example.daily.enumeration.ErrorCode;

public class SseException extends BaseException{
    public SseException() {
    }

    public SseException(String msg) {
        super(msg, ErrorCode.SSE_ERROR.getCode());
    }
}
