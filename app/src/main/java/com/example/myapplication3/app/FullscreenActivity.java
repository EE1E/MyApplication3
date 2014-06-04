package com.example.myapplication3.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication3.app.util.SystemUiHider;

import java.text.DateFormat;
import java.util.Date;


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
            if(((gps.getLatitude()==0.0)&&(gps.getLongitude()==0.0)))
            {
                Toast.makeText(getApplicationContext(), "No gps, open map!!! ",
                        Toast.LENGTH_LONG).show();

            }
            else
            {

                txt1.append("\n Lat: " + gps.getLatitude()); // returns latitude
                txt1.append("\n Long:" + gps.getLongitude()); // returns longitude

                // \n is for new line
            }


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
