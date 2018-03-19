package com.learn.maksymgromov.wetherapp;

import android.widget.ImageView;

import com.learn.maksymgromov.wetherapp.data.Weather;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
    public static Weather convertWeatherFromJson(String jsonText) {
        Weather weather = new Weather();
        try {
            JSONObject json = new JSONObject(jsonText);
            weather.setLocation(json.getString("name") + ", " + json.getJSONObject("sys").getString("country"));

            JSONObject jsonWeather = json.getJSONArray("weather").getJSONObject(0);
            JSONObject jsonMain = json.getJSONObject("main");

            weather.setPicture("http://openweathermap.org/img/w/" + jsonWeather.getString("icon") + ".png");

            weather.setDescription(jsonWeather.getString("description"));
            weather.setHumidity(jsonMain.getString("humidity") + "%");
            weather.setTemperature(jsonMain.getString("temp")+ " K");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weather;
    }
}
