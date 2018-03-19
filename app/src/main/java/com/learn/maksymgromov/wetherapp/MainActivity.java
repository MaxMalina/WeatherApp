package com.learn.maksymgromov.wetherapp;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn.maksymgromov.wetherapp.data.Weather;
import com.learn.maksymgromov.wetherapp.data.remote.WeatherRepository;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.location) TextView mLocationView;
    @BindView(R.id.weatherIcon) ImageView mWeatherIconView;
    @BindView(R.id.weather) TextView mWeatherView;
    @BindView(R.id.humidity) TextView mHumidityView;
    @BindView(R.id.temperature) TextView mTemperatureView;

    private WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        weatherViewModel.getWeather("Kiev").observe(this, weather -> setView(weather));
    }

    private void setView(Weather weather) {
        mLocationView.setText(weather.getLocation());
        mHumidityView.setText(weather.getHumidity());
        mTemperatureView.setText(weather.getTemperature());
        mWeatherView.setText(weather.getDescription());

        Picasso.with(getApplicationContext()).load(weather.getImgLink()).into(mWeatherIconView);
        mWeatherIconView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }
}
