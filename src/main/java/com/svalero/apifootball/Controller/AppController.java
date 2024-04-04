package com.svalero.apifootball.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    private TextField tfCountryOrCoach;
    @FXML
    private TabPane tpTeamsOrCoachs;
    @FXML
    private Button btSearchTeamByCountry;
    @FXML
    private Button btSearchCoachByName;
    @FXML
    private Button btExit;


    public AppController(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tpTeamsOrCoachs.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

    }

    //Metodo para cuando pulsemo el botton
    @FXML
    public void btSearchTeamByCountry (ActionEvent event) {
        String country = tfCountryOrCoach.getText();
        createSearchTeam(country);
        //System.out.println(country);
        tfCountryOrCoach.clear();
        tfCountryOrCoach.requestFocus();
    }

    @FXML
    public void btSearchCoachByName(ActionEvent event){
        String nameCoach = tfCountryOrCoach.getText();
        createSearchCoach(nameCoach);
        tfCountryOrCoach.clear();
        tfCountryOrCoach.requestFocus();
    }

    private void createSearchCoach(String nameCoach) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("searchCoachs.fxml"));
            SearchCoachController searchCoachController = new SearchCoachController(nameCoach);
            loader.setController(searchCoachController);
            VBox searchBox = loader.load();

            tpTeamsOrCoachs.getTabs().add(new Tab(nameCoach, searchBox));
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private void createSearchTeam(String country) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("searchTeams.fxml"));
            SearchTeamController searchTeamController = new SearchTeamController(country);
            loader.setController(searchTeamController);
            VBox searchBox = loader.load();

            tpTeamsOrCoachs.getTabs().add(new Tab(country, searchBox));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
    }
}
