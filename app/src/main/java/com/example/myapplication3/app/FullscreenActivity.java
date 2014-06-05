package com.example.myapplication3.app;

import com.example.myapplication3.app.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Date;

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


    public void btn1clk(View v) {
        Button btn1 = (Button) v;


        txt1 = (TextView) findViewById(R.id.txt1);
        txt1.setText(btn1.getText());
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        txt1.append("\n" + currentDateTimeString);

    }




    //Spreadsheet
    public void btn2clk(View v) {
        Button btn2 = (Button) v;


        txt1 = (TextView) findViewById(R.id.txt1);
        txt1.setText(btn2.getText());
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        txt1.append("\n" + currentDateTimeString);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        txt1 = (TextView) findViewById(R.id.txt1);
        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);

        // Get the location manager
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener ll = new mylocationlistener();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);


    }

    class mylocationlistener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            double pLong = location.getLatitude();
            double pLat = location.getLatitude();

            txt1.append("\n" + Double.toString(pLat));
            txt1.append("\n" + Double.toString(pLong));
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }

}
