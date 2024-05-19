package com.example.gestorenpleadosinigocembo;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Editar implements Initializable {
    public Button InsertarBTN;
    public TextField SalarioTxt;
    public ComboBox<String> PrestosCombo;
    public TextField NombreTxt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PrestosCombo.setItems(FXCollections.observableArrayList("Scada Manager", "Sales Manager", "Product Owner", "Product Manager", "Analyst Programmer", "Junior Programmer"));

    }
    public static void cerrarVentana(ActionEvent e) {
        Node source = (Node) e.getSource();     //Me devuelve el elemento al que hice click
        Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
        stage.close();
    }
    public void cambiar(ActionEvent event)
    {
        if (conexion())
        {
            cerrarVentana(event);
        }
        else
        {
            fallo();
        }
    }
    public boolean conexion()
    {
        String url = "jdbc:mysql://localhost:3306/gestortrabajadores";
        String usuario = "root";
        String contrasenya = "root";
        Connection miConexion = null;
        try {
            miConexion = DriverManager.getConnection(url, usuario, contrasenya);
            PreparedStatement statmen = miConexion.prepareStatement("UPDATE EMPLEADO SET NOMBRE = ?, PUESTO = ?, SALARIO = ? WHERE NOMBRE = ?;");
            statmen.setString(1, NombreTxt.getText());
            statmen.setString(2, PrestosCombo.getValue());
            statmen.setDouble(3, Double.parseDouble(SalarioTxt.getText()));
            statmen.setString(4, NombreTxt.getText());
            statmen.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("No funciona la conexion");
            return false;
        } finally {
            if (miConexion != null) {
                try {
                    miConexion.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar");
                }
            }
        }
    }
    public void fallo()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText("Si no rellena los campos no se puede añadir a la lista de los trabajadores o el fromato es erroneo");
        alert.setTitle("IMPOSIBLE ACTUALIZAR TRABAJADOR");
        alert.show();
    }
}
