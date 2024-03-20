package com.svalero.apifootball.Service;


import com.svalero.apifootball.Model.Country;
import com.svalero.apifootball.Model.CountryResponse;
import com.svalero.apifootball.Model.Team;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface FootballApi {
    @GET("teams")
    Observable<List<Team>> getTeamsByCountry(@Query("country") String country);

    @GET("countries")
    Observable<CountryResponse> getCountries();
}
