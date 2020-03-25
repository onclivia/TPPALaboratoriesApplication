package com.tppa.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class ContactActivity extends MainActivity implements OnMapReadyCallback {
    private MapView mapView;
    private GoogleMap gmap;


    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    Button sendBtn;
    EditText txtMessage;
    String phoneNo;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        TextView name = findViewById(R.id.name);
        name.setText("Elgiganten");

        TextView address = findViewById(R.id.address);
        address.setText("Åvænget 6, 7400 Herning, Danemarca");

        TextView phone = findViewById(R.id.phone);
        phone.setText("+40742673691");



        sendBtn = findViewById(R.id.btnSendSMS);
        phoneNo =  findViewById(R.id.phone).toString();
        txtMessage = findViewById(R.id.editSms);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
            }
        });

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.map_view);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        final EditText latitude = findViewById(R.id.edit_lat);
        final EditText longitude = findViewById(R.id.edit_long);

        Button button = findViewById(R.id.changeLatLongBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                changeMap(latitude.getText().toString(), longitude.getText().toString(), gmap);
            }
        });
    }

    protected void sendSMSMessage() {
        phoneNo = findViewById(R.id.phone).toString();
        message = txtMessage.getText().toString();

        try{
            SmsManager smgr = SmsManager.getDefault();
            smgr.sendTextMessage(phoneNo,null, message,null,null);
            Toast.makeText(this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeMap(String latitude, String longitude, GoogleMap googlemap){

        LatLng newPosition = new LatLng(Integer.parseInt(latitude), Integer.parseInt(longitude));
        googlemap.moveCamera(CameraUpdateFactory.newLatLng(newPosition));
        googlemap.addMarker(new MarkerOptions()
                .position(newPosition)
                .title("New"));
        googlemap.animateCamera(CameraUpdateFactory.newLatLng(newPosition));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }
    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setMinZoomPreference(15);
        LatLng shop = new LatLng(56.132977, 9.007910);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(shop));
        googleMap.addMarker(new MarkerOptions()
                .position(shop)
                .title("Shop"));
    }

}

