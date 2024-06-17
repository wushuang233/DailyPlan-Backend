package com.example.daily.exception;

import com.example.daily.enumeration.ErrorCode;

public class VerifyCodeInavalidException extends BaseException{
    public VerifyCodeInavalidException() {
    }

    public VerifyCodeInavalidException(String msg) {
        super("验证码" + msg, ErrorCode.VERIFY_CODE_INVALID_ERROR.getCode());
    }
}
