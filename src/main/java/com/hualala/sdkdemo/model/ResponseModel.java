package com.hualala.sdkdemo.model;

import lombok.Data;

/**
 * @author gaoh
 * @version 1.0
 * @date 2019/10/8 17:21
 */
@Data
public class ResponseModel<T> {
    private String code;
    private T data;
    private String traceID;
    private String message;
}
