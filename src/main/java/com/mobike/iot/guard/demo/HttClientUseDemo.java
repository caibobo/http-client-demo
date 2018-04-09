package com.mobike.iot.guard.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.message.BasicNameValuePair;

public class HttClientUseDemo extends HttpClientService {

    public static void main(String[] args) {

        new HttClientUseDemo().getConfCall();
    }

    public void getConfCall() {

        String url = "http://www.baidu.com";

        List<BasicNameValuePair> urlParams = new ArrayList<BasicNameValuePair>();
        urlParams.add(new BasicNameValuePair("appid", "2"));
        exeHttpReq(url, false, urlParams, null, new GetConfCall());
    }

    public void exeHttpReq(String baseUrl, boolean isPost,
                           List<BasicNameValuePair> urlParams,
                           List<BasicNameValuePair> postBody,
                           FutureCallback<HttpResponse> callback) {

        try {
            System.out.println("enter exeAsyncReq");
            exeAsyncReq(baseUrl, isPost, urlParams, postBody, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 被回调的对象，给异步的httpclient使用
     *
     * */
    class GetConfCall implements FutureCallback<HttpResponse> {

        /**
         * 请求完成后调用该函数
         */
        public void completed(HttpResponse response) {

            System.out.println(response.getStatusLine().getStatusCode());
            System.out.println(getHttpContent(response));

            HttpClientUtils.closeQuietly(response);

        }

        /**
         * 请求取消后调用该函数
         */
        public void cancelled() {

        }

        /**
         * 请求失败后调用该函数
         */
        public void failed(Exception e) {

        }

    }
}
