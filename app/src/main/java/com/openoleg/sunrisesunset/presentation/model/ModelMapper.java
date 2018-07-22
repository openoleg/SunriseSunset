package com.openoleg.sunrisesunset.presentation.model;

import com.openoleg.sunrisesunset.domain.model.Daylight;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ModelMapper {
    public static DaylightModel toDaylightModel(Daylight daylight, String pattern) {
        String sunrise, sunset, solarNoon;
        String dayLength;
        String civilTwilightBegin, civilTwilightEnd;
        String nauticalTwilightBegin, nauticalTwilightEnd;
        String astronomicalTwilightBegin, astronomicalTwilightEnd;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        simpleDateFormat.setTimeZone(Calendar.getInstance().getTimeZone());
        sunrise = simpleDateFormat.format(daylight.getSunrise());
        sunset = simpleDateFormat.format(daylight.getSunset());
        solarNoon = simpleDateFormat.format(daylight.getSolarNoon());
        dayLength = daylight.getDayLength();
        civilTwilightBegin = simpleDateFormat.format(daylight.getCivilTwilightBegin());
        civilTwilightEnd = simpleDateFormat.format(daylight.getCivilTwilightEnd());
        nauticalTwilightBegin = simpleDateFormat.format(daylight.getNauticalTwilightBegin());
        nauticalTwilightEnd = simpleDateFormat.format(daylight.getNauticalTwilightEnd());
        astronomicalTwilightBegin = simpleDateFormat.format(daylight.getAstronomicalTwilightBegin());
        astronomicalTwilightEnd = simpleDateFormat.format(daylight.getAstronomicalTwilightEnd());
        return new DaylightModel(sunrise, sunset, solarNoon, dayLength, civilTwilightBegin, civilTwilightEnd, nauticalTwilightBegin, nauticalTwilightEnd, astronomicalTwilightBegin, astronomicalTwilightEnd);
    }
}
