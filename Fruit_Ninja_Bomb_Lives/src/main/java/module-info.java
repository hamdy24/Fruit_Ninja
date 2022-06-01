module com.example.fruitninjahassan {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.media;
    opens com.example.fruitninjahassan to javafx.fxml;
    exports com.example.fruitninjahassan;
}