package edu.rit.se.demoapp02;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements CircleEventListener {


    RelativeLayout layoutCircle;
    Button buttonStart;
    TextView textViewScore;
    Switch switchRendering;

    Timer circleMoveTimer, circleGenerateTimer;
    int hitsValid = 0, hitsTotal = 0;
    List<Circle> circleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutCircle = (RelativeLayout) findViewById(R.id.layoutCircle);
        layoutCircle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hitsTotal++;
                        textViewScore.setText("Successful Hits: " + hitsValid + "  Misses: " + (hitsTotal -  hitsValid));
                    }
                }
        );
        buttonStart = (Button) findViewById(R.id.buttonGame);
        textViewScore = (TextView) findViewById(R.id.textViewStatus);
        switchRendering = (Switch) findViewById(R.id.switchAccessibility);

        switchRendering.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) //Line A
            {
                reset();

                String message;
                if (isChecked)
                {
                    message = "Circles will be rendered as seen by a color blind (Deuteranope) person";
                }
                else
                {
                    message = "Circles will be rendered as seen by a non-color blind person";
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder
                        .setTitle(MainActivity.this.getTitle())
                        .setMessage(message+"\n\nTap the GREEN circle to score points!\n\nGood Luck!")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                buttonStart.callOnClick();
                            }
                        })
                        .show();
            }
        });
    }

    public void startButton_onClick(View v) {
        reset();

        circleMoveTimer = new Timer();
        circleMoveTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (Circle c : circleList) {
                            c.Move();
                        }
                    }
                });
            }
        }, 0, 700);

        circleGenerateTimer = new Timer();
        circleGenerateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        generateCircle();
                    }
                });
            }
        }, 0, 900);
    }

    private void reset() {
        buttonStart.setText("New Game");
        hitsTotal = 0;
        hitsValid = 0;
        circleList = new ArrayList<Circle>();
        textViewScore.setText("Successful Hits: " + hitsValid + "  Misses: " + (hitsTotal -  hitsValid));
        layoutCircle.removeAllViews();
        if (circleGenerateTimer != null) {
            circleGenerateTimer.cancel();
        }

        if (circleMoveTimer != null) {
            circleMoveTimer.cancel();
        }
    }


    private void generateCircle() {
        int randomColor = Utility.GenerateRandomInteger(1, 4);
        int color = Color.GRAY;
        boolean poppable = false;
        switch (randomColor) { //Alternate colors are based on Deuteranope color vision
            case 1:
                color = switchRendering.isChecked() ? Color.parseColor("#AA9400") : Color.parseColor("#FF0000");
                poppable = false;
                break; //Red
            case 2:
                color = switchRendering.isChecked() ? Color.parseColor("#DAC22E") : Color.parseColor("#00FF00");
                poppable = true;
                break; //Green
            case 3:
                color = switchRendering.isChecked() ? Color.parseColor("#014EFE") : Color.parseColor("#0000FF");
                poppable = false;
                break; //Blue
            case 4:
                color = switchRendering.isChecked() ? Color.parseColor("#DAC200") : Color.parseColor("#FFA500");
                ;
                poppable = false;
                break; //Orange
        }

        Circle circle = new Circle(this, color, layoutCircle.getWidth(), poppable);
        circle.setCircleEventListener(this);

        circleList.add(circle);

        layoutCircle.addView(circle);
    }


    @Override
    public void CirclePopped() {
        hitsValid++;
        textViewScore.setText("Successful Hits: " + hitsValid + "  Misses: " + (hitsTotal -  hitsValid));
    }
}
