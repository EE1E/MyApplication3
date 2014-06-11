package com.example.myapplication3.app;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication3.app.util.SystemUiHider;
import com.makemyandroidapp.googleformuploader.GoogleFormUploader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity{
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private TextView txt1;


    // GPSTracker class
    GPS gps;
    Button btn1;
    Button btn2;


    public void btn1clk(View arg0) {


        txt1 = (TextView) findViewById(R.id.txt1);
        txt1.setText(btn1.getText());
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        txt1.append("\n" + currentDateTimeString);


        gps = new GPS(FullscreenActivity.this);
        //check if GPS enabled
        if (gps.canGetLocation()) {
            if (((gps.getLatitude() == 0.0) && (gps.getLongitude() == 0.0))) {
                Toast.makeText(getApplicationContext(), "No GPS yet, please try again",
                        Toast.LENGTH_LONG).show();

            } else {

                txt1.append("\n Lat: " + gps.getLatitude()); // returns latitude
                txt1.append("\n Long:" + gps.getLongitude()); // returns longitude

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                StringBuilder sb = new StringBuilder();
                Geocoder gc = new Geocoder(this, Locale.getDefault());
                try {
                    List<Address> addresses = gc.getFromLocation(latitude, longitude, 5);

                    if (addresses.size() > 0) {
                        Address address = addresses.get(0);
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
                            sb.append(address.getAddressLine(i)).append("\n");
                        //sb.append(address.getLocality()).append("\n");
//                        sb.append(address.getPostalCode()).append("\n");
//                        sb.append(address.getCountryName());
//                        sb.append(address.getExtras());
//                        sb.append(address.getFeatureName());
//                        sb.append(address.getPremises());
//                        sb.append(address.getThoroughfare());
                        txt1.append("\n"+sb.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                GoogleFormUploader uploader = new GoogleFormUploader("1A2swvqW_akwg4aWL3-6FPJExVT2kpC0hb6pMOXx_PJc");
                uploader.addEntry("2058901428", "ID");
                try {
                    uploader.addEntry("755055969", URLEncoder.encode(String.valueOf(btn1.getText()), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                uploader.addEntry("1963076528", String.valueOf(gps.getLatitude()));
                uploader.addEntry("322839136", String.valueOf(gps.getLongitude()));
                uploader.addEntry("493297396", " "+sb.toString());
                uploader.upload();


                // \n is for new line
            }


        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }







    }








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);


        txt1 = (TextView) findViewById(R.id.txt1);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
    }




}
