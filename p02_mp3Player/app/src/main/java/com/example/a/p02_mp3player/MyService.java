package com.example.a.p02_mp3player;

import android.app.Service;
import android.content.Intent;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    Geocoder geocoder = new Geocoder(this);

    public MyService() {
    }

    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        timer = new Timer();

        timer.schedule(new GpsWrite(), 2000, 2000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    class  GpsWrite extends TimerTask{

        @Override
        public void run() {
            LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Log.d("timere","timer :::::::: ");
        }
    }
}
