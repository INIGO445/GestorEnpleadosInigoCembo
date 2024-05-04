package com.example.gestorenpleadosinigocembo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    private final ListaDeTrabajadores miLista = new ListaDeTrabajadores();
    @FXML
    public ListView<String> ListaDeEmpleadosVista;
    @FXML
    public Button RefrescarBtn;
    @FXML
    public Button EditarBtn;
    @FXML
    public Button EliminarBtn;
    @FXML

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PrestosCombo.setItems(FXCollections.observableArrayList("Scada Manager", "Sales Manager", "Product Owner", "Product Manager", "Analyst Programmer", "Junior Programmer"));
    }
    public void insertar()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Mensaje");
        alert.setContentText(STR."Empleado \{NombreTxt.getText()} introducido en la base de datos satisfactoriamente");
        alert.setTitle("HECHO");
        alert.show();
    }
    public void empelado()
    {
        if (NombreTxt.getText().isEmpty() || SalarioTxt.getText().isEmpty() || PrestosCombo.getItems().isEmpty())
        {
            llenar();
        }
        else
        {
            insertar();
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
    public void conexionBasedDeDatos()  {
        String url = "jdbc:mysql://localhost:3306/gestortrabajadores";
        String usuario = "root";
        String contrasenya = "root";
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(url, usuario, contrasenya);
            if (conexion != null) {
            }
        } catch (SQLException e) {
            System.out.println("Error al acceder");
        }
        finally {
            if (conexion != null)
            {
                try {
                    conexion.close();
                }
                catch (SQLException e)
                {
                    System.out.println("Error al cerrar");
                }
            }
        }
    }
    public void linea() {
        File miFichero = new File("src/main/resources/com/example/gestorenpleadosinigocembo/ArchivosTXT/trabajadores.txt");
        try {
            Scanner miScaner = new Scanner(miFichero);
            while (miScaner.hasNext())
            {
                String[] trabajador;
                trabajador = miScaner.nextLine().split(";");
                String Nombre =trabajador[0];
                String Puesto =trabajador[1];
                int Salario = Integer.parseInt(trabajador[2]);
                System.out.println(Nombre + Puesto + Salario);
                new Trabajador(Nombre,Puesto,Salario);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo");
        }
    }
}