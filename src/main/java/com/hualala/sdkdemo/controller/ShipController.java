package com.hualala.sdkdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hualala.sdkdemo.httpUtil.HttpClientUtil;
import com.hualala.sdkdemo.model.BaseVo;
import com.hualala.sdkdemo.model.data.FoodCategoryDataModel;
import com.hualala.sdkdemo.model.ResponseModel;
import com.hualala.sdkdemo.model.data.FoodListDataModel;
import com.hualala.sdkdemo.utils.BaseUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * @author gaoh
 * @version 1.0
 * @date 2019/10/8 18:53
 */
@RestController
public class ShipController {
    private static final Long SHOP_ID = 76528942L;

    /**
     * 查询店铺菜品分类
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getFoodClassCategory")
    public ResponseModel getFoodClassCategory(HttpServletRequest request) {
        String encapsulation = "";
        Map<String, Object> resultMap = new HashMap<>(6);
        try {
            BaseVo baseVo = new BaseVo();
            baseVo.setShopID(SHOP_ID);


            encapsulation = HttpClientUtil.parameterEncapsulation(baseVo, "/doc/getFoodClassCategory");

            resultMap.put("data", null);
        } catch (Exception e) {
            resultMap.put("msg", BaseUtils.loggerError(e));
        }


        return JSONObject.parseObject(encapsulation, new TypeReference<ResponseModel<FoodCategoryDataModel>>() {
        });
    }

    /**
     * 查询店铺菜品列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getOpenFood")
    public ResponseModel getOpenFood(HttpServletRequest request) {
        String encapsulation = "";
        Map<String, Object> resultMap = new HashMap<>(6);
        try {
            BaseVo baseVo = new BaseVo();
            baseVo.setShopID(SHOP_ID);


            encapsulation = HttpClientUtil.parameterEncapsulation(baseVo, "/doc/getOpenFood");

            resultMap.put("data", null);
        } catch (Exception e) {
            resultMap.put("msg", BaseUtils.loggerError(e));
        }


        return JSONObject.parseObject(encapsulation, new TypeReference<ResponseModel<FoodListDataModel>>() {
        });
    }

    /**
     * 查询集团菜品列表
     *
     * @return
     */
    @RequestMapping(value = "/queryGroupFoodSubInfoList")
    public String queryGroupFoodSubInfoList() {
        String encapsulation = "";
        Map<String, Object> resultMap = new HashMap<>(6);
        try {
            BaseVo baseVo = new BaseVo();

            baseVo.setPageNo(0);
            baseVo.setPageSize(10);
            encapsulation = HttpClientUtil.parameterEncapsulation(baseVo, "/doc/queryGroupFoodSubInfoList");

            resultMap.put("data", null);
        } catch (Exception e) {
            resultMap.put("msg", BaseUtils.loggerError(e));
        }

        return encapsulation;
    }

    /**
     * 查询店铺可用菜品库存数量
     *
     * @return
     */
    @RequestMapping(value = "/getAvailableFoodRemainQtyByShopID")
    public String getAvailableFoodRemainQtyByShopID() {
        String encapsulation = "";
        Map<String, Object> resultMap = new HashMap<>(6);
        try {
            BaseVo baseVo = new BaseVo();
            baseVo.setShopID(SHOP_ID);

            encapsulation = HttpClientUtil.parameterEncapsulation(baseVo, "/inventory/getAvailableFoodRemainQtyByShopID");

            resultMap.put("data", null);
        } catch (Exception e) {
            resultMap.put("msg", BaseUtils.loggerError(e));
        }

        return encapsulation;
    }

}
