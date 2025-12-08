package lk.ijse.fxclassproject.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import lk.ijse.fxclassproject.DTO.CustomerDTO;
import lk.ijse.fxclassproject.DTO.ItemDTO;
import lk.ijse.fxclassproject.Models.CustomerModel;
import lk.ijse.fxclassproject.Models.ItemModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML
    private Label addressLable;

    @FXML
    private ComboBox<Number> combo_ItemId;

    @FXML
    private ComboBox<Number> combo_customerID;

    @FXML
    private Label lable_AvaQTY_item;

    @FXML
    private Label lable_itemName;

    @FXML
    private Label lable_unitprice_item;

    @FXML
    private Label nameLable;

    @FXML
    private Label salaryLable;

    private CustomerModel customerModel =  new CustomerModel();
    private ItemModel itemModel = new ItemModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboCustomerID();
        loadComboItemID();
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

    private void loadComboItemID() {
        try {
            List<ItemDTO> itemList = itemModel.itemAll();

            ObservableList<Number> itemIdList = FXCollections.observableArrayList();

            for (ItemDTO itemDTO : itemList) {
                itemIdList.add(itemDTO.getId());
            }
            combo_ItemId.setItems(itemIdList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleSelectComboCustomerId(ActionEvent event) {
        try {
            Number selectedId  = combo_customerID.getSelectionModel().getSelectedItem();
            int selectCustomerId = selectedId.intValue();

            CustomerDTO customerDTO = customerModel.customerSearch(String.valueOf(selectCustomerId));

            if(customerDTO != null){
                nameLable.setText(customerDTO.getName());
                addressLable.setText(customerDTO.getAddress());
                salaryLable.setText(String.valueOf( customerDTO.getSalary()));
            }else {
                new Alert(Alert.AlertType.ERROR, "Customer Not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleSelectComboItemId(ActionEvent event) {
        try {
            Number selectedId  = combo_ItemId.getSelectionModel().getSelectedItem();
            int selectItemId = selectedId.intValue();

            ItemDTO itemDTO = itemModel.itemSearch(String.valueOf(selectItemId));

            if (itemDTO != null) {
                lable_itemName.setText(itemDTO.getName());
                lable_AvaQTY_item.setText(String.valueOf(itemDTO.getQty()));
                lable_unitprice_item.setText(String.valueOf(itemDTO.getUnit_price()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
