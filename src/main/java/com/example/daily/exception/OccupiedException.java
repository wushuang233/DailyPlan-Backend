package com.example.daily.exception;


import com.example.daily.constant.MessageConstant;
import com.example.daily.enumeration.ErrorCode;

/**
 * @author deepwind
 */
public class OccupiedException extends BaseException{
    public OccupiedException() {
    }

    public OccupiedException(String msg) {
        super(msg +" " + MessageConstant.ALREADY_EXISTS, ErrorCode.OCCUPIED_ERROR.getCode());
    }

}
