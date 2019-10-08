package com.hualala.sdkdemo.model.data;

import com.hualala.sdkdemo.model.FoodInfoModel;
import com.hualala.sdkdemo.model.PageHeaderModel;
import com.hualala.sdkdemo.model.ResultModel;
import com.hualala.sdkdemo.model.TagInfoModel;
import lombok.Data;

import java.util.List;

/**
 * @author gaoh
 * @version 1.0
 * @date 2019/10/8 19:28
 * 店铺菜品列表
 */
@Data
public class FoodListDataModel {
    private ResultModel result;
    private List<FoodInfoModel> foodList;
    private List<TagInfoModel> foodTags;
    private PageHeaderModel pageHeader;
    private String EBookVersionNo;

}
