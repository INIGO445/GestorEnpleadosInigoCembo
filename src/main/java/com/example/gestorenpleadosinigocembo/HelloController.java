package com.example.gestorenpleadosinigocembo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelloController implements Initializable {
    @FXML
    public TextField SalarioTxt;
    @FXML
    public TextField NombreTxt;
    @FXML
    public Button InsertarBTN;
    @FXML
    public ComboBox<String> PrestosCombo;
    @FXML
    public ListView<String> ListaDeEmpleadosVista;
    @FXML
    public Button RefrescarBtn;
    @FXML
    public Button EditarBtn;
    @FXML
    public Button EliminarBtn;
    @FXML
    public Label NombreLabel;
    @FXML
    public Label PuestoLabel;
    @FXML
    public Label SalarioLabel;
    @FXML
    public Label FechaLabel;
    @FXML
    public Label IDLabel;
    public Button basesBtn;

    @FXML

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PrestosCombo.setItems(FXCollections.observableArrayList("Scada Manager", "Sales Manager", "Product Owner", "Product Manager", "Analyst Programmer", "Junior Programmer"));
        ListaDeEmpleadosVista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String empelados = ListaDeEmpleadosVista.getSelectionModel().getSelectedItem();
                datos(empelados);
            }
        });
    }
    public void avisoI()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Mensaje");
        alert.setContentText(STR."Empleado \{NombreTxt.getText()} introducido en la base de datos satisfactoriamente");
        alert.setTitle("HECHO");
        alert.show();
    }
    public void empelado()
    {
        if (NombreTxt.getText().isEmpty() || SalarioTxt.getText().isEmpty() || PrestosCombo.getValue() == null)
        {
            llenar();
        }
        else
        {
            inserccion();
            avisoI();
        }
    }
    public void llenar()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText("Si no rellena los campos no se puede añadir a la lista de los trabajadores");
        alert.setTitle("IMPOSIBLE AÑADIR TRABAJADOR");
        alert.show();
    }
    public void inserccion() {
        Connection miConexion = null;
        try {
            miConexion = conexionBasedDeDatos();
            PreparedStatement statmen = miConexion.prepareStatement("INSERT INTO EMPLEADO (NOMBRE, PUESTO, SALARIO, FECHA) VALUES (?,?,?,NOW())");
            statmen.setString(1, NombreTxt.getText());
            statmen.setString(2, PrestosCombo.getValue());
            statmen.setInt(3, Integer.parseInt(SalarioTxt.getText()));
            statmen.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No funciona la conexion");
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
    public Connection conexionBasedDeDatos()  {
        String url = "jdbc:mysql://localhost:3306/gestortrabajadores";
        String usuario = "root";
        String contrasenya = "root";
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(url, usuario, contrasenya);
            if (conexion != null) {
                return conexion;
            }
        } catch (SQLException e) {
            System.out.println("Error al acceder");
        }
        return null;
    }
    @FXML
    public void linea() {
        File miFichero = new File("src/main/java/Modelo/ArchivosTXT/trabajadores.txt");
        try {
            Connection miConexion = null;
            miConexion = conexionBasedDeDatos();
            PreparedStatement statmen = miConexion.prepareStatement("INSERT INTO EMPLEADO (NOMBRE, PUESTO, SALARIO, FECHA) VALUES (?,?,?,NOW())");
            Scanner miScaner = new Scanner(miFichero);
            while (miScaner.hasNext())
            {
                String[] trabajador;
                trabajador = miScaner.nextLine().split(";");
                String Nombre =trabajador[0];
                String Puesto =trabajador[1];
                int Salario = Integer.parseInt(trabajador[2]);
                statmen.setString(1, Nombre);
                statmen.setString(2, Puesto);
                statmen.setInt(3, Salario);
                statmen.executeUpdate();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<String> entradaDeNombres()
    {
        ArrayList<String> nombres = new ArrayList<>();
        try {
            Connection conect = conexionBasedDeDatos();
            Statement miStatment = conect.createStatement();
            ResultSet resul = miStatment.executeQuery("SELECT NOMBRE FROM Empleado");
            while (resul.next())
            {
                nombres.add(resul.getString("NOMBRE"));
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar");
        }
        return nombres;
    }
    public void eleccionEliminar(String nombre)
    {
        try {
            Connection connection = conexionBasedDeDatos();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM EMPLEADO WHERE NOMBRE = ?");
            preparedStatement.setString(1, nombre);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al Borrar");
        }
    }
    public void eliminacion()
    {
        eleccionEliminar(ListaDeEmpleadosVista.getSelectionModel().getSelectedItem());
    }
    public void refrescar()
    {
        try
        {
            Connection connection = conexionBasedDeDatos();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT NOMBRE FROM EMPLEADO");
            ListaDeEmpleadosVista.getItems().clear();
            while (resultSet.next())
            {
                ListaDeEmpleadosVista.getItems().add(resultSet.getString("NOMBRE"));
            }
        } catch (SQLException e) {
            System.out.println("Error al refrescar");
        }
    }
    public void datos(String nombre)
    {
        try {
            Connection connection = conexionBasedDeDatos();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EMPLEADO where NOMBRE = ?");
            preparedStatement.setString(1, nombre);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
            IDLabel.setText(resultSet.getString("ID"));
            NombreLabel.setText(resultSet.getString("NOMBRE"));
            PuestoLabel.setText(resultSet.getString("PUESTO"));
            SalarioLabel.setText(resultSet.getString("SALARIO"));
            FechaLabel.setText(resultSet.getString("FECHA"));
            }
        } catch (SQLException e) {
            System.out.println("Error al Seleccionar");
        }
    }
}