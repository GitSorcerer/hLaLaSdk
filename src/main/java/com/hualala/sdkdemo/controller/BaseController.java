package com.hualala.sdkdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hualala.api.model.SignModel;
import com.hualala.api.utils.AESUtil;
import com.hualala.api.utils.SignUtil;
import com.hualala.sdkdemo.httpUtil.HttpClientUtil;
import com.hualala.sdkdemo.model.BaseVo;
import com.hualala.sdkdemo.model.PaperModel;
import com.hualala.sdkdemo.service.BaseService;
import com.hualala.sdkdemo.utils.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author: GH
 * @Date: 2019/9/27 22:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/base")
@Slf4j
public class BaseController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BaseService baseService;

    //开发者appsecret
    private static final String APP_SECRET = "UmvgvQWv";
    //开发者appkey
    private static final Long APP_KEY = 1694L;
    //测试环境下单地址
    private static final String ORDER_URL = "https://dohko-open-api.hualala.com/doc/getAllShop";
    private static final Long GROUP_ID = 1155L;
    private static final Long SHOP_ID = 76068673L;

    @RequestMapping(value = "/main")
    public String test() {
        String response = "";
        try {
            BaseVo baseVo = new BaseVo();
            baseVo.setGroupID(GROUP_ID);
            baseVo.setShopID(SHOP_ID);
            //参与签名字段集合
            Long timestamp = System.currentTimeMillis();
            Map<String, Object> signMap = new HashMap<String, Object>();
            signMap.put("timestamp", timestamp);
            signMap.put("version", "2.0");
            signMap.put("appKey", APP_KEY);
            signMap.put("requestBody", baseVo);

            //调用SDK方法getMapSign，生成公共参数signature的值，generatorStr：签名字段拼接，generatorSig：签名加密结果
            SignModel signModel = SignUtil.getMapSign(signMap, APP_SECRET);

            //签名拼接字符串
            System.out.println("generatorStr : " + signModel.getGeneratorStr());

            //签名加密生成字符串
            System.out.println("generatorSig : " + signModel.getGeneratorSig());

            //调用SDK方法将业务参数json进行AES加密，生成requestBody的值
            String requestBody = AESUtil.AESEncode(APP_SECRET, JSONObject.toJSONString(baseVo));

            //创建公共参数列表键值对map
            Map<String, String> params = new HashMap<String, String>();
            params.put("timestamp", timestamp.toString());
            params.put("version", "2.0");
            params.put("appKey", APP_KEY.toString());
            params.put("requestBody", requestBody);
            params.put("signature", signModel.getGeneratorSig());

            //请求标识id
            String traceID = UUID.randomUUID().toString();
            //2.0接口groupID,shopID,traceID放入header中传输，groupID和traceID必传
            response = HttpClientUtil.senderPost(ORDER_URL, params, GROUP_ID, SHOP_ID, traceID);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


    @RequestMapping(value = "/rest")
    public String rest(String request) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded;charset=utf-8");
        headers.setContentType(type);
        headers.add("groupID", GROUP_ID.toString());
        headers.add("shopID", SHOP_ID.toString());
        headers.add("traceID", UUID.randomUUID().toString());

       /* httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        //将groupID与shopID放入header中传输，2.0接口header中参数不参与签名,traceID必传
        httpPost.setHeader("groupID", groupID.toString());
        httpPost.setHeader("shopID", shopID.toString());
        httpPost.setHeader("traceID", traceID);*/

        String param = "";
//        JSONObject jsonObj = JSONObject.toJSONString();

        HttpEntity<String> formEntity = new HttpEntity<>(param, headers);

        String result = restTemplate.postForObject(ORDER_URL, formEntity, String.class);


        log.debug("result:{}", result);


        assert result != null;
        return result;
    }

    /*
    * HttpHeaders headers = new HttpHeaders();
MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
headers.setContentType(type);
headers.add("Accept", MediaType.APPLICATION_JSON.toString());

JSONObject jsonObj = JSONObject.fromObject(params);

HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);

String result = restTemplate.postForObject(url, formEntity, String.class);
    *
    *
    * */


    @RequestMapping(value = "/restParam")
    public String restParam(String request) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded;charset=utf-8");
        headers.setContentType(type);
        headers.add("groupID", GROUP_ID.toString());
        headers.add("shopID", SHOP_ID.toString());
        headers.add("traceID", UUID.randomUUID().toString());


        Map<String, String> param = baseService.getParam(APP_SECRET, GROUP_ID, SHOP_ID, APP_KEY);

        HttpEntity<String> formEntity = new HttpEntity<>(JSONObject.toJSONString(param), headers);

        List<BasicNameValuePair> basicNameValuePairs = new ArrayList<>();
        for (Map.Entry<String, String> entity : param.entrySet()) {
            basicNameValuePairs.add(new BasicNameValuePair(entity.getKey(), entity.getValue()));
        }

        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(basicNameValuePairs, Consts.UTF_8);


        String result = restTemplate.postForObject(ORDER_URL, formEntity, String.class);
//        String result = restTemplate.postForObject(ORDER_URL, urlEncodedFormEntity, String.class);
        log.info(result);

        return result;
    }


    @RequestMapping(value = "/map")
    public Map<String, Object> mapTest(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>(6);
        try {
            resultMap.put("data", new PaperModel());
            PaperModel paperModel = new PaperModel();
            paperModel.setFileName("文件");
            paperModel.setCreateDate(new Date());
            resultMap.put("paper", JSONObject.toJSONString(paperModel));
            Map<String, String> map = JSONObject.parseObject(JSONObject.toJSONString(paperModel), new TypeReference<Map<String, String>>() {

            });
            resultMap.put("paperMap", map);
        } catch (Exception e) {
            resultMap.put("msg", BaseUtils.loggerError(e));
        }
        return resultMap;
    }


}
