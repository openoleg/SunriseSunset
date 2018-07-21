package com.openoleg.sunrisesunset.data.source.remote;

import com.openoleg.sunrisesunset.data.model.DaylightResponseEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SunriseSunsetApi {
    @GET("/json")
    Call<DaylightResponseEntity> getDaylight(@Query("lat") Float lat,
                                             @Query("lng") Float lng,
                                             @Query("date") String date,
                                             @Query("formatted") Integer formatted);
}
