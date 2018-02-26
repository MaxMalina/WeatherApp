package com.learn.maksymgromov.wetherapp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

public class RequestClient {
    private static final String TAG = RequestClient.class.getSimpleName();

    private static final String API_LINK = "http://api.openweathermap.org/";
    private static final String API_KEY = "a0d9152d6aebb18c68b80377a2fe78ab";

    private Retrofit retrofit;

    public RequestClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_LINK)
                .build();
    }

    public String getRequest(String city) {
        Request request = new Request.Builder().url(API_LINK + "data/2.5/weather/" + "?q=" + city + "&appid=" + API_KEY).build();
        try {
            Response response = retrofit.callFactory().newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            Log.e(TAG, "Unable connect to server");
        }
        return "";
    }
}
