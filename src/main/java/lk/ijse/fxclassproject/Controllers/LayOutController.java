package lk.ijse.fxclassproject.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.fxclassproject.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LayOutController implements Initializable {
    @FXML
    private AnchorPane mainContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("LayOut Controller");
    }

    @FXML
    private void clickCustomerNav() throws IOException {
        Parent customerFXML = App.loadFXML("Customer");
        mainContent.getChildren().setAll(customerFXML);
    }

    @FXML
    private void clickItemNav() throws IOException {
        Parent customerFXML = App.loadFXML("Item");
        mainContent.getChildren().setAll(customerFXML);
    }

    @FXML
    private void clickOrderNav() throws IOException {
        Parent customerFXML = App.loadFXML("Order");
        mainContent.getChildren().setAll(customerFXML);
    }
}
