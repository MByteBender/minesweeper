module com.example.minesweeper {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.ehvp.minesweeper to javafx.fxml;
    exports com.ehvp.minesweeper;
}