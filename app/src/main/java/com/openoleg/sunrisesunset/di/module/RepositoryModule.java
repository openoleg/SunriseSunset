package com.openoleg.sunrisesunset.di.module;

import com.openoleg.sunrisesunset.data.repository.DaylightRepositoryImpl;
import com.openoleg.sunrisesunset.data.source.remote.SunriseSunsetApi;
import com.openoleg.sunrisesunset.domain.repository.DaylightRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Provides
    DaylightRepository provideDaylightRepository(SunriseSunsetApi sunriseSunsetApi) {
        return new DaylightRepositoryImpl(sunriseSunsetApi);
    }
}
