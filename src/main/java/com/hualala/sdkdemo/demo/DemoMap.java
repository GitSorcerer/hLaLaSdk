package com.hualala.sdkdemo.demo;

import com.alibaba.fastjson.JSONObject;
import com.hualala.api.model.SignModel;
import com.hualala.api.utils.AESUtil;
import com.hualala.api.utils.SignUtil;
import com.hualala.sdkdemo.httpUtil.HttpClientUtil;
import com.hualala.sdkdemo.model.BaseVo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 2.0接口请求示例
 */

public class DemoMap {
    //开发者appsecret
    private static  final String APP_SECRET="12345678";
    //开发者appkey
    private static  final Long APP_KEY=1153L;
    //测试环境下单地址
    private static  final String ORDER_URL="https://dohko-open-api.hualala.com/doc/getAllShop";
    private static final Long GROUP_ID=1155L;
    private static final Long SHOP_ID=76068673L;

    public static void main(String[] args) {
        try {
            BaseVo baseVo=new BaseVo();
            baseVo.setGroupID(GROUP_ID);
            baseVo.setShopID(SHOP_ID);
            //参与签名字段集合
            Long timestamp=System.currentTimeMillis();
            Map<String,Object> signMap=new HashMap<String, Object>();
            signMap.put("timestamp",timestamp);
            signMap.put("version","2.0");
            signMap.put("appKey",APP_KEY);
            signMap.put("requestBody",baseVo);

            //调用SDK方法getMapSign，生成公共参数signature的值，generatorStr：签名字段拼接，generatorSig：签名加密结果
            SignModel  signModel= SignUtil.getMapSign(signMap,APP_SECRET);

            //签名拼接字符串
            System.out.println("generatorStr : "+signModel.getGeneratorStr());

            //签名加密生成字符串
            System.out.println("generatorSig : "+signModel.getGeneratorSig());

            //调用SDK方法将业务参数json进行AES加密，生成requestBody的值
            String requestBody= AESUtil.AESEncode(APP_SECRET,JSONObject.toJSONString(baseVo));

            //创建公共参数列表键值对map
            Map<String,String> params=new HashMap<String, String>();
            params.put("timestamp",timestamp.toString());
            params.put("version","2.0");
            params.put("appKey",APP_KEY.toString());
            params.put("requestBody",requestBody);
            params.put("signature",signModel.getGeneratorSig());

            //请求标识id
            String traceID=UUID.randomUUID().toString();
            //2.0接口groupID,shopID,traceID放入header中传输，groupID和traceID必传
            String response=HttpClientUtil.senderPost(ORDER_URL,params,GROUP_ID,SHOP_ID, traceID);
            System.out.println(response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
