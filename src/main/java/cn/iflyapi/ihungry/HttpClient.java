package cn.iflyapi.ihungry;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author: qfwang
 * @date: 2018-11-04 9:59 PM
 */
public class HttpClient {
    private static final Logger logger = Logger.getLogger("http-ding");

    public static void postJSON(String url, JSONObject params, String contentType) throws IOException {

        //创建默认的httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建post请求对象
        HttpPost httpPost = new HttpPost(url);

        StringEntity entity = new StringEntity(params.toString(), "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType(contentType);

        //设置参数到请求对象中
        httpPost.setEntity(entity);

        //设置header信息
        //指定报文头【Content-type】"application/x-www-form-urlencoded"、【User-Agent】

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() != 200) {
            logger.warning("postJSON|通知钉钉消息失败！");
        }
        //关流
        response.close();

    }
}
