package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.Random;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_CODE=123;
    final String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;
    final long MIN_TIME = 5000;//5000 milliseconds that is 5 seconds.
    final float MIN_DISTANCE = 1000; //1000m to location update. or 1 km

    final String WEATHER_SITE="http://api.openweathermap.org/data/2.5/weather";
    final String APP_ID="41135d7151f20a3b40619c398395330f";


    String longitude;
    String latitude;

    String locationmanager = LocationManager.GPS_PROVIDER; //gps

    LocationManager mLocationManager;//start or stop requesting location updates.
    LocationListener mLocationListener;//listen for any changes in location

	TextView temper;
	TextView city;
	ImageView condition;
	Button changeCity;
	TextView sunr_text;
	TextView suns_text;
	TextView humidity_text;
	TextView max_temp;
	TextView min_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("weather", "" + locationmanager);

	    temper=findViewById(R.id.temp_text);
	    