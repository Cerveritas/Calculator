module com.example.calculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;
    requires rhino;


    opens com.example.calculator to javafx.fxml;
    exports com.example.calculator;
}