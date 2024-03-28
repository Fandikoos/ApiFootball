package com.svalero.apifootball.Model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
