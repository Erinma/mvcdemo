package com.example.monthpractice1.utils;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mamiaomiao on 2018/2/27.
 */

public class OkhttpUtil {
    private static OkhttpUtil util;

    private OkhttpUtil() {
    }

    public OkhttpUtil getInstance() {
        if (util == null) {
            synchronized (OkhttpUtil.class) {
                if (util == null) {
                    util = new OkhttpUtil();
                }
            }
        }
        return util;
    }

    private static OkHttpClient okHttpClient;

    private static OkHttpClient getOkHttpClient() {

        if (okHttpClient == null) {
            File sdcache = new File(Environment.getExternalStorageDirectory(), "cache");
            int cacheSize = 10 * 1024 * 1024;
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            Request builder = request.newBuilder().addHeader("source", "android").build();
                            Response response = chain.proceed(builder);
                            return response;
                        }
                    })
                    .connectTimeout(15, TimeUnit.SECONDS)
                    //.cache(new Cache(sdcache, cacheSize))
                    .build();
        }
        return okHttpClient;
    }

    /**
     * get请求
     * 参数1 url
     * 参数2 回调Callback
     */

    public static void doGet(String url, Callback callback) {

        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getOkHttpClient();
        //创建Request
        Request request = new Request.Builder().url(url).build();
        //得到Call对象
        Call call = okHttpClient.newCall(request);
        //执行异步请求
        call.enqueue(callback);


    }

    /**
     * post请求
     * 参数1 url
     * 参数2 回调Callback
     */

    public static void doPost(String url, Map<String, String> params, Callback callback) {

        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getOkHttpClient();
        //3.x版本post请求换成FormBody 封装键值对参数
        FormBody.Builder builder = new FormBody.Builder();
        //遍历集合
        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
        //创建Request
        Request request = new Request.Builder().url(url).post(builder.build()).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);

    }

}
