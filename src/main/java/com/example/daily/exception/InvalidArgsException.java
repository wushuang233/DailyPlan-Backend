package com.example.daily.exception;


import com.example.daily.enumeration.ErrorCode;

public class InvalidArgsException extends BaseException{
    public InvalidArgsException() {
    }

    public InvalidArgsException(String msg) {
        super(msg, ErrorCode.FRONTEND_ERROR.getCode());
    }
}
