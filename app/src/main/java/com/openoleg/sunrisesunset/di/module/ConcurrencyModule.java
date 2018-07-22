package com.openoleg.sunrisesunset.di.module;

import com.openoleg.sunrisesunset.device.concurrency.MainThreadImpl;
import com.openoleg.sunrisesunset.di.scope.ApplicationScope;
import com.openoleg.sunrisesunset.domain.concurrency.MainThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;

@Module
public class ConcurrencyModule {
    @Provides
    @ApplicationScope
    MainThread provideMainThread() {
        return new MainThreadImpl();
    }

    @Provides
    @ApplicationScope
    ExecutorService provideExecutorService() {
        return Executors.newFixedThreadPool(5);
    }
}
