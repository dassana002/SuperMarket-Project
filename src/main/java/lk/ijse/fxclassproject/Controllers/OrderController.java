package lk.ijse.fxclassproject.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import lk.ijse.fxclassproject.DTO.CustomerDTO;
import lk.ijse.fxclassproject.Models.CustomerModel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML
    private ComboBox<Number> combo_customerID;
    private CustomerModel customerModel =  new CustomerModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboCustomerID();
    }

    private void loadComboCustomerID() {
        try {
            List<CustomerDTO> customerList = customerModel.customerAll();
            ObservableList<Number> customerIdList = FXCollections.observableArrayList();

            for (CustomerDTO customerDTO : customerList) {
                customerIdList.add(customerDTO.getId());
            }
            combo_customerID.setItems(customerIdList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
