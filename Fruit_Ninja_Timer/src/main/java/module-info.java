module fruit.ninja.fruit_ninja_hamdy {
    requires javafx.controls;
    requires javafx.fxml;


    opens fruit.ninja.fruit_ninja_hamdy to javafx.fxml;
    exports fruit.ninja.fruit_ninja_hamdy;
}