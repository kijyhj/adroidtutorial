package com.example.a.a17_service2;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyService myService;
    boolean isBound = false;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            myService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MyService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void onBtnClick(View v){
        int num = myService.getRanDomNumber();

        Toast.makeText(MainActivity.this, "num : " + num, Toast.LENGTH_SHORT).show();

        MyIntentService.startActionFoo(this, "abc", "def");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isBound){
            unbindService(connection);
        }
    }
}
