module lk.ijse.fxclassproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens lk.ijse.fxclassproject.Controllers to javafx.fxml;
    exports lk.ijse.fxclassproject;
}
