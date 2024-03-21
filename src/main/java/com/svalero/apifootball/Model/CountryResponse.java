package com.svalero.apifootball.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryResponse {
    @SerializedName("response")
    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }
}
