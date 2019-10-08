package com.hualala.sdkdemo.model;

import lombok.Data;

/**
 * @author gaoh
 * @version 1.0
 * @date 2019/10/8 19:39
 * 分頁参数信息
 */
@Data
public class PageHeaderModel {
    private Integer pageCount;
    private Integer totalSize;
    private Integer pageNo;
    private Integer pageSize;
}
