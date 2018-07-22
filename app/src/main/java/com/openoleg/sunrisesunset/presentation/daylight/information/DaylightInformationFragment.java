package com.openoleg.sunrisesunset.presentation.daylight.information;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.openoleg.sunrisesunset.R;
import com.openoleg.sunrisesunset.SunriseSunsetApplication;
import com.openoleg.sunrisesunset.domain.model.Location;
import com.openoleg.sunrisesunset.presentation.daylight.information.mvp.DaylightInformationContract;
import com.openoleg.sunrisesunset.presentation.model.DaylightModel;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class DaylightInformationFragment extends Fragment implements DaylightInformationContract.View {
    private static final int REQUEST_PLACE_PICKER = 1001;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

    @Inject
    public DaylightInformationContract.Presenter presenter;

    private TextView cityTextView, countryTextView, dateTextView;
    private TextView setLocationTextButton, setDateTextButton;
    private TextView sunriseTextView, sunsetTextView, solarNoonTextView, dayLengthTextView;
    private TextView civilTwilightTextView, nauticalTwilightTextView, astronomicalTwilightTextView;

    private GoogleApiClient googleApiClient;
    private Geocoder geocoder;

    public DaylightInformationFragment() {}

    public static DaylightInformationFragment getInstance() {
        return new DaylightInformationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_daylight_information, container, false);
        SunriseSunsetApplication.getApplication().getDaylightComponent().inject(this);
        findAllViews(parentView);
        // Listeners
        setLocationTextButton.setOnClickListener(view -> presenter.onSetLocationButtonPressed());
        setDateTextButton.setOnClickListener(view -> presenter.onSetDateButtonPressed());

        googleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        return parentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
        presenter.attachView(this);
    }

    @Override
    public void onStop() {
        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
        presenter.detachView();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PLACE_PICKER && resultCode == Activity.RESULT_OK) {
            Place place = PlacePicker.getPlace(getActivity(), data);
            try {
                Address address = address = geocoder.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1).get(0);
                presenter.onLocationRetrieved(new Location(
                        (float) place.getLatLng().latitude,
                        (float) place.getLatLng().longitude,
                        address.getLocality(),
                        address.getCountryName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Contract
    @Override
    public void displayLocation() {
        if (!checkFineLocationPermission()) {
            return;
        }
        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi.getCurrentPlace(googleApiClient, null);
        result.setResultCallback(placeLikelihoods -> {
            PlaceLikelihood placeLikelihood = placeLikelihoods.get(0);
            if (placeLikelihood != null && placeLikelihood.getPlace() != null) {
                Place place = placeLikelihood.getPlace();
                try {
                    Address address = geocoder.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1).get(0);
                    presenter.onLocationRetrieved(new Location(
                            (float) place.getLatLng().latitude,
                            (float) place.getLatLng().longitude,
                            address.getLocality(),
                            address.getCountryName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void displayLocation(Location location) {
        cityTextView.setText(location.getCity());
        countryTextView.setText(location.getCountry());
    }

    @Override
    public void displayDate(Date date) {
        dateTextView.setText(DATE_FORMAT.format(date));
    }

    @Override
    public void displayDaylightInformation(DaylightModel daylightModel) {
        sunriseTextView.setText(String.format("%s: %s", getResources().getString(R.string.daylight_information_sunrise), daylightModel.getSunrise()));
        sunsetTextView.setText(String.format("%s: %s", getResources().getString(R.string.daylight_information_sunset), daylightModel.getSunset()));
        solarNoonTextView.setText(String.format("%s: %s", getResources().getString(R.string.daylight_information_solar_noon), daylightModel.getSolarNoon()));
        dayLengthTextView.setText(String.format("%s: %s", getResources().getString(R.string.daylight_information_day_length), daylightModel.getDayLength()));

        civilTwilightTextView.setText(String.format("%s: %s - %s",
                getResources().getString(R.string.daylight_information_civil),
                daylightModel.getCivilTwilightBegin(),
                daylightModel.getCivilTwilightEnd()));
        nauticalTwilightTextView.setText(String.format("%s: %s - %s",
                getResources().getString(R.string.daylight_information_nautical),
                daylightModel.getNauticalTwilightBegin(),
                daylightModel.getNauticalTwilightEnd()));
        astronomicalTwilightTextView.setText(String.format("%s: %s - %s",
                getResources().getString(R.string.daylight_information_astronomical),
                daylightModel.getAstronomicalTwilightBegin(),
                daylightModel.getAstronomicalTwilightEnd()));
    }

    @Override
    public void displayPlacePicker() {
        if (googleApiClient == null || !googleApiClient.isConnected()) {
            return;
        }
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(getActivity()), REQUEST_PLACE_PICKER);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog.OnDateSetListener listener = (datePicker, pickedYear, pickedMonth, pickedDay) -> {
            try {
                Date date = DATE_FORMAT.parse(String.format("%02d.%02d.%d", pickedDay, pickedMonth+1, pickedYear));
                presenter.onDateRetrieved(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), listener, currentYear, currentMonth, currentDay);
        datePickerDialog.show();
    }

    private void findAllViews(View view) {
        cityTextView = view.findViewById(R.id.fragment_daylight_information_city);
        countryTextView = view.findViewById(R.id.fragment_daylight_information_country);
        dateTextView = view.findViewById(R.id.fragment_daylight_information_date);

        setLocationTextButton = view.findViewById(R.id.fragment_daylight_information_set_location_button);
        setDateTextButton = view.findViewById(R.id.fragment_daylight_information_set_date_button);

        sunriseTextView = view.findViewById(R.id.fragment_daylight_information_sunrise);
        sunsetTextView = view.findViewById(R.id.fragment_daylight_information_sunset);
        solarNoonTextView = view.findViewById(R.id.fragment_daylight_information_solar_noon);
        dayLengthTextView = view.findViewById(R.id.fragment_daylight_information_day_length);

        civilTwilightTextView = view.findViewById(R.id.fragment_daylight_information_civil_twilight);
        nauticalTwilightTextView = view.findViewById(R.id.fragment_daylight_information_nautical_twilight);
        astronomicalTwilightTextView = view.findViewById(R.id.fragment_daylight_information_astronomical_twilight);
    }

    private boolean checkFineLocationPermission() {
        return ActivityCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
}
