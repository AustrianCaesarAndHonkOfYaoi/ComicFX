module com.example.comicfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.comicfx to javafx.fxml;
    exports com.example.comicfx;
}