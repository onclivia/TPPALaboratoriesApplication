package com.tppa.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CheckSensorsActivity extends MainActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_sensors);
        // Attach the adapter to a ListView
        android.widget.ListView list =  findViewById(R.id.sensorsListView);

        SensorManager sensorManager = (SensorManager) this.getSystemService(this.SENSOR_SERVICE);
        List<Sensor> sensorsList = sensorManager.getSensorList(Sensor.TYPE_ALL);


        // Create the adapter to convert the array to views
        SensorAdapter adapter = new SensorAdapter(this, sensorsList, sensorManager);

        list.setAdapter(adapter);


    }
}
