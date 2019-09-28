package com.hualala.sdkdemo.httpUtil;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hualala.sdkdemo.utils.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.http.impl.client.HttpClients.createDefault;

/**
 * 发送Http请求工具类, get post请求
 */
@Slf4j
public class HttpClientUtil {

    private static final Log logger = LogFactory.getLog(HttpClientUtil.class);
    private static final int DEFAULT_TIMEOUT = 60000;

    public static String senderPost(String url, Map<String, String> params, Long groupID, Long shopID, String traceID) throws IOException {

        CloseableHttpClient httpClient = createDefault();
        CloseableHttpResponse response = null;
        String result = "";

        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig
                    .custom()
                    .setSocketTimeout(DEFAULT_TIMEOUT)
                    .setConnectTimeout(DEFAULT_TIMEOUT)
                    .build();//设置请求和传输超时时间

            httpPost.setConfig(requestConfig);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            //将groupID与shopID放入header中传输，2.0接口header中参数不参与签名,traceID必传
            httpPost.setHeader("groupID", groupID.toString());
            httpPost.setHeader("shopID", shopID.toString());
            httpPost.setHeader("traceID", traceID);

            List<BasicNameValuePair> basicNameValuePairs = new ArrayList<BasicNameValuePair>();
            for (Map.Entry<String, String> entity : params.entrySet()) {
                basicNameValuePairs.add(new BasicNameValuePair(entity.getKey(), entity.getValue()));
            }

            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(basicNameValuePairs, Consts.UTF_8);
            httpPost.setEntity(urlEncodedFormEntity);
            // 获取当前时间戳
            Long now = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String StartTime = sdf.format(date);
            // 计算耗时
            long time = System.currentTimeMillis() - now;
            logger.info("调用三方平台耗时： " + time + "毫秒");
            // 格式化请求参数
            String requestParams = JSON.toJSONString(params);
            int httpStatus = statusLine.getStatusCode();
            logger.info(String.format("param url: %s, params: %s, response status: %s",
                    url, requestParams, httpStatus));

            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, Consts.UTF_8);
            logger.info(String.format("response data: %s", result));
            return result;
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error("close http client failed", e);
            }
        }

    }


    /**
     * <p>
     * post/get请求
     * </p>
     *
     * @param url         请求的URL
     * @param paramMap    请求的参数
     * @param requestType 请求类型
     * @return 返回的字符串
     */
    public static String httpClient(String url, Map<String, String> paramMap, String requestType) {
        if (StringUtils.isBlank(url)) {
            return "url不能为空!";
        }
        if (StringUtils.isBlank(requestType)) {
            return "请求类型不能为空!";
        }
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 参数
        StringBuilder params = new StringBuilder();
        if (paramMap != null) {
            log.info("请求参数:" + JSONObject.toJSONString(paramMap));
            Iterator<Map.Entry<String, String>> iterator = paramMap.entrySet().iterator();
            for (int i = 0; iterator.hasNext(); ++i) {
                Map.Entry<String, String> next = iterator.next();
                if (i == 0) {
                    params.append("?").append(next.getKey()).append("=").append(next.getValue());
                } else {
                    params.append("&").append(next.getKey()).append("=").append(next.getValue());
                }
            }
        }
        log.info(params.toString());
        // 响应模型
        CloseableHttpResponse response = null;
        url += params;
        try {
            if ("GET".equalsIgnoreCase(requestType)) {
                HttpGet httpGet = new HttpGet(url);
                response = httpClient.execute(httpGet);
            } else if ("POST".equalsIgnoreCase(requestType)) {
                // 创建Post请求
                HttpPost httpPost = new HttpPost(url);
                // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
                httpPost.setHeader("Content-Type", "application/json;charset=utf8");
                // 由客户端执行(发送)Post请求
                response = httpClient.execute(httpPost);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("请求类型:{},请求路径:{}", requestType, url);
        if (response == null) return "response空!";
        try {
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            StatusLine statusLine = response.getStatusLine();

            log.info("响应状态为:" + response.getStatusLine());
            log.info("响应内容长度为:" + responseEntity.getContentLength());
            if (statusLine.toString().contains(HttpStatus.SC_OK + "")) {
                log.info("请求成功!");
                String fromStatus = EntityUtils.toString(responseEntity);
                log.info("响应内容为:" + fromStatus);
                return fromStatus;
            } else {
                return response.getStatusLine().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }
        return "";
    }

    /**
     * <p>
     * post请求
     * </p>
     *
     * @param url    路径
     * @param object 实体对象
     * @return 请求的数据
     */
    public static String postHttpClientEntity(String url, Object object) {
        if (StringUtils.isBlank(url)) {
            return "url不能为空!";
        }
        if (object == null) {
            return "实体对象不能为空!";
        }
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);
        // 我这里利用阿里的fastjson，将Object转换为json字符串;
        // (需要导入com.alibaba.fastjson.JSON包)
        StringEntity entity = new StringEntity(JSON.toJSONString(object), "UTF-8");
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            StatusLine statusLine = response.getStatusLine();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (statusLine.toString().contains(HttpStatus.SC_OK + "")) {
                log.info("请求成功!");
                String fromStatus = EntityUtils.toString(responseEntity);
                log.info("响应内容为:" + fromStatus);
                return fromStatus;
            } else {
                return statusLine.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }
        return "";
    }

    /**
     * <p>
     * post请求  可以传入实体参数 或者自定义参数
     * </p>
     * <p>
     * httpType http类型  http/https
     * host     主机Ip
     * port     端口
     *
     * @param path     路径
     * @param object   请求对象
     * @param paramMap 请求的参数
     * @return 返回请求的对象
     */
    public static String postClientEntityParam(String path,
                                               Object object,
                                               Map<String, String> paramMap) {
       /* if (StringUtils.isBlank(httpType) && StringUtils.isBlank(host)  && StringUtils.isBlank(path)  && port == null) {
            return "请求的路径信息不能为空!";
        }*/
        String httpType = BaseUtils.getConfig().get("HTTP_TYPE").toString();

        String host = BaseUtils.getConfig().get("HOST").toString();
        int port = Integer.parseInt(BaseUtils.getConfig().get("PORT").toString());

        if (object == null) {
            return "实体对象不能为空!";
        }
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        // 参数
        URI uri = null;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<>();
            if (paramMap != null) {
                Iterator<Map.Entry<String, String>> iterator = paramMap.entrySet().iterator();
                for (; iterator.hasNext(); ) {
                    Map.Entry<String, String> next = iterator.next();
                    params.add(new BasicNameValuePair(next.getKey(), next.getValue()));
                }
            }
            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
            if (paramMap == null) {
                uri = new URIBuilder().setScheme(httpType).setHost(host).setPort(port)
                        .setPath(path).build();
            } else {
                uri = new URIBuilder().setScheme(httpType).setHost(host).setPort(port)
                        .setPath(path).setParameters(params).build();
            }
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        log.info("url:{}", uri);

        HttpPost httpPost = new HttpPost(uri);
        // HttpPost httpPost = new
        // HttpPost("http://127.0.0.1:12345/doPostControllerThree1");

        // 将user对象转换为json字符串，并放入entity中
        StringEntity entity = new StringEntity(JSON.toJSONString(object), "UTF-8");
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);

        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            StatusLine statusLine = response.getStatusLine();

            log.info("响应状态为:" + response.getStatusLine());
            log.info("响应内容长度为:" + responseEntity.getContentLength());
            if (statusLine.toString().contains(HttpStatus.SC_OK + "")) {
                log.info("请求成功!");
                String fromStatus = EntityUtils.toString(responseEntity);
                log.info("响应内容为:" + fromStatus);
                return fromStatus;
            } else {
                return response.getStatusLine().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            close(httpClient, response);
        }
    }

    /**
     * 关闭资源
     *
     * @param httpClient CloseableHttpClient
     * @param response   CloseableHttpResponse
     */
    private static void close(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        try {
            // 释放资源
            if (httpClient != null) {
                httpClient.close();
            }
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

