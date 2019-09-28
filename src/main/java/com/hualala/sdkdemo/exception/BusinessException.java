package com.hualala.sdkdemo.exception;


import com.hualala.sdkdemo.utils.BaseUtils;

/**
 * 业务异常基类
 */
public class BusinessException extends RuntimeException {


    private static final long serialVersionUID = 5333594641625781010L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable e){
        super(message, e);
    }

    public BusinessException(Throwable e){
        BaseUtils.loggerError(e);
    }
}