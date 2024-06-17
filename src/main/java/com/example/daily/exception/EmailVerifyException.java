package com.example.daily.exception;


import com.example.daily.enumeration.ErrorCode;

public class EmailVerifyException extends BaseException {
    public EmailVerifyException() {
    }

    public EmailVerifyException(String msg) {
        super("邮箱" + msg, ErrorCode.EMAIL_VERIFY_ERROR.getCode());
    }
}
