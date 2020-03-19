package com.garden.kokoa;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Fragment4 extends Fragment {
    final String TAG = "WEATHER";
    String result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (View) inflater.inflate(R.layout.fragment4,container,false);

//        KWeatherHttpClient httpClient = new KWeatherHttpClient();
//        String data = httpClient.getWeatherJson();
//        Log.d(TAG, "Weather Data: "+data);

//        WeatherHttpClient client = new WeatherHttpClient();
//        String data = client.getWeatherData("2172797");
//        Log.d(TAG, "onCreateView: "+data);



        return rootView;
    }


}
