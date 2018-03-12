package com.pvirtch.ffmpegcmake;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.pvirtch.ffmpegcmake.permission.CheckPermListener;
import com.pvirtch.ffmpegcmake.permission.EasyPermissions;
import com.pvirtch.ffmpegcmake.permission.PermissionCallbacks;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SurfaceHolder mHolder;
    private String[] s = {android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission
            .WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission
            .INTERNET, android.Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Example of a call to a native method 
       /* TextView tv = (TextView) findViewById(R.id.sample_text);
        String[] a = {"1", "2"};
        tv.setText(FFmpegKit.play("jiade") + "");*/
        //获取权限
        ActivityCompat.requestPermissions(this, s, 0);
        SurfaceView sfPlay = findViewById(R.id.main_surface_play);
        mHolder = sfPlay.getHolder();
        mHolder.addCallback(surfaceBack);

        findViewById(R.id.compress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                intent.putExtra("path", "/storage/emulated/0/DCIM/Camera/123.mp4");
                intent.putExtra("imagePath", "/storage/emulated/0/DCIM/Camera/123.mp4");
                startActivity(intent);
            }
        });
    }

    private SurfaceHolder.Callback surfaceBack = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(final SurfaceHolder holder) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FFmpegKit.play(holder.getSurface());
                }
            }).start();


        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    };
    private CheckPermListener mPermissionListener;

    public void checkPermission(CheckPermListener listener, int resString, String... mPerms) {
        mPermissionListener = listener;
        if (EasyPermissions.hasPermissions(this, mPerms)) {
            if (mPermissionListener != null)
                mPermissionListener.superPermission();
        } else {
            EasyPermissions.requestPermissions(this, getString(resString), 123, mPerms);
        }
    }

    /**
     * 用户权限处理,
     * 如果全部获取, 则直接过.
     * 如果权限缺失, 则提示Dialog.
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

  
}
