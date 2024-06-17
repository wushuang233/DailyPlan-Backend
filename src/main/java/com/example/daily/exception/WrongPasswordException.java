package com.example.daily.exception;

import com.example.daily.enumeration.ErrorCode;

public class WrongPasswordException extends BaseException{
    public WrongPasswordException() {
    }

    public WrongPasswordException(String msg) {
        super(msg, ErrorCode.WRONG_PASSWORD.getCode());
    }
}
