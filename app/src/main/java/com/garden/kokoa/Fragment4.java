package com.garden.kokoa;

import android.os.Bundle;
import android.os.Handler;
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
        Handler handler = new Handler();

        KBackgroundThread thread = new KBackgroundThread();
        thread.start();


        return rootView;
    }

    class KBackgroundThread extends Thread {
        private String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?id=";
        private String IMG_URL = "http://openweathermap.org/img/w/";
        private String APPID = "28f7e99b02cd5af43ef56fd473940a0d";
        public void run() {
            HttpURLConnection con = null ;
            InputStream is = null;

            try {
                con = (HttpURLConnection) ( new URL(BASE_URL + "2172797" + "&appid="+APPID)).openConnection();
                Log.d("NetWorkThread", "run: "+BASE_URL + "2172797" + "&appid="+APPID);
                con.setRequestMethod("GET");
                con.setDoInput(true);
                con.setDoOutput(true);
                con.connect();

                // Let's read the response
                StringBuffer buffer = new StringBuffer();
                is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = null;
                while (  (line = br.readLine()) != null )
                    buffer.append(line + "\r\n");

                is.close();
                con.disconnect();
                Log.d("NetWorkThread", "run: "+buffer.toString());
            }
            catch(Exception e) {
                Log.d("NetWorkThread", "run: "+e.getMessage());
            }
            finally {
                try { is.close(); } catch(Throwable t) {}
                try { con.disconnect(); } catch(Throwable t) {}
            }
        }



    }




}
