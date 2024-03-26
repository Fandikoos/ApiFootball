package com.svalero.apifootball;

import com.svalero.apifootball.Controller.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void init() throws Exception {
        System.out.println("Starting JavaFX Application");
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaz.fxml"));
        loader.setController(new AppController());
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
        //Crear una instancia de TeamService
        /*TeamService teamService = new TeamService();

        //teamService.listCountries();
        teamService.listTeamsByCountry("portugal");

        try {
            Thread.sleep(5000); // Esperar 5 segundos (ajusta este valor según sea necesario)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
    
}
