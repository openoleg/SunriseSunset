package com.openoleg.sunrisesunset.data.model;

import com.openoleg.sunrisesunset.domain.model.Daylight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class EntityMapper {
    public static Daylight toDaylight(DaylightEntity daylightEntity) throws ParseException {
        SimpleDateFormat dateFormat;

        Date sunrise;
        Date sunset;
        Date solarNoon;

        String dayLength;

        Date civilTwilightBegin;
        Date civilTwilightEnd;

        Date nauticalTwilightBegin;
        Date nauticalTwilightEnd;

        Date astronomicalTwilightBegin;
        Date astronomicalTwilightEnd;

        if (daylightEntity.getDayLength().contains(":")) {
            dateFormat = new SimpleDateFormat("hh:mm:ss a", Locale.ENGLISH);
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            dayLength = daylightEntity.getDayLength();

        }
        else {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ENGLISH);
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            dayLength = convertDayLength(daylightEntity.getDayLength());
        }

        sunrise = dateFormat.parse(daylightEntity.getSunrise());
        sunset = dateFormat.parse(daylightEntity.getSunset());
        solarNoon = dateFormat.parse(daylightEntity.getSolarNoon());

        civilTwilightBegin = dateFormat.parse(daylightEntity.getCivilTwilightBegin());
        civilTwilightEnd = dateFormat.parse(daylightEntity.getCivilTwilightEnd());

        nauticalTwilightBegin = dateFormat.parse(daylightEntity.getNauticalTwilightBegin());
        nauticalTwilightEnd = dateFormat.parse(daylightEntity.getNauticalTwilightEnd());

        astronomicalTwilightBegin = dateFormat.parse(daylightEntity.getAstronomicalTwilightBegin());
        astronomicalTwilightEnd = dateFormat.parse(daylightEntity.getAstronomicalTwilightEnd());

        return new Daylight(sunrise, sunset, solarNoon, dayLength, civilTwilightBegin, civilTwilightEnd, nauticalTwilightBegin, nauticalTwilightEnd, astronomicalTwilightBegin, astronomicalTwilightEnd);
    }

    private static String convertDayLength(String unformatted) {
        int dayLengthInSeconds = Integer.parseInt(unformatted);
        int hours = dayLengthInSeconds / 3600;
        int minutes = (dayLengthInSeconds % 3600) / 60;
        int seconds = dayLengthInSeconds % 60;
        return String.format("%02d, %02d, %02d", hours, minutes, seconds);
    }
}
