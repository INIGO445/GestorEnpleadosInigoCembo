module com.example.gestorenpleadosinigocembo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gestorenpleadosinigocembo to javafx.fxml;
    exports com.example.gestorenpleadosinigocembo;
}