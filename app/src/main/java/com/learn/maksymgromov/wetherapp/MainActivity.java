package com.learn.maksymgromov.wetherapp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        new RequestTask().execute("Kiev");
    }

    private class RequestTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return new RequestClient().getRequest(strings[0]);
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String response) {
            try {
                JSONObject jsonResponse = new JSONObject(response);

                mLocationView.setText(jsonResponse.getString("name") + ", " + jsonResponse.getJSONObject("sys").getString("country"));

                JSONObject jsonWeather = jsonResponse.getJSONArray("weather").getJSONObject(0);
                JSONObject jsonMain = jsonResponse.getJSONObject("main");

                Picasso.with(getApplicationContext()).load("http://openweathermap.org/img/w/" + jsonWeather.getString("icon") + ".png").into(mWeatherIconView);
                mWeatherIconView.setScaleType(ImageView.ScaleType.FIT_CENTER);

                mWeatherView.setText(jsonWeather.getString("description"));
                mHumidityView.setText(jsonMain.getString("humidity") + "%");
                mTemperatureView.setText(jsonMain.getString("temp")+ " K");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
