package com.example.a.a13_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestDbHandler dbHandler = new TestDbHandler(this);
        dbHandler.insert("홍길동", 10, "서울");
        dbHandler.insert("김길동", 20, "인천");
        dbHandler.insert("abcd", 30, "부산");

        dbHandler.delete("abcd");
        dbHandler.update("김길동", 40);

        dbHandler.selectAll();
    }
}
