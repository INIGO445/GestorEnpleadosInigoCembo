package com.example.gestorenpleadosinigocembo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Stage stagePrincipal;
    //private Stage dos;
    public HelloApplication() throws IOException {
        stagePrincipal = new Stage();
    }
    @Override
    public void start(Stage stagePrincipal) throws IOException {
        this.stagePrincipal = stagePrincipal;
        mostrarVentanaPrincipal();
    }

    public void mostrarVentanaPrincipal() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stagePrincipal.setTitle("GestorEmpleados");
        stagePrincipal.setScene(scene);
        stagePrincipal.show();
    }
    public void mostrarVentanaEditar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Editar.class.getResource("editar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stagePrincipal.setTitle("GestorEmpleados");
        stagePrincipal.setScene(scene);
        stagePrincipal.show();
    }


    public static void main(String[] args) {
        launch();
    }
}