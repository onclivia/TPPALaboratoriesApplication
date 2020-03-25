package com.tppa.listviewdemo;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SensorAdapter extends ArrayAdapter<Sensor> {
    public SensorAdapter(Context context, List<Sensor> sensors) {
        super(context, 0, sensors);
    }

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
        TextView sensorDetails = (TextView) convertView.findViewById(R.id.sensorDetails);

        // Populate the data into the template view using the data object
        sensorName.setText(sensor.getName());
        sensorDetails.setText(sensor.getVendor());

        // Return the completed view to render on screen
        return convertView;
    }


}
