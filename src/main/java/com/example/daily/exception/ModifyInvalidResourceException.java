package com.example.daily.exception;

import com.example.daily.enumeration.ErrorCode;

public class ModifyInvalidResourceException extends BaseException{
    public ModifyInvalidResourceException() {
    }

    public ModifyInvalidResourceException(String msg) {
        super(msg, ErrorCode.INVALID_RES_OPERATION.getCode());
    }
}
