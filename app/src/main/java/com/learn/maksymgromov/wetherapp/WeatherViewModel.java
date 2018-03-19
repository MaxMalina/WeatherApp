package com.learn.maksymgromov.wetherapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.learn.maksymgromov.wetherapp.data.Weather;
import com.learn.maksymgromov.wetherapp.data.remote.WeatherRepository;

public class WeatherViewModel extends ViewModel {
    private LiveData<Weather> weather;
    private WeatherRepository weatherRepository = new WeatherRepository();

    public LiveData<Weather> getWeather(String city) {
        if(weather == null){
            weather = weatherRepository.getWeather(city);
        }
        return weather;
    }
}
