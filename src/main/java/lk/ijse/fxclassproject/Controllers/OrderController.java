package lk.ijse.fxclassproject.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.fxclassproject.DTO.CustomerDTO;
import lk.ijse.fxclassproject.DTO.ItemDTO;
import lk.ijse.fxclassproject.DTO.OrderItemTM;
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

    @FXML
    private TableColumn<OrderItemTM, String> col_Name;

    @FXML
    private TableColumn<OrderItemTM, Integer> col_QTY;

    @FXML
    private TableColumn<OrderItemTM, Double> col_total_Price;

    @FXML
    private TableColumn<OrderItemTM, Double> col_unit_Price;

    @FXML
    private TableView<OrderItemTM> orderTable;

    @FXML
    private TextField qty_value;

    @FXML
    private Label total_value;

    private final CustomerModel customerModel =  new CustomerModel();
    private final ItemModel itemModel = new ItemModel();
    private final ObservableList<OrderItemTM> orderItemObList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_Name.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        col_QTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        col_unit_Price.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        col_total_Price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

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

    private void loadOrderItemTbl() {
        orderTable.setItems(orderItemObList);
        calcOrderTotal();
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

    @FXML
    void addToCart(ActionEvent event) {
        Number selectedId = combo_ItemId.getSelectionModel().getSelectedItem();

        int itemId = selectedId.intValue();
        String itemName = lable_itemName.getText();
        String itemPrice = lable_unitprice_item.getText();
        String qty = qty_value.getText();
        double totalPrice = Double.parseDouble(lable_unitprice_item.getText());

        OrderItemTM orderItemTM = new OrderItemTM(itemId, itemName, Double.parseDouble(itemPrice), Integer.parseInt(qty), totalPrice);
        orderItemObList.add(orderItemTM);

        loadOrderItemTbl();
    }

    private void calcOrderTotal() {
        double total = 0.0;

        for (OrderItemTM orderItemTM : orderItemObList) {
            total += orderItemTM.getTotalPrice();
        }

        total_value.setText(String.valueOf(total));
    }
}
