package com.openoleg.sunrisesunset.di.component;

import com.openoleg.sunrisesunset.di.module.DaylightModule;
import com.openoleg.sunrisesunset.di.scope.DaylightScope;
import com.openoleg.sunrisesunset.presentation.daylight.DaylightActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {DaylightModule.class})
@DaylightScope
public interface DaylightComponent {
    @Subcomponent.Builder
    interface Builder {
        DaylightComponent build();
    }

    void inject(DaylightActivity daylightActivity);
}
