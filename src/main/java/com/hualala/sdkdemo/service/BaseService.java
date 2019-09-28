package com.hualala.sdkdemo.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.api.model.SignModel;
import com.hualala.api.utils.AESUtil;
import com.hualala.api.utils.SignUtil;
import com.hualala.sdkdemo.model.BaseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaoh
 * @version 1.0
 * @date 2019/9/28 13:41
 */
@Service
@Slf4j
public class BaseService {

    //开发者appsecret
    private static final String APP_SECRET = "UmvgvQWv";
    //开发者appkey
    private static final Long APP_KEY = 1694L;
    //测试环境下单地址
    private static final Long GROUP_ID = 1155L;
    private static final Long SHOP_ID = 76068673L;


    /**
     * 获取参数
     *
     * @param APP_SECRET
     * @param longParam  [0]GROUP_ID  [1]  SHOP_ID [2]APP_KEY
     * @return
     */
    public Map<String, String> getParam(String APP_SECRET, Long... longParam) {
        BaseVo baseVo = new BaseVo();
        baseVo.setGroupID(longParam[0]);
        baseVo.setShopID(longParam[1]);
        //参与签名字段集合
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> signMap = new HashMap<String, Object>();
        signMap.put("timestamp", timestamp);
        signMap.put("version", "2.0");
        signMap.put("appKey", longParam[2]);
        signMap.put("requestBody", baseVo);

        //调用SDK方法getMapSign，生成公共参数signature的值，generatorStr：签名字段拼接，generatorSig：签名加密结果
        SignModel signModel = null;
        try {
            signModel = SignUtil.getMapSign(signMap, APP_SECRET);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //签名拼接字符串
        assert signModel != null;
        System.out.println("generatorStr : " + signModel.getGeneratorStr());

        //签名加密生成字符串
        System.out.println("generatorSig : " + signModel.getGeneratorSig());

        //调用SDK方法将业务参数json进行AES加密，生成requestBody的值
        String requestBody = null;
        try {
            requestBody = AESUtil.AESEncode(APP_SECRET, JSONObject.toJSONString(baseVo));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //创建公共参数列表键值对map
        Map<String, String> params = new HashMap<String, String>();
        params.put("timestamp", timestamp.toString());
        params.put("version", "2.0");
        params.put("appKey", longParam[2].toString());
        params.put("requestBody", requestBody);
        params.put("signature", signModel.getGeneratorSig());

        return params;
    }

}
