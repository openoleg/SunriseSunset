package com.openoleg.sunrisesunset.domain.model;

import java.util.Date;

public class Daylight {
    private Date sunrise, sunset, solarNoon;
    private String dayLength;
    private Date civilTwilightBegin, civilTwilightEnd;
    private Date nauticalTwilightBegin, nauticalTwilightEnd;
    private Date astronomicalTwilightBegin, astronomicalTwilightEnd;

    public Daylight(Date sunrise, Date sunset, Date solarNoon, String dayLength, Date civilTwilightBegin, Date civilTwilightEnd, Date nauticalTwilightBegin, Date nauticalTwilightEnd, Date astronomicalTwilightBegin, Date astronomicalTwilightEnd) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.solarNoon = solarNoon;
        this.dayLength = dayLength;
        this.civilTwilightBegin = civilTwilightBegin;
        this.civilTwilightEnd = civilTwilightEnd;
        this.nauticalTwilightBegin = nauticalTwilightBegin;
        this.nauticalTwilightEnd = nauticalTwilightEnd;
        this.astronomicalTwilightBegin = astronomicalTwilightBegin;
        this.astronomicalTwilightEnd = astronomicalTwilightEnd;
    }

    public Date getSunrise() {
        return sunrise;
    }

    public Date getSunset() {
        return sunset;
    }

    public Date getSolarNoon() {
        return solarNoon;
    }

    public String getDayLength() {
        return dayLength;
    }

    public Date getCivilTwilightBegin() {
        return civilTwilightBegin;
    }

    public Date getCivilTwilightEnd() {
        return civilTwilightEnd;
    }

    public Date getNauticalTwilightBegin() {
        return nauticalTwilightBegin;
    }

    public Date getNauticalTwilightEnd() {
        return nauticalTwilightEnd;
    }

    public Date getAstronomicalTwilightBegin() {
        return astronomicalTwilightBegin;
    }

    public Date getAstronomicalTwilightEnd() {
        return astronomicalTwilightEnd;
    }
}
