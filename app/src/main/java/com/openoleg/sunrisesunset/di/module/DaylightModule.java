package com.openoleg.sunrisesunset.di.module;

import com.openoleg.sunrisesunset.di.scope.DaylightScope;
import com.openoleg.sunrisesunset.domain.usecase.GetDaylightUseCase;
import com.openoleg.sunrisesunset.presentation.daylight.information.mvp.DaylightInformationContract;
import com.openoleg.sunrisesunset.presentation.daylight.information.mvp.DaylightInformationPresenter;

import java.util.concurrent.ExecutorService;

import dagger.Module;
import dagger.Provides;

@Module
public class DaylightModule {
    @Provides
    @DaylightScope
    DaylightInformationContract.Presenter provideDaylightInformationPresenter(ExecutorService executorService, GetDaylightUseCase getDaylightUseCase) {
        return new DaylightInformationPresenter(executorService, getDaylightUseCase);
    }
}
