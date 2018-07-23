package com.openoleg.sunrisesunset.presentation.daylight.information.mvp;

import com.openoleg.sunrisesunset.domain.model.Daylight;
import com.openoleg.sunrisesunset.domain.model.Location;
import com.openoleg.sunrisesunset.domain.usecase.GetDaylightUseCase;
import com.openoleg.sunrisesunset.domain.usecase.IsInternetConnectedUseCase;
import com.openoleg.sunrisesunset.presentation.base.mvp.BasePresenter;
import com.openoleg.sunrisesunset.presentation.model.ModelMapper;

import java.util.Date;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class DaylightInformationPresenter extends BasePresenter<DaylightInformationContract.View> implements DaylightInformationContract.Presenter {
    private ExecutorService executorService;
    private GetDaylightUseCase getDaylightUseCase;
    private IsInternetConnectedUseCase isInternetConnectedUseCase;
    private Location location;
    private Date date;
    private Daylight daylight;
    private IsInternetConnectedUseCase.IsInternetConnectedObserver isInternetConnectedObserver;

    @Inject
    public DaylightInformationPresenter(ExecutorService executorService, GetDaylightUseCase getDaylightUseCase, IsInternetConnectedUseCase isInternetConnectedUseCase) {
        this.executorService = executorService;
        this.getDaylightUseCase = getDaylightUseCase;
        this.isInternetConnectedUseCase = isInternetConnectedUseCase;
        date = new Date();
        isInternetConnectedObserver = new IsInternetConnectedUseCase.IsInternetConnectedObserver() {
            @Override
            public void onConnected() {
                if (view != null) {
                    view.displayLocation();
                }
            }

            @Override
            public void onDisconnected() {
                if (view != null) {
                    view.displayNoInternetConnectionSnackbar();
                }
            }
        };
    }

    @Override
    public void attachView(DaylightInformationContract.View view) {
        super.attachView(view);
        if (view != null) {
            if (location != null && daylight != null) {
                view.displayLocation(location);
                view.displayDaylightInformation(ModelMapper.toDaylightModel(daylight, "HH:mm:ss"));
            }
            else {
                executorService.execute(() -> isInternetConnectedUseCase.run(isInternetConnectedObserver));
            }
            view.displayDate(date);
        }
    }

    @Override
    public void onLocationRetrieved(Location location) {
        this.location = location;
        if (view != null) {
            view.displayLocation(location);
        }
        doDaylightRequest();
    }

    @Override
    public void onDateRetrieved(Date date) {
        this.date = date;
        if (view != null) {
            view.displayDate(date);
        }
        doDaylightRequest();
    }

    @Override
    public void onSetLocationButtonPressed() {
        if (view != null) {
            view.displayPlacePicker();
        }
    }

    @Override
    public void onSetDateButtonPressed() {
        if (view != null) {
            view.displayDatePickerDialog();
        }
    }

    private void doDaylightRequest() {
        GetDaylightUseCase.GetDaylightUseCaseObserver observer = daylight -> {
            this.daylight = daylight;
            if (view != null) {
                view.displayDaylightInformation(ModelMapper.toDaylightModel(daylight, "HH:mm:ss"));
            }
        };
        executorService.execute(() -> getDaylightUseCase.run(location.getLatitude(), location.getLongitude(), date, observer));
    }

    @Override
    public void onNoInternetConnectionSnackbarTryAgain() {
        executorService.execute(() -> isInternetConnectedUseCase.run(isInternetConnectedObserver));
    }
}
