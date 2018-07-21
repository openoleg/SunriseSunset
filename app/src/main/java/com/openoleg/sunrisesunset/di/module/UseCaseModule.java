package com.openoleg.sunrisesunset.di.module;

import com.openoleg.sunrisesunset.domain.concurrency.MainThread;
import com.openoleg.sunrisesunset.domain.repository.DaylightRepository;
import com.openoleg.sunrisesunset.domain.usecase.GetDaylightUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {
    @Provides
    GetDaylightUseCase provideGetDaylightUseCase(DaylightRepository daylightRepository, MainThread mainThread) {
        return new GetDaylightUseCase(daylightRepository, mainThread);
    }
}
