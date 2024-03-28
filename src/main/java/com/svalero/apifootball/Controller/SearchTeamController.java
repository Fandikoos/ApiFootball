package com.svalero.apifootball.Controller;

import com.svalero.apifootball.Model.Team;
import com.svalero.apifootball.Task.TeamTask;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class SearchTeamController implements Initializable {

    @FXML
    private Label lbStatus;
    @FXML
    private ProgressBar pbProgress;
    @FXML
    private TableView<Team> teamTableView;
    @FXML
    private Button orderByFoundation;
    @FXML
    private Label infoSort;

    private ObservableList<Team> teamInfo;
    private String country;
    private TeamTask teamTask;
    private boolean ascendingOrder = true;

    public SearchTeamController(String country) {
        this.country = country;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println(country + "searchController");
        this.teamInfo = FXCollections.observableArrayList();
        teamTask = new TeamTask(country, teamInfo);

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
            lbStatus.setText("The search has finished");
        });

        teamTableView.setItems(teamInfo);


        //Actualizamos la barra de tareas
        teamTask.messageProperty().addListener((observable, oldValue, newValue) -> {
            lbStatus.setText(newValue);
        });

        // Configurar las columnas de la TableView
        TableColumn<Team, String> nameColumn = new TableColumn<>("Nombre");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setCellFactory(col -> new TableCell<>() {
            {
                setContentDisplay(ContentDisplay.CENTER);
                setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
            }
        });

        TableColumn<Team, Integer> foundationColumn = new TableColumn<>("Fundación");
        foundationColumn.setCellValueFactory(new PropertyValueFactory<>("founded"));
        foundationColumn.setCellFactory(col -> new TableCell<>() {
            {
                setContentDisplay(ContentDisplay.CENTER);
                setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item != null ? item.toString() : "");
            }
        });

        TableColumn<Team, String> countryColumn = new TableColumn<>("País");
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        countryColumn.setCellFactory(col -> new TableCell<>() {
            {
                setContentDisplay(ContentDisplay.CENTER);
                setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
            }
        });


        TableColumn<Team, String> codeColumn = new TableColumn<>("Codigo");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        codeColumn.setCellFactory(col -> new TableCell<>() {
            {
                setContentDisplay(ContentDisplay.CENTER);
                setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item);
            }
        });

        TableColumn<Team, Image> teamImageColumn = new TableColumn<>("Imagen");
        teamImageColumn.setCellValueFactory(cellData -> {
            String imageUrl = cellData.getValue().getLogo();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Image image = new Image(imageUrl);
                return new SimpleObjectProperty<>(image);
            } else {
                return new SimpleObjectProperty<>(null);
            }
        });
        teamImageColumn.setCellFactory(col -> new TableCell<>() {
            private final ImageView imageView = new ImageView();

            {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
            }

            @Override
            public void updateItem(Image image, boolean empty) {
                super.updateItem(image, empty);
                if (empty || image == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(image);
                    setGraphic(imageView);
                }
            }
        });

        // Ajustar el ancho de las columnas
        teamImageColumn.setPrefWidth(150);

        // Añadir las columnas a la TableView
        teamTableView.getColumns().addAll(nameColumn, foundationColumn, countryColumn, codeColumn, teamImageColumn);


        new Thread(teamTask).start();

    }

    @FXML
    private void sortByFoundation(){
        if (ascendingOrder) {
            infoSort.setText("Orden actual: de viejo a nuevo");
            teamInfo.sort(Comparator.comparingInt(Team::getFounded));
        } else {
            teamInfo.sort(Comparator.comparingInt(Team::getFounded).reversed());
            infoSort.setText("Orden actual: de nuevo a viejo");
        }
        ascendingOrder = !ascendingOrder;
        teamTableView.setItems(teamInfo);
    }

}
