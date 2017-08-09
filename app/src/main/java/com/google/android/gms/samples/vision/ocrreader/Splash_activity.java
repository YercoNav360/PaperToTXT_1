package com.google.android.gms.samples.vision.ocrreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import java.util.Timer;
import java.util.TimerTask;

public class Splash_activity extends AppCompatActivity {
    TimerTask myTimerTask;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        timer = new Timer();

        myTimerTask = new TimerTask() {
            @Override
            public void run() {
                start();
            }
        };

        timer.schedule(myTimerTask, 1000);
    }

    public void start(){
        // Start home activity
        startActivity(new Intent(Splash_activity.this, MainActivity.class));
        // close splash activity
        finish();
    }
}
