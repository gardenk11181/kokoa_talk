package com.garden.kokoa;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Fragment4 extends Fragment {
    final String TAG = "WEATHER";
    String result;
    Handler handler = new Handler();
    TextView kCelcius;
    TextView kLocation;
    TextView kMaxCelcius;
    TextView kMinCelcius;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = (View) inflater.inflate(R.layout.fragment4,container,false);

        kCelcius = rootView.findViewById(R.id.kCelsius);
        kLocation = rootView.findViewById(R.id.kLocation);
        kMaxCelcius = rootView.findViewById(R.id.kMaxCelcius);
        kMinCelcius = rootView.findViewById(R.id.kMinCelcius);

        KWeatherCallThread thread = new KWeatherCallThread();
        thread.start();


        return rootView;
    }

    public class KWeatherCallThread extends Thread {
        private String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?id=";
        private String IMG_URL = "http://openweathermap.org/img/w/";
        private String APPID = "28f7e99b02cd5af43ef56fd473940a0d";
        public void run() {
            HttpURLConnection con = null ;
            InputStream is = null;
            final String result;

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
                Log.d(TAG, "data: "+buffer.toString());
                result = buffer.toString();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        parseJSON(result);

                    }
                });

            }
            catch(Exception e) {
                Log.d("NetWorkThread", "run: "+e.getMessage());
            }
            finally {
                try { is.close(); } catch(Throwable t) {}
                try { con.disconnect(); } catch(Throwable t) {}
            }
        }

        void parseJSON(String data) {
            try {
                JSONObject obj = new JSONObject(data);
                String weather = obj.getJSONArray("weather").getJSONObject(0).getString("main");
                String city = obj.getString("name");
                JSONObject temps = obj.getJSONObject("main");

                int temp = Integer.parseInt(temps.getString("temp").substring(0,3))-273;
                int tempMax = Integer.parseInt(temps.getString("temp_max").substring(0,3))-273;
                int tempMin = Integer.parseInt(temps.getString("temp_min").substring(0,3))-273;


                kCelcius.setText((String.valueOf(temp)+(char)176+"C"));
                kMaxCelcius.setText(("최고온도: "+tempMax+(char)176+"C"));
                kMinCelcius.setText(("최저온도: "+tempMin+(char)176+"C"));

            } catch(Exception e) {
                Log.d(TAG, e.getMessage());
            }

        }
    }


}
