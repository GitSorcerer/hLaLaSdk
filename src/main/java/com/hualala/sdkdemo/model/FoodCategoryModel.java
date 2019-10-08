package com.hualala.sdkdemo.model;

import lombok.Data;

/**
 * @author gaoh
 * @version 1.0
 * @date 2019/10/8 17:18
 */
@Data
public class FoodCategoryModel {
    private String actionTime;//修改时间
    private String foodSubjectName;//收入名称
    private String sortIndex;//分类显示排序值
    private String isSingleSale;//是/否可单独销售 0：不单独销售（默认） 1：可单独销售
    private String groupID;//集团ID
    private String foodCategoryCode;//分类编号
    private String isActive;//是/否启用
    private String type;//
    private String adsID;//对应介绍页面ID
    private String foodCategoryName;//分类名称
    private String isBatching;//是/否配料分类 0：不是（默认） 1：是
    private String timeActiveTag;//时段有效性标记 pow(2,2)+pow(2,4)午餐与晚餐开放 pow(2,1)+..pow(2,5)=62 表示全部时段开放
    private String foodKey;//菜品Key
    private String foodCount;//菜品条目数
    private String foodCategoryID;//分类Id
    private String foodCategoryGroupName;//分类分组名称
    private String createTime;//
    private String action;//记录状态
    private String foodSubjectKey;//所属收入科目Key
    private String sourceFoodCategoryID;//源分类Id
    private String shopID;//店铺ID
    private String settlementProportion;//结算系数(0.0-1.0)
    private String foodCategoryKey;//分类Key

}
