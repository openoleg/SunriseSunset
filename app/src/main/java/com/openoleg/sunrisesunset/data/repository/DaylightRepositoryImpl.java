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
    public Daylight getDaylight(float lat, float lng, String date) {
        Daylight daylight;
        Call<DaylightResponseEntity> call = sunriseSunsetApi.getDaylight(lat, lng, date, 1);
        try {
            Response<DaylightResponseEntity> response = call.execute();
            if (response.body().getStatusCode().equals("OK")) {
                try {
                    daylight = EntityMapper.toDaylight(response.body().getDaylightEntity());
                    return daylight;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
