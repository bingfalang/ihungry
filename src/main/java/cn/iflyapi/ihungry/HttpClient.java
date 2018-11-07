package cn.iflyapi.ihungry;

import cn.iflyapi.ihungry.util.Constant;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

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

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() != 200) {
            logger.warning("postJSON|通知钉钉消息失败！");
        }
        //关流
        response.close();

    }

    /**
     * 返回数据：工作日对应结果为 0, 休息日对应结果为 1, 节假日对应的结果为 2
     * @param url
     * @return
     * @throws IOException
     */
    public static boolean isWorkDay(String url) throws IOException {

        //创建默认的httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() != 200) {
            logger.warning("isWorkDay|判断节假日失败！");
        }
        JSONObject object = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
        int result = (int)object.get("data");
        //关流
        response.close();
        return result == 0;

    }

}
