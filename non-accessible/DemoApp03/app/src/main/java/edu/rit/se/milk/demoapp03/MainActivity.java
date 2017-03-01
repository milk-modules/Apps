package edu.rit.se.milk.demoapp03;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button buttonLeft, buttonRight;
    TextView textStatus;
    Switch switchRendering;

    RelativeLayout layoutCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLeft = (Button)findViewById(R.id.buttonLeft);
        buttonRight = (Button)findViewById(R.id.buttonRight);
        textStatus = (TextView)findViewById(R.id.textViewStatus);
        switchRendering = (Switch)findViewById(R.id.switchAccessibility);

        layoutCover = (RelativeLayout)findViewById(R.id.layoutContents);

        switchRendering.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked) {
                    layoutCover.setBackgroundColor(Color.BLACK);
                    buttonLeft.setBackgroundColor(Color.BLACK);
                    buttonRight.setBackgroundColor(Color.BLACK);
                }
                else
                {
                    layoutCover.setBackgroundColor(Color.TRANSPARENT);
                    buttonLeft.setBackgroundResource(android.R.drawable.btn_default);
                    buttonRight.setBackgroundResource(android.R.drawable.btn_default);
                }
            }
        });


        buttonLeft.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textStatus.setText("Button Tapped: \"Yes\"");
                    }
                }
        );

        buttonRight.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textStatus.setText("Button Tapped: \"No\"");
                    }
                }
        );
    }
}
