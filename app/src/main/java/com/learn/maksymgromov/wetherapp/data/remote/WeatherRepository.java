package com.learn.maksymgromov.wetherapp.data.remote;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.learn.maksymgromov.wetherapp.Utils;
import com.learn.maksymgromov.wetherapp.data.Weather;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

public class WeatherRepository {
    private static final String TAG = WeatherRepository.class.getSimpleName();

    private static final String API_LINK = "http://api.openweathermap.org/";
    private static final String API_KEY = "a0d9152d6aebb18c68b80377a2fe78ab";

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_LINK)
                    .build();
        }

        return retrofit;
    }

    public WeatherRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_LINK)
                .build();
    }

    public LiveData<Weather> getWeather(String city) {
        final MutableLiveData<Weather> weather = new MutableLiveData<>();
        Request request = new Request.Builder().url(API_LINK + "data/2.5/weather/" + "?q=" + city + "&appid=" + API_KEY).build();
        getRetrofitClient().callFactory().newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Weather w = Utils.convertWeatherFromJson(response.body().string());
                weather.postValue(w);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Unable to connect to server");
            }
        });
        return weather;
    }
}
