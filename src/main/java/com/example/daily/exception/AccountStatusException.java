package com.example.daily.exception;


import com.example.daily.enumeration.ErrorCode;

public class AccountStatusException extends BaseException{
    public AccountStatusException() {
    }

    public AccountStatusException(ErrorCode errorCode, String msg) {
        super(msg, errorCode.getCode());
    }
}
