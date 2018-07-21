package com.openoleg.sunrisesunset.di.module;

import com.openoleg.sunrisesunset.data.source.remote.SunriseSunsetApi;
import com.openoleg.sunrisesunset.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    @Provides
    @ApplicationScope
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.sunrise-sunset.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @ApplicationScope
    SunriseSunsetApi provideSunriseSunsetApi(Retrofit retrofit) {
        return retrofit.create(SunriseSunsetApi.class);
    }
}
