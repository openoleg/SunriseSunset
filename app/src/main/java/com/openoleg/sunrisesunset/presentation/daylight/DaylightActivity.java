package com.openoleg.sunrisesunset.presentation.daylight;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.openoleg.sunrisesunset.R;
import com.openoleg.sunrisesunset.SunriseSunsetApplication;
import com.openoleg.sunrisesunset.presentation.daylight.information.DaylightInformationFragment;

public class DaylightActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_FINE_LOCATION = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daylight);
        requestFineLocationPermission();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_daylight_container ,DaylightInformationFragment.getInstance()).commit();
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            SunriseSunsetApplication.getApplication().clearDaylightComponent();
        }
        super.onDestroy();
    }


    private void requestFineLocationPermission() {
        ActivityCompat.requestPermissions(DaylightActivity.this,
                new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_REQUEST_FINE_LOCATION);
    }
}
