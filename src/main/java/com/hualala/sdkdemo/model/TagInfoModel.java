package com.hualala.sdkdemo.model;

import lombok.Data;

/**
 * @author gaoh
 * @version 1.0
 * @date 2019/10/8 19:47
 * 字段说明
 */
@Data
public class TagInfoModel {
    private String itemID;//配菜分组id
    private String tagDesc;//配菜分组描述
    private String actionTime;//修改时间
    private String isActive;//是否启用
    private String tagType;//分组类型
    private String groupID;//集团id
    private String shopID;//店铺id
    private String tagName;//分组名称
    private String createTime;//创建时间
    private String foodCount;//菜品数量
    private String foodIDs;//关联菜品id，逗号分隔
}
