package com.example.daily.exception;


import com.example.daily.enumeration.ErrorCode;

public class AccountNotExistsException extends BaseException{
    public AccountNotExistsException() {
    }

    public AccountNotExistsException(String msg) {
        super(msg, ErrorCode.ACCOUNT_NOT_EXISTS_ERROR.getCode());
    }
}
