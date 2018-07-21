package com.openoleg.sunrisesunset.data.model;

import com.google.gson.annotations.SerializedName;

public class DaylightResponseEntity {
    @SerializedName("results")
    private DaylightEntity daylightEntity;
    @SerializedName("status")
    private String statusCode;

    public DaylightEntity getDaylightEntity() {
        return daylightEntity;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setDaylightEntity(DaylightEntity daylightEntity) {
        this.daylightEntity = daylightEntity;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
