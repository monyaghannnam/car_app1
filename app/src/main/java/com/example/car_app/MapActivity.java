package com.example.car_app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {
    private boolean mlocationPermissionGranted=false;
    private static final int Location_Permission_Request_Code=1234;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    private void getLocationPermission(){ //send permission request
         String[]permissions={Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
         if(ContextCompat.checkSelfPermission(this.getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){

             if(ContextCompat.checkSelfPermission(this.getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                 mlocationPermissionGranted=true;

             }else {
                 ActivityCompat.requestPermissions(this,permissions,Location_Permission_Request_Code);
             }

         }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mlocationPermissionGranted=false;
        switch (requestCode){
            case Location_Permission_Request_Code:{
                if(grantResults.length>0 ){
                    for(int i=0;i<grantResults.length;i++) {
                        if(grantResults[i]!=PackageManager.PERMISSION_GRANTED) {
                            mlocationPermissionGranted = false;
                            return;
                        }
                    }
                    mlocationPermissionGranted = true; //initlize our map
                }
            }
        }
    }
}
