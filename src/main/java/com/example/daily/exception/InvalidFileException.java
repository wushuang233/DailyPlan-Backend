package com.example.daily.exception;


import com.example.daily.enumeration.ErrorCode;

/**
 * @author deepwind
 */
public class InvalidFileException extends BaseException{
    public InvalidFileException() {
    }

    public InvalidFileException(String msg) {
        super(msg, ErrorCode.INVALID_FILE_ERROR.getCode());
    }
}
