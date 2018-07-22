package com.openoleg.sunrisesunset.presentation.model;

public class DaylightModel {
    private String sunrise, sunset, solarNoon;
    private String dayLength;
    private String civilTwilightBegin, civilTwilightEnd;
    private String nauticalTwilightBegin, nauticalTwilightEnd;
    private String astronomicalTwilightBegin, astronomicalTwilightEnd;

    public DaylightModel(String sunrise, String sunset, String solarNoon, String dayLength, String civilTwilightBegin, String civilTwilightEnd, String nauticalTwilightBegin, String nauticalTwilightEnd, String astronomicalTwilightBegin, String astronomicalTwilightEnd) {
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

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getSolarNoon() {
        return solarNoon;
    }

    public String getDayLength() {
        return dayLength;
    }

    public String getCivilTwilightBegin() {
        return civilTwilightBegin;
    }

    public String getCivilTwilightEnd() {
        return civilTwilightEnd;
    }

    public String getNauticalTwilightBegin() {
        return nauticalTwilightBegin;
    }

    public String getNauticalTwilightEnd() {
        return nauticalTwilightEnd;
    }

    public String getAstronomicalTwilightBegin() {
        return astronomicalTwilightBegin;
    }

    public String getAstronomicalTwilightEnd() {
        return astronomicalTwilightEnd;
    }
}
