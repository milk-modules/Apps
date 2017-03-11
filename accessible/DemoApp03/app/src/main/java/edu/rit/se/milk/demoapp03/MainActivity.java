package edu.rit.se.milk.demoapp03;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {

    Button buttonLeft, buttonRight;
    TextView textStatus;
    Switch switchRendering;

    RelativeLayout layoutCover;

    Timer buttonMoveTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLeft = (Button) findViewById(R.id.buttonLeft);
        buttonRight = (Button) findViewById(R.id.buttonRight);
        textStatus = (TextView) findViewById(R.id.textViewStatus);
        switchRendering = (Switch) findViewById(R.id.switchAccessibility);
        layoutCover = (RelativeLayout) findViewById(R.id.layoutContents);

        setupEventHandlers();
    }

    private void setupEventHandlers() {
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

        switchRendering.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // reset();
                    buttonMoveTimer = new Timer();
                    buttonMoveTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    moveButton();
                                }
                            });
                        }
                    }, 0, 50);
                } else {
                    reset();
                    buttonMoveTimer.cancel();
                }
            }
        });
    }

    private void reset() {
        setContentView(R.layout.activity_main);

        buttonLeft = (Button) findViewById(R.id.buttonLeft);
        buttonRight = (Button) findViewById(R.id.buttonRight);
        textStatus = (TextView) findViewById(R.id.textViewStatus);
        switchRendering = (Switch) findViewById(R.id.switchAccessibility);
        layoutCover = (RelativeLayout) findViewById(R.id.layoutContents);

        setupEventHandlers();
    }

    private void moveButton() {
        Random rand = new Random();
        int leftX = rand.nextInt(1) + 1;
        int rightX = rand.nextInt(2) + 1;
        int rightY = rand.nextInt(3) + 1;
        int leftY = rand.nextInt(4) + 1;

        int directionX = rand.nextInt(2) + 1;
        int directionY = rand.nextInt(2) + 1;

        if (directionX == 1) {
            buttonLeft.setX(buttonLeft.getX() + leftX);
            buttonLeft.setY(buttonLeft.getY() + leftY);

            buttonRight.setX(buttonRight.getX() - rightX);
            buttonRight.setY(buttonRight.getY() - rightY);
        } else {
            buttonLeft.setX(buttonLeft.getX() - leftX);
            buttonLeft.setY(buttonLeft.getY() - leftY);

            buttonRight.setX(buttonRight.getX() + rightX);
            buttonRight.setY(buttonRight.getY() + rightY);
        }


    }
}
