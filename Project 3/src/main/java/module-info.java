module com.example.project_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.project_3 to javafx.fxml;
    exports com.example.project_3;
}