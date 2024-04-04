package com.svalero.apifootball.Controller;

import com.svalero.apifootball.Model.Coach;
import com.svalero.apifootball.Model.Team;
import com.svalero.apifootball.Task.CoachTask;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.ResourceBundle;

public class SearchCoachController implements Initializable {

    @FXML
    private Label lbStatus;
    @FXML
    private ProgressBar pbProgress;
    @FXML
    private TableView<Coach> coachTableView;

    private ObservableList<Coach> infoCoach;
    private String nameCoach;
    private CoachTask coachTask;

    public SearchCoachController (String nameCoach){
        this.nameCoach = nameCoach;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.infoCoach = FXCollections.observableArrayList();
        coachTask = new CoachTask(nameCoach, infoCoach);

        pbProgress.progressProperty().unbind();
        pbProgress.progressProperty().bind(coachTask.progressProperty());

        coachTask.stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("The search has finished");
                alert.show();
            }
        });

        coachTask.setOnSucceeded(event -> {
            lbStatus.setText("The search has finished");
        });

        coachTask.messageProperty().addListener((observable, oldValue, newValue) -> {
            lbStatus.setText(newValue);
        });

        //Obtenemos la lista de datos del coach y se los pasamos al tableview
        coachTableView.setItems(infoCoach);

        //Configuramos las columnas de la Tableview, nombre de la columna y luego el dato que queremos, ene ste caso de la clase Coach y el name
        TableColumn<Coach, String> nameColumn = new TableColumn<>("Nombre");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        //Esto simplemente es una manera de centrar vertical y horizontalmente los textos dentro de un TableView
        nameColumn.setCellFactory(col -> new TableCell<Coach, String>() {
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

        TableColumn<Coach, String> lastNameColumn = new TableColumn<>("Apellido");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        lastNameColumn.setCellFactory(col -> new TableCell<Coach, String>() {
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

        TableColumn<Coach, Integer> ageColumn = new TableColumn<>("Edad");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        ageColumn.setCellFactory(col -> new TableCell<Coach, Integer>() {
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

        TableColumn<Coach, String> nationalityColumn = new TableColumn<>("Nacionalidad");
        nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        nationalityColumn.setCellFactory(col -> new TableCell<Coach, String>() {
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

        TableColumn<Coach, String> currentTeamColumn = new TableColumn<>("Equipo actual");
        currentTeamColumn.setCellValueFactory(cellData -> {
            // Obtenemos el coach de la fila actual
            Coach coach = cellData.getValue();
            // Si el coach es null o no tiene un equipo asignado, devolvemos un valor vacío
            if (coach == null || coach.getTeam() == null) {
                return new SimpleStringProperty("");
            } else {
                // Si el coach tiene un equipo asignado, devolvemos el nombre del equipo
                return new SimpleStringProperty(coach.getTeam().getName());
            }
        });
        currentTeamColumn.setCellFactory(col -> new TableCell<>() {
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

        // Asegurar que la URL de la imagen sea válida
        TableColumn<Coach, Image> coachImageColumn = new TableColumn<>("Imagen");
        coachImageColumn.setCellValueFactory(cellData -> {
            String imageUrl = cellData.getValue().getPhoto();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Image image = new Image(imageUrl);
                return new SimpleObjectProperty<>(image);
            } else {
                return new SimpleObjectProperty<>(null);
            }
        });
        coachImageColumn.setCellFactory(col -> new TableCell<Coach, Image>() {
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

        // Ajustamos el ancho de algunas columnas para que se vea toda la informacion del coach.
        nameColumn.setPrefWidth(100);
        lastNameColumn.setPrefWidth(180);
        currentTeamColumn.setPrefWidth(100);
        coachImageColumn.setPrefWidth(180);

        //Añadimos todas estas columnas y su informacion al tableView
        coachTableView.getColumns().addAll(nameColumn, lastNameColumn, ageColumn, nationalityColumn, currentTeamColumn ,coachImageColumn);

        new Thread(coachTask).start();
    }
}
