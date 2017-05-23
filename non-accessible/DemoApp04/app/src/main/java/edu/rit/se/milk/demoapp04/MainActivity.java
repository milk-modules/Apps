package edu.rit.se.milk.demoapp04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button btnSave, btnReset, btnCheck, btnBack;
    EditText txtName, txtUsername, txtPassword;
    Switch switchRendering;
    Timer buttonMoveTimer;
    List<View> movingButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void setupEventHandlers() {
        switchRendering.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonMoveTimer = new Timer();
                    buttonMoveTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    moveUIElements();
                                }
                            });
                        }
                    }, 0, 50);
                } else {
                    initialize();
                    buttonMoveTimer.cancel();
                }
            }
        });
    }


    private void initialize() {
        setContentView(R.layout.activity_main);

        btnBack = (Button) findViewById(R.id.button00);
        btnReset = (Button) findViewById(R.id.button01);
        btnCheck = (Button) findViewById(R.id.button02);
        btnSave = (Button) findViewById(R.id.button03);
        txtName = (EditText) findViewById(R.id.editText10);
        txtUsername = (EditText) findViewById(R.id.editText11);
        txtPassword = (EditText) findViewById(R.id.editText12);

        switchRendering = (Switch) findViewById(R.id.switchAccessibility);

        movingButtons = new ArrayList<>();
        movingButtons.add(btnBack);
        movingButtons.add(btnReset);
        movingButtons.add(btnCheck);
        movingButtons.add(btnSave);
        movingButtons.add(txtName);
        movingButtons.add(txtUsername);
        movingButtons.add(txtPassword);

        setupEventHandlers();
    }

    private void moveUIElements() {
        Random rand = new Random();
        int direction = rand.nextInt(2) + 1;
        View tempView;

        if (direction == 1) {
            for(int i=0;i<movingButtons.size();i++){
                tempView = ((View) movingButtons.get(i));
                tempView.setX(tempView.getX() + 5);
                tempView.setY(tempView.getY() + 10);
            }

        } else {
            for(int i=0;i<movingButtons.size();i++){
                tempView = ((View) movingButtons.get(i));
                tempView.setX(tempView.getX() - 5);
                tempView.setY(tempView.getY() - 10);
            }

        }

    }
}
