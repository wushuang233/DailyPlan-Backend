package com.example.daily.exception;

import com.example.daily.enumeration.ErrorCode;

public class PasswordInvalidException extends BaseException{
    public PasswordInvalidException() {
    }
    public PasswordInvalidException(String msg) {
        super(msg, ErrorCode.PASSWORD_INVALID.getCode());
    }
}
