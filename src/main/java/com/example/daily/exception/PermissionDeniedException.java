package com.example.daily.exception;

import com.example.daily.enumeration.ErrorCode;

public class PermissionDeniedException extends BaseException{
    public PermissionDeniedException() {
    }

    public PermissionDeniedException(String msg) {
        super(msg, ErrorCode.PERMISSION_DENIED.getCode());
    }
}
