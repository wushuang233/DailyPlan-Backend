package com.example.daily.exception;

import com.example.daily.enumeration.ErrorCode;

public class ResourceNotFoundException extends BaseException{
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String msg) {
        super(msg, ErrorCode.RESOURCE_NOT_FOUND.getCode());
    }
}
