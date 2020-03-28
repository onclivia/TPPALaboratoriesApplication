package com.tppa.listviewdemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SensorAdapter extends ArrayAdapter<Sensor> {
    public SensorManager sensorManager;

    public SensorAdapter(Context context, List<Sensor> sensors, SensorManager sm) {
        super(context, 0, sensors);
        sensorManager = sm;
    }



    public TextView sensorDetails = null;

    SensorEventListener sel = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            String text = "Values:\n";
            for(int i = 0;i < values.length; i++) {
                text = text + (i+1) + ": " + values[i]+"\n";
            }
            sensorDetails.setText(text);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position

        Sensor sensor = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sensor_item_in_listview, parent, false);
        }

        // Lookup view for data population
        TextView sensorName = (TextView) convertView.findViewById(R.id.sensorName);
        sensorDetails = (TextView) convertView.findViewById(R.id.sensorDetails);

        this.sensorManager.registerListener(sel,sensor,SensorManager.SENSOR_DELAY_NORMAL);

        // Populate the data into the template view using the data object
        sensorName.setText(sensor.getName());


        // Return the completed view to render on screen
        return convertView;
    }


}
