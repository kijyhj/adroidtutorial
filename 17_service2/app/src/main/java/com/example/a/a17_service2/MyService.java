package com.example.a.a17_service2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class MyService extends Service {
    public MyService() {
    }

    class MyBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    MyBinder binder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private Random random = new Random();

    public int getRanDomNumber(){
        return random.nextInt(100);
    }
}
