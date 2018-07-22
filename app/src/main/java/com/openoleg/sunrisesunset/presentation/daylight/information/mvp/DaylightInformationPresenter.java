package com.openoleg.sunrisesunset.presentation.daylight.information.mvp;

import com.openoleg.sunrisesunset.domain.model.Daylight;
import com.openoleg.sunrisesunset.domain.model.Location;
import com.openoleg.sunrisesunset.domain.usecase.GetDaylightUseCase;
import com.openoleg.sunrisesunset.presentation.base.mvp.BasePresenter;
import com.openoleg.sunrisesunset.presentation.model.ModelMapper;

import java.util.Date;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class DaylightInformationPresenter extends BasePresenter<DaylightInformationContract.View> implements DaylightInformationContract.Presenter {
    private ExecutorService executorService;
    private GetDaylightUseCase getDaylightUseCase;
    private Location location;
    private Date date;
    private Daylight daylight;

    @Inject
    public DaylightInformationPresenter(ExecutorService executorService, GetDaylightUseCase getDaylightUseCase) {
        this.executorService = executorService;
        this.getDaylightUseCase = getDaylightUseCase;
        date = new Date();
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
                view.displayLocation(); // View request location from places API and than call onLocationRetrieved
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
}
