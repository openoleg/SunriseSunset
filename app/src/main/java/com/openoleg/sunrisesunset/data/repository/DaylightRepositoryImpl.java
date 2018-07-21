package com.openoleg.sunrisesunset.data.repository;

import com.openoleg.sunrisesunset.data.model.DaylightResponseEntity;
import com.openoleg.sunrisesunset.data.model.EntityMapper;
import com.openoleg.sunrisesunset.data.source.remote.SunriseSunsetApi;
import com.openoleg.sunrisesunset.domain.model.Daylight;
import com.openoleg.sunrisesunset.domain.repository.DaylightRepository;

import java.io.IOException;
import java.text.ParseException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class DaylightRepositoryImpl implements DaylightRepository {
    private SunriseSunsetApi sunriseSunsetApi;

    @Inject
    public DaylightRepositoryImpl(SunriseSunsetApi sunriseSunsetApi) {
        this.sunriseSunsetApi = sunriseSunsetApi;
    }

    @Override
    public Daylight getDaylight(float lat, float lng) {
        Daylight daylight = null;
        Call<DaylightResponseEntity> call = sunriseSunsetApi.getDaylight(lat, lng, null, null);
        try {
            Response<DaylightResponseEntity> response = call.execute();
            switch (response.body().getStatusCode()) {
                case "OK":
                    try {
                        daylight = EntityMapper.toDaylight(response.body().getDaylightEntity());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "INVALID_REQUEST":

                case "INVALID_DATE":

                case "UNKNOWN_ERROR":
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return daylight;
    }
}
