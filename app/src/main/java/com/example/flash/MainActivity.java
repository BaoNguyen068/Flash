package com.example.flash;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton bt1;
    boolean hasCameraFlash= false;
    boolean flashOn = false;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = findViewById(R.id.bt1);
        hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        bt1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(hasCameraFlash){
                    if(flashOn){
                        Toast.makeText(MainActivity.this,"flash light is on",Toast.LENGTH_SHORT).show();


                        bt1.setImageResource(R.drawable.power_off);
                        flashOn= false;
                        try {
                            flashLightOff();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }

                    }
                    else{
                        Toast.makeText(MainActivity.this,"flash light is off",Toast.LENGTH_SHORT).show();

                        bt1.setImageResource(R.drawable.power_on);
                        flashOn=true;
                        try {
                            flashLightOn();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }

                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"No Flash",Toast.LENGTH_LONG).show();


                }
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            private void flashLightOn() throws CameraAccessException {
                CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                String cameraID = cameraManager.getCameraIdList()[0];
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cameraManager.setTorchMode(cameraID, true);
                }
                Toast.makeText(MainActivity.this,"flash light is on",Toast.LENGTH_SHORT).show();
                flashOn = true;
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            private void flashLightOff() throws CameraAccessException {
                CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                String cameraID = cameraManager.getCameraIdList()[0];
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cameraManager.setTorchMode(cameraID, false);
                }
                Toast.makeText(MainActivity.this,"flash light is off",Toast.LENGTH_SHORT).show();
                flashOn = false;

            }
        });

    }

}