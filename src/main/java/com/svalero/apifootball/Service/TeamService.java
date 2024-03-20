package com.svalero.apifootball.Service;

import com.svalero.apifootball.Model.StadiumInfo;
import com.svalero.apifootball.Model.TeamInfo;
import io.reactivex.Observable;
import javafx.collections.ObservableList;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class TeamService {

    private static final String BASE_URL = "https://v3.football.api-sports.io/";
    private FootballApi footballApi;

    //Configuramos el cliente http
    public TeamService () {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        footballApi = retrofit.create(FootballApi.class);

    }

    //Metodo de la api (stream)
    public Observable<List<TeamInfo>> getStadiumInfo(String country){
        return footballApi.getTeamInfo(country);
    }

}
