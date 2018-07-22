package com.openoleg.sunrisesunset.presentation.daylight.information.mvp;

import com.openoleg.sunrisesunset.domain.model.Location;
import com.openoleg.sunrisesunset.presentation.base.mvp.BaseContract;
import com.openoleg.sunrisesunset.presentation.model.DaylightModel;

import java.util.Date;

public interface DaylightInformationContract {
    interface View extends BaseContract.View {
        void displayLocation();
        void displayLocation(Location location);
        void displayDate(Date date);
        void displayDaylightInformation(DaylightModel daylightModel);
        void displayPlacePicker();
        void displayDatePickerDialog();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void onLocationRetrieved(Location location);
        void onDateRetrieved(Date date);
        void onSetLocationButtonPressed();
        void onSetDateButtonPressed();
    }
}
