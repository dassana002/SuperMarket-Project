/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.fxclassproject.Controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.fxclassproject.DTO.CustomerDTO;
import lk.ijse.fxclassproject.Models.CustomerModel;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author dassa
 */
public class CustomerController implements Initializable{
    
    @FXML
    private TextField idField;
    
    @FXML
    private TextField addressField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField salaryField;
    
    @FXML
    private TableView tableCustomer;
    
    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colID;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colSalary;
    
    private final CustomerModel custModel = new CustomerModel();

    //Regex using
    private final String CUSTOMER_ID_REGEX = "^[0-9]+$";
    private final String CUSTOMER_NAME_REGEX = "^[A-Za-z]{3,}$";
    private final String CUSTOMER_ADDRESS_REGEX = "^[A-Za-z0-9]{5,}$";
    private final String CUSTOMER_SALARY_REGEX = "^[0-9]{3,}(\\.[0-9]?)$";

    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("Customer FXML");
        // link by between col name and attribute
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        
        loadCustomerTable();
    }
    
    @FXML 
    private void loadCustomerTable() {
        
        try {
            List<CustomerDTO> customerList = custModel.customerAll();

            ObservableList<CustomerDTO> obList = FXCollections.observableArrayList();

            for (CustomerDTO customerDTO : customerList) {
                obList.add(customerDTO);
            }
            
            tableCustomer.setItems(obList);
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    } 

    @FXML
    void dalete(ActionEvent event) {
        try {

            String id = idField.getText();

            if(!id.matches(CUSTOMER_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid ID").show();
            } else {

                boolean result = custModel.customerDelete(id);

                if(result) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer deleted successfully!").show();
                    cleanFields();
                    loadCustomerTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    @FXML
    void reset(ActionEvent event) {
        cleanFields();
    }

    @FXML
    void save(ActionEvent event) {
        String name = nameField.getText().trim();
        String salary = salaryField.getText().trim();
        String address = addressField.getText().trim();

        if(!name.matches(CUSTOMER_NAME_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer name").show();
        } else if(!address.matches(CUSTOMER_ADDRESS_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer address").show();
        } else if(!salary.matches(CUSTOMER_SALARY_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer salary").show();
        } else {
            try {

                CustomerDTO cusDTO = new CustomerDTO(name, address, Double.parseDouble(salary));
                boolean result = custModel.customerSave(cusDTO);

                if(result) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer saved successfully!").show();
                    cleanFields();
                    loadCustomerTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                }
            } catch(Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        }
    }

    @FXML
    void update(ActionEvent event) {

        try {

            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String salary = salaryField.getText().trim();
            String address = addressField.getText().trim();

            if(!id.matches(CUSTOMER_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid customer id").show();
            } else if(!name.matches(CUSTOMER_NAME_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid customer name").show();
            } else if(!address.matches(CUSTOMER_ADDRESS_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid customer address").show();
            } else if(!salary.matches(CUSTOMER_SALARY_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid customer salary").show();
            } else {

                CustomerDTO customerDTO = new CustomerDTO(Integer.parseInt(id), name, address, Double.parseDouble(salary));
                boolean result = custModel.customerUpdate(customerDTO);

                if(result) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully!").show();
                    cleanFields();
                    loadCustomerTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    @FXML
    void search(KeyEvent event) {
        try {
            if(event.getCode() == KeyCode.ENTER) {

                String id = idField.getText();

                if(!id.matches(CUSTOMER_ID_REGEX)) {
                    new Alert(Alert.AlertType.ERROR, "Invalid ID").show();
                } else {

                    CustomerDTO customerDTO = custModel.customerSearch(id);

                    if(customerDTO!=null) {
                        nameField.setText(customerDTO.getName());
                        addressField.setText(customerDTO.getAddress());
                        salaryField.setText(String.valueOf(customerDTO.getSalary()));
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Customer not found!").show();
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }
    
    private void cleanFields() {
    
        idField.setText("");
        nameField.setText("");
        addressField.setText("");
        salaryField.setText("");
        
    }
}
