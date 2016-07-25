package com.example.a.a04_widget2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "on Button  Click", Toast.LENGTH_SHORT).show();
            }
        });

        CheckBox myCheckbox = (CheckBox) findViewById(R.id.myCheckBox);
        myCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                Toast.makeText(MainActivity.this, "checked : " + b, Toast.LENGTH_SHORT).show();
            }
        });

        RadioGroup myGroup = (RadioGroup)findViewById(R.id.myRadioGroupup);
        myGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                switch (i){
                    case R.id.radio1:
                        Toast.makeText(MainActivity.this, "radio1 selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio2:
                        Toast.makeText(MainActivity.this, "radio2 selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio3:
                        Toast.makeText(MainActivity.this, "radio3 selected", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
