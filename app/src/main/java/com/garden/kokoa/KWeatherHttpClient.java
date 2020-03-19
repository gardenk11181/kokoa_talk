package com.garden.kokoa;


import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class KWeatherHttpClient {
    public final String TAG = "Client";
    // request 받아올 url
    private String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?id=";
    private String IMG_URL = "http://openweathermap.org/img/w/";
    private String APPID = "28f7e99b02cd5af43ef56fd473940a0d";
    private String CITYID = "2172797";
    InputStream is;
    HttpURLConnection urlConnection;

    public String getWeatherJson() {

        // unhandled exception을 위한 try-catch구문이 필수적
        try {
            String str = BASE_URL+CITYID+"&appid="+ APPID;
            URL url = new URL(str);
            urlConnection = (HttpURLConnection) url.openConnection();

            StringBuffer buffer = new StringBuffer();
            is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            urlConnection.disconnect();
            return buffer.toString();


        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "getWeatherJson: "+e.getMessage());
        } finally {
            try { is.close(); } catch(Throwable t) {}
            try { urlConnection.disconnect(); } catch(Throwable t) {}
            Log.d(TAG, "getWeatherJson: called finally");
        }

        return null;



    }
}
