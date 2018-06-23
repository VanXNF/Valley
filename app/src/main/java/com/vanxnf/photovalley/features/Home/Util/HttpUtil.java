package com.vanxnf.photovalley.features.Home.Util;


import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

    public static Call sendGetRequest(String api, String token, okhttp3.Callback callback) {

        OkHttpClient client  = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .addHeader("Authorization", "token " + token)
                .url("http://192.168.4.128:8080/api/" + api)
                .get()
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

        return call;
    }

    public static Call sendPostRequest(String api, String token, String action, int messageId, okhttp3.Callback callback) {

        OkHttpClient client  = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3,TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("status", action)
                .addFormDataPart("id", String.valueOf(messageId))
                .build();

        Request request = new Request.Builder()
                .addHeader("Authorization", "token " + token)
                .url("http://192.168.4.128:8080/api/" + api)
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

        return call;
    }

    public static Call sendPostRequest(String api, String token, File file, String message, okhttp3.Callback callback) {

        OkHttpClient client  = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
                .addFormDataPart("message", message)
                .build();

        Request request = new Request.Builder()
                .addHeader("Authorization", "token " + token)
                .url("http://192.168.4.128:8080/api/" + api)
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

        return call;
    }
}
