module fruit.ninja.fruit_ninja_hamdy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens fruit.ninja.fruit_ninja_hamdy to javafx.fxml;
    exports fruit.ninja.fruit_ninja_hamdy;
}