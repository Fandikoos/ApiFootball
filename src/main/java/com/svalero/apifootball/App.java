package com.svalero.apifootball;

import com.svalero.apifootball.Model.Team;
import com.svalero.apifootball.Model.TeamResponse;
import com.svalero.apifootball.Model.Venue;
import com.svalero.apifootball.Service.TeamService;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.TestObserver;
import javafx.application.Application;
import javafx.stage.Stage;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

    }
    public static void main(String[] args) {
        launch();
        /* Crear una instancia de TeamService
        TeamService teamService = new TeamService();

        teamService.listCountries();

        try {
            Thread.sleep(5000); // Esperar 5 segundos (ajusta este valor según sea necesario)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
    
}
