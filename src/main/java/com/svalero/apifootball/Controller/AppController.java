package com.svalero.apifootball.Controller;

import com.svalero.apifootball.App;
import io.reactivex.functions.Consumer;
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
    private TextField tfCountry;
    @FXML
    private TabPane tpCountries;
    @FXML
    private Button btSearchCountry;


    public AppController(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tpCountries.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

    }

    //Metodo para cuando pulsemo el botton
    @FXML
    public void btSearchCountry (ActionEvent event) {
        String country = tfCountry.getText();
        createSearch(country);
        //System.out.println(country);
        tfCountry.clear();
        tfCountry.requestFocus();
    }

    private void createSearch(String country) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
            SearchController searchController = new SearchController(country);
            loader.setController(searchController);
            VBox searchBox = loader.load();

            tpCountries.getTabs().add(new Tab(country, searchBox));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
