package edu.rit.se.milk.demoapp05;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnDate, btnTime;
    TextView txtDisplay;
    Switch switchRendering;
    LinearLayout layoutCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDate = (Button) findViewById(R.id.buttonTodayDate);
        btnTime = (Button) findViewById(R.id.buttonCurrentTime);
        txtDisplay = (TextView) findViewById(R.id.textViewDisplay);
        switchRendering = (Switch) findViewById(R.id.switchAccessibility);
        layoutCover = (LinearLayout)findViewById(R.id.layoutContents);

        setupButtonHandlers();
    }

    private void setupButtonHandlers() {
        switchRendering.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    layoutCover.setBackgroundColor(Color.BLACK);
                    btnDate.setBackgroundColor(Color.BLACK);
                    btnTime.setBackgroundColor(Color.BLACK);
                }
                else
                {
                    layoutCover.setBackgroundColor(Color.TRANSPARENT);
                    btnDate.setBackgroundResource(android.R.drawable.btn_default);
                    btnTime.setBackgroundResource(android.R.drawable.btn_default);
                }
            }
        });


        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar today = Calendar.getInstance();
                Date todayDate = today.getTime();
                String todayDateString = new SimpleDateFormat("EEEE, MMMM d, yyyy").format(todayDate);

                txtDisplay.setText("Today's Date: " + todayDateString);

            }
        });


        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar today = Calendar.getInstance();
                Date todayDate = today.getTime();
                String currentTimeString = new SimpleDateFormat("hh:mm:ss a").format(todayDate);
                
                txtDisplay.setText("Current time: " + currentTimeString);
            }
        });
    }
}
