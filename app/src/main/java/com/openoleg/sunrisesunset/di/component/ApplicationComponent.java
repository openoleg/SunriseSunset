package com.openoleg.sunrisesunset.di.component;

import com.openoleg.sunrisesunset.di.module.ConcurrencyModule;
import com.openoleg.sunrisesunset.di.module.RepositoryModule;
import com.openoleg.sunrisesunset.di.module.RetrofitModule;
import com.openoleg.sunrisesunset.di.module.UseCaseModule;
import com.openoleg.sunrisesunset.di.scope.ApplicationScope;

import dagger.Component;

@Component(modules = {RetrofitModule.class, ConcurrencyModule.class, RepositoryModule.class, UseCaseModule.class})
@ApplicationScope
public interface ApplicationComponent {
    DaylightComponent.Builder daylightComponentBuilder();
}
