module lk.ijse.fxclassproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    opens lk.ijse.fxclassproject.DTO to javafx.base;
    opens lk.ijse.fxclassproject.Controllers to javafx.fxml;
    exports lk.ijse.fxclassproject.DTO;
    exports lk.ijse.fxclassproject;

}
