package com.hualala.sdkdemo.model;

import lombok.Data;

/**
 * @Author: GH
 * @Date: 2019/9/27 22:03
 * @Version 1.0
 * 请求参数
 */
@Data
public class BaseVo {
    private Long shopID;//店铺id
    private Long groupID;//集团id
    private Integer pageNo;//页码
    private Integer pageSize;//每页条数
}
