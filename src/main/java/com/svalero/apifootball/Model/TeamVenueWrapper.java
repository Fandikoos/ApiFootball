package com.svalero.apifootball.Model;

import com.google.gson.annotations.SerializedName;

public class TeamVenueWrapper {
    @SerializedName("team")
    private Team team;
    @SerializedName("venue")
    private Venue venue;

    public Team getTeam() {
        return team;
    }

    public Venue getVenue() {
        return venue;
    }
}
