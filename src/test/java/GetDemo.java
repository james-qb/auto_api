import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.util.JsonUtil;
import com.qa.util.RestClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;


public class GetDemo extends TestBase {
    TestBase testBase;
    String host;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    final static Logger Log = LoggerFactory.getLogger(GetDemo.class);

    @BeforeClass
    public void setup() {
        testBase = new TestBase();
        host = prop.getProperty("GETPOST");
        url = host + "api/users";
    }

    //get方法带请求头
    @Test(description = "get请求成功")
    public void getAPITest() throws IOException {
        Log.info("开始执行用例...");
        restClient = new RestClient();
        restClient.get(url);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Content-Type", "application/json");
        closeableHttpResponse = restClient.get(url, hashMap);
        //断言状态码
        Log.info("测试响应状态码是否是200");
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Log.info("测试状态码" + statusCode);
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_200, "状态码不是200");
        //把响应内容存储在字符串对象
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        //System.out.println(responseString);
        Log.info("执行JSON解析，解析的内容是 " + responseString);
        JSONObject responseJson = JSON.parseObject(responseString);
        //json内容解析
        String s = JsonUtil.getValueByJPath(responseJson, "data[0]/first_name");
        //断言第一个名字是否是"George"
        Log.info("接口内容响应断言");
        Assert.assertEquals(s, "George", "fiset_name is not George");
        Log.info("用例执行结束...");
    }

}