package com.svalero.apifootball.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamResponse {
    @SerializedName("response")
    private List<TeamVenueWrapper> response;

    public List<TeamVenueWrapper> getResponse() {
        return response;
    }
}
