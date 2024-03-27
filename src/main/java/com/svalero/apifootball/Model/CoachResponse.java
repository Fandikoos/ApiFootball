package com.svalero.apifootball.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoachResponse {
    @SerializedName("response")
    private List<Coach> response;

    public List<Coach> getResponse() {
        return response;
    }
}
