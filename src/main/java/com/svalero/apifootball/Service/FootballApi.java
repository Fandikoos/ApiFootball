package com.svalero.apifootball.Service;


import com.svalero.apifootball.Model.TeamInfo;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface FootballApi {
    @GET("v3.football.api-sports.io/teams/{country}")
    Observable<List<TeamInfo>> getTeamInfo(@Path("country") String country);

}
