package com.example.myapplication3.app;

import com.example.myapplication3.app.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Date;
import android.widget.Toast;
import java.text.DateFormat;


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


    public void btn1clk(View arg0) {



        txt1 = (TextView) findViewById(R.id.txt1);
        txt1.setText(btn1.getText());
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        txt1.append("\n" + currentDateTimeString);


        gps = new GPS(FullscreenActivity.this);
        //check if GPS enabled
        if(gps.canGetLocation()){

            txt1.append("\n" + gps.getLatitude()); // returns latitude
            txt1.append("\n" + gps.getLongitude()); // returns longitude

            // \n is for new line

        }else{
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
    }




}
