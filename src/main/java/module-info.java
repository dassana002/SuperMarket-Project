module lk.ijse.fxclassproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens lk.ijse.fxclassproject to javafx.fxml;
    exports lk.ijse.fxclassproject;
}
