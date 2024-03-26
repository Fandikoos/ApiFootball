package com.svalero.apifootball.Service;


import com.svalero.apifootball.Model.CountryResponse;
import com.svalero.apifootball.Model.TeamResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FootballApi {
    @GET("teams")
    Observable<TeamResponse> getTeamsByCountry(@Query("country") String country);

    @GET("countries")
    Observable<CountryResponse> getCountries();

}
