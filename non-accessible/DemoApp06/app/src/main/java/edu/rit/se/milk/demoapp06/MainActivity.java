package edu.rit.se.milk.demoapp06;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements WeatherFragment.OnFragmentInteractionListener{

    Timer weatherTimer;
    WeatherFragment todayWeather,tomorrowWeather;
    Switch switchRendering;
    LinearLayout layoutCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateWeather();

        switchRendering = (Switch)findViewById(R.id.switchAccessibility);
        layoutCover = (LinearLayout)findViewById(R.id.layoutContents);

        switchRendering.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked) {
                    layoutCover.setBackgroundColor(Color.BLACK);
                    todayWeather.setFragmentVisibility(false);
                    tomorrowWeather.setFragmentVisibility(false);
                }
                else
                {
                    layoutCover.setBackgroundColor(Color.TRANSPARENT);
                    todayWeather.setFragmentVisibility(true);
                    tomorrowWeather.setFragmentVisibility(true);
                }
            }
        });

    }

    private void generateWeather(){
        weatherTimer = new Timer();
        weatherTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        todayWeather = (WeatherFragment)
                                getSupportFragmentManager().findFragmentById(R.id.weather_fragment_today);
                        todayWeather.generateRandomWeatherForecast("today");


                        tomorrowWeather = (WeatherFragment)
                                getSupportFragmentManager().findFragmentById(R.id.weather_fragment_tomorrow);
                        tomorrowWeather.generateRandomWeatherForecast("tomorrow");
                    }
                });
            }
        }, 0, 30000);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
