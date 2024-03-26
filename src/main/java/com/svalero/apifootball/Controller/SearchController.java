package com.svalero.apifootball.Controller;

import com.svalero.apifootball.Task.TeamTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    @FXML
    private Label lbStatus;
    @FXML
    private ProgressBar pbProgress;
    @FXML
    private ListView<String> teamsListView;

    private ObservableList<String> teamsNames;
    private String country;
    private TeamTask teamTask;

    public SearchController(String country) {
        this.country = country;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println(country + "searchController");
        this.teamsNames = FXCollections.observableArrayList();
        teamTask = new TeamTask(country, teamsNames);

        // Linkear barra de progreso
        pbProgress.progressProperty().unbind();
        pbProgress.progressProperty().bind(teamTask.progressProperty());

        teamTask.stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("The search has finished");
                alert.show();
            }
        });

        teamTask.setOnSucceeded(event -> {
            this.teamsListView.setItems(this.teamsNames);
        });


        //Actualizamos la barra de tareas
        teamTask.messageProperty().addListener((observable, oldValue, newValue) -> {
            lbStatus.setText(newValue);
        });

        new Thread(teamTask).start();

    }
}
