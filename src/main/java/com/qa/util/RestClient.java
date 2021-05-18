package com.qa.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    //1.Get请求方法
    public CloseableHttpResponse get(String url) throws IOException {
        //创建一个可以关闭的HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个HttpGet的请求对象
        HttpGet httpget = new HttpGet(url);
        //执行请求,相当于postman上的发送按钮,然后赋值给HttpResponse对象接收
        return httpClient.execute(httpget);
    }

    //2.Get方法(带请求头信息)
    public CloseableHttpResponse get(String url, HashMap<String, String> headermap) throws IOException {
        //创建一个可关闭的HttpClient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //创建一个HttpGet的请求对象
        HttpGet httpget = new HttpGet(url);
        //加载请求头到httpget对象
        for (Map.Entry<String, String> entry : headermap.entrySet()) {
            httpget.addHeader(entry.getKey(), entry.getValue());
        }
        //执行请求,相当于postman上点击发送按钮,然后赋值给HttpResonse对象接收
        return httpclient.execute(httpget);
    }
}