package com.example.daily.exception;

import com.example.daily.enumeration.ErrorCode;

public class AccountNotLoginException extends BaseException{
    public AccountNotLoginException() {
    }

    public AccountNotLoginException(String msg) {
        super(msg, ErrorCode.ACCOUNT_NOT_LOGIN.getCode());
    }
}
