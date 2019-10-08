package com.hualala.sdkdemo.model.data;

import com.hualala.sdkdemo.model.FoodCategoryModel;
import com.hualala.sdkdemo.model.ResultModel;
import lombok.Data;

import java.util.List;

/**
 * @author gaoh
 * @version 1.0
 * @date 2019/10/8 18:31
 */
@Data
public class FoodCategoryDataModel {
    private ResultModel result;
    private List<FoodCategoryModel> foodCategory;
}
