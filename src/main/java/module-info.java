module com.example.gestorenpleadosinigocembo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.gestorenpleadosinigocembo to javafx.fxml;
    exports com.example.gestorenpleadosinigocembo;
}