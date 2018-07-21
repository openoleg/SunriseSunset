package com.openoleg.sunrisesunset.di.module;

import com.openoleg.sunrisesunset.device.concurrency.MainThreadImpl;
import com.openoleg.sunrisesunset.di.scope.ApplicationScope;
import com.openoleg.sunrisesunset.domain.concurrency.MainThread;

import dagger.Module;
import dagger.Provides;

@Module
public class ConcurrencyModule {
    @Provides
    @ApplicationScope
    MainThread provideMainThread() {
        return new MainThreadImpl();
    }
}
