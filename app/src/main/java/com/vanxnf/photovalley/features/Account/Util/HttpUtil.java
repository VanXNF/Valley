package com.vanxnf.photovalley.features.Account.Util;

import com.vanxnf.photovalley.features.Account.Entity.Account;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

    public static Call sendOkHttpRequest(String address, Account account, okhttp3.Callback callback) {

        OkHttpClient client  = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", account.getUserName())
                .addFormDataPart("password", account.getPassword())
                .build();

        Request request = new Request.Builder()
                .url("http://192.168.4.128:8080/api/" + address)
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

        return call;
    }
}
