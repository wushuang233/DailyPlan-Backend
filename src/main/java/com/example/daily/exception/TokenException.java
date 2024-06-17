package com.example.daily.exception;

import com.example.daily.enumeration.ErrorCode;

public class TokenException extends BaseException{
    public TokenException() {
    }

    public TokenException(String msg) {
        super(msg, ErrorCode.INVALID_TOKEN.getCode());
    }
}
