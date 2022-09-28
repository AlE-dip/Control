package com.example.control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5000;
    public static String TAG = "AAA";
    public Button btRun, btStop;
    public ImageView imMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();
        setView();

        btRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, ControlService.class));
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this, ControlService.class));
            }
        });

        imMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "main");
            }
        });
    }

    private void setView() {
        btRun = findViewById(R.id.bt_run);
        btStop = findViewById(R.id.bt_stop);
        imMain = findViewById(R.id.im_main);
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
                if (!Settings.canDrawOverlays(this)) {
                    // You don't have permission
                    checkPermission();
                } else {
                    Log.d(TAG, "have permission");
                }
            }
        }
    }










}