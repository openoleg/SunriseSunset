package com.openoleg.sunrisesunset;

import android.app.Application;

import com.openoleg.sunrisesunset.di.component.ApplicationComponent;
import com.openoleg.sunrisesunset.di.component.DaggerApplicationComponent;
import com.openoleg.sunrisesunset.di.component.DaylightComponent;

public class SunriseSunsetApplication extends Application {
    private static SunriseSunsetApplication application;
    private ApplicationComponent applicationComponent;
    private DaylightComponent daylightComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        applicationComponent = DaggerApplicationComponent.builder().build();
    }

    public static SunriseSunsetApplication getApplication() {
        return application;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public DaylightComponent getDaylightComponent() {
        if (daylightComponent == null) {
            daylightComponent = applicationComponent.daylightComponentBuilder().build();
        }
        return daylightComponent;
    }

    public void clearDaylightComponent() {
        daylightComponent = null;
    }
}
