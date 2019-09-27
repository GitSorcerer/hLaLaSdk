package com.hualala.sdkdemo.model;

import lombok.Data;

/**
 * @Author: GH
 * @Date: 2019/9/27 22:03
 * @Version 1.0
 * 请求的商品Id 和组Id
 */
@Data
public class BaseVo {
    private Long shopID;
    private Long groupID;
}
