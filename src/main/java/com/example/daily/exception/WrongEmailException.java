package com.example.daily.exception;

import com.example.daily.enumeration.ErrorCode;

public class WrongEmailException extends BaseException{
    public WrongEmailException() {
    }

    public WrongEmailException(String msg) {
        super(msg, ErrorCode.WRONG_ORIGIN_EMAIL.getCode());
    }
}
