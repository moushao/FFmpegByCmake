package com.pvirtch.ffmpegcmake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SurfaceHolder mHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Example of a call to a native method 
       /* TextView tv = (TextView) findViewById(R.id.sample_text);
        String[] a = {"1", "2"};
        tv.setText(FFmpegKit.play("jiade") + "");*/
        SurfaceView sfPlay = findViewById(R.id.main_surface_play);
        mHolder = sfPlay.getHolder();
        mHolder.addCallback(surfaceBack);
    }

    private SurfaceHolder.Callback surfaceBack = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            FFmpegKit.play(holder.getSurface() );
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    };

}
