package com.example.a.a14_location;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Geocoder geocoder = new Geocoder(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);

        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        List<String> providers = manager.getAllProviders();

        String str = "";

        for(int i = 0 ; i < providers.size() ; i++){
            str += "provider : " + providers.get(i) + " state : " + manager.isProviderEnabled(providers.get(i)) + "\n";
        }

        textView.setText(str);

        Button btnOk = (Button) findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editAddress);
                String strAddress = editText.getText().toString();

                List<Address> list = null;
                try {
                    list = geocoder.getFromLocationName(strAddress, 10);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address address = list.get(0);
                textView.append(address.toString());
            }
        });



        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String str = " lat : " + location.getLatitude() + " lon : " + location.getLongitude();
                textView.append(str);
                try {
                    List<Address> list = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
                    Address address = list.get(0);
                    textView.append(address.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener );
    }
}
