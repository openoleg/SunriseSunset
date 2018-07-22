package com.openoleg.sunrisesunset.domain.repository;

import com.openoleg.sunrisesunset.domain.model.Daylight;

public interface DaylightRepository {
    Daylight getDaylight(float lat, float lng, String date);
}
