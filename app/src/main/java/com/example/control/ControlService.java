package com.example.control;

import static com.example.control.MainActivity.TAG;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.nfc.Tag;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.io.IOException;

public class ControlService extends Service {

    public ControlService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View viMain = inflater.inflate(R.layout.control, null);
        Button btShowText = viMain.findViewById(R.id.bt_show_text);
        Button btStop = viMain.findViewById(R.id.bt_stop);

        btShowText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "touch me");
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                windowManager.removeView(viMain);
                stopSelf();
            }
        });

        // Add layout to window manager
        windowManager.addView(viMain, params);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Destroy");
        super.onDestroy();
        startService(new Intent(this, ControlService.class));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}