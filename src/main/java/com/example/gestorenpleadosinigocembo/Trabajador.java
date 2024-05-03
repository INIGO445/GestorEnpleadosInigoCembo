package com.example.gestorenpleadosinigocembo;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class Trabajador implements Initializable {
    public TextField SalarioTxt;
    public TextField NombreTxt;
    public Button InsertarBTN;
    public ComboBox<String> PrestosCombo;
    @FXML
    private Label welcomeText;
    @FXML
    private String Nombre;
    @FXML
    private String Puesto;
    @FXML
    private int Salario;
    //private final Date Fecha_Alta = ;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PrestosCombo.setItems(FXCollections.observableArrayList("Scada Manager", "Sales Manager", "Product Owner", "Product Manager", "Analyst Programmer", "Junior Programmer"));
    }
    public void insertar(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Mensaje");
        alert.setContentText(STR."Empleado \{NombreTxt.getText()} introducido en la base de datos satisfactoriamente");
        alert.setTitle("HECHO");
        alert.show();
    }
}