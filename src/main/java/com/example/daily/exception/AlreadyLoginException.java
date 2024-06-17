package com.example.daily.exception;


import com.example.daily.enumeration.ErrorCode;

public class AlreadyLoginException extends BaseException{
    public AlreadyLoginException() {
    }

    public AlreadyLoginException(String msg) {
        super(msg, ErrorCode.LOGIN_TWICE.getCode());
    }
}
