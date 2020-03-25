package com.tppa.listviewdemo;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {


    Toast toast;
    final Context context = this;


    String products[] = {"Samsung S7", "Samsung S8", "Samsung S8+", "Samsung S9", "Samsung S9+", "Samsung S10"};
    int descriptionImages[] = {R.drawable.s7, R.drawable.s8, R.drawable.s8plus, R.drawable.s9, R.drawable.s9plus, R.drawable.s10};
    String details[] = {"Telefon mobil Samsung Galaxy S7, 32GB, 4G, Silver ", "Telefon mobil Samsung Galaxy S8, 64GB, 4G, Arctic ", "Telefon mobil Samsung Galaxy S8 Plus, 64GB, 4G", "Telefon mobil Samsung Galaxy S9, Dual SIM, 64GB", "Telefon mobil Samsung Galaxy S9 Plus, Dual SIM ", "Telefon mobil Samsung Galaxy S10, Dual SIM, 128GB"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toast.makeText(context, "OnCreateCalled",Toast.LENGTH_LONG).show();


        ListView simpleList = (ListView) findViewById(R.id.simpleListView);
        final CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), products, descriptionImages,details);
        simpleList.setAdapter(customAdapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TextView details_textView = (TextView) findViewById(R.id.details_text_view);
                details_textView.setText(customAdapter.getItemDetails(position));
            }
        });

        SavePrefs();

        Button checkSensorsBtn = findViewById(R.id.sensorsBtn);
        checkSensorsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openCheckSensorsActivity();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){


        //Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch(item.getItemId()){
            case R.id.login:{
                CustomLoginDialog customLoginDialog = new CustomLoginDialog();
                customLoginDialog.show(getSupportFragmentManager(), "login_dialog");
                return true;
            }
            case R.id.share: {

                final Dialog dialog = new Dialog(context, R.style.Dialog);
                dialog.setContentView(R.layout.dialog_share_layout);
                dialog.setTitle("Share");


                // set the custom dialog components - text, image and button
                TextView details = (TextView) dialog.findViewById(R.id.details);
                details.setText("Share on Facebook");
                ImageView image = (ImageView) dialog.findViewById(R.id.icon);
                image.setImageResource(R.drawable.facebook);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonShare);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                return true;
            }
            case R.id.contact: {
                openContactActivity();
                return true;
            }
            case R.id.user_settings:{
                openUserSettingsActivity();
                return true;
            }
            case R.id.save_internal:{
                SaveFileToInternalStorage();
                return true;
            }
            default:
                return super.onContextItemSelected(item);
        }
    }


    public void openContactActivity(){
        Intent myIntent = new Intent(getBaseContext(),   ContactActivity.class);
        startActivity(myIntent);
    }

    public void openUserSettingsActivity(){
        Intent i = new Intent(getBaseContext(), UserSettingsActivity.class);
        startActivity(i);

    }


    public void SavePrefs(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

    }


    private void SaveFileToInternalStorage() {
        String fileContents = "";
        String filename = "item_list.txt";
        for(int i=0; i < products.length; i++){
            fileContents = fileContents + products[i] + " " + details[i] +"\n" ;
        }
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(fileContents);
            toast.makeText(this,"Saved to: " + getFilesDir() + "/" + filename, Toast.LENGTH_LONG).show();
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    public void openCheckSensorsActivity(){
        Intent i = new Intent(getBaseContext(), CheckSensorsActivity.class);
        startActivity(i);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        TextView toSave = (TextView) findViewById(R.id.details_text_view);
        savedInstanceState.putString("DETAILS_STRING_KEY", toSave.getText().toString());
        toast.makeText(context, "OnSaveInstance Called",Toast.LENGTH_LONG).show();

    }



    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        TextView toRestore = (TextView) findViewById(R.id.details_text_view);
        toRestore.setText(savedInstanceState.getString("DETAILS_STRING_KEY"));
        toast.makeText(context, "OnRestoreInstanceState Called",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRestart(){
        super.onRestart();

        toast.makeText(context, "OnRestart Called",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();

        toast.makeText(context, "OnStart Called",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume(){
        super.onResume();

        toast.makeText(context, "OnResume Called",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onPause(){
        super.onPause();
        toast.makeText(context, "OnPause Called",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStop(){
        super.onStop();
        toast.makeText(context, "OnStop Called",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        toast.makeText(context, "OnDestroy Called",Toast.LENGTH_LONG).show();

    }


}
