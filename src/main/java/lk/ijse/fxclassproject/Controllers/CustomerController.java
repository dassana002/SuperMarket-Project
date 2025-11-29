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
         String id = idField.getText();
        
        try {
            boolean isResult = custModel.customerDelete(id);

            if(isResult) {
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted successfully!").show();
                cleanFields();
                
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }

    @FXML
    void reset(ActionEvent event) {
        cleanFields();
    }

    @FXML
    void save(ActionEvent event) {
        String name = nameField.getText();
        String address = addressField.getText();
        double salary = Double.parseDouble(salaryField.getText());

        try{
            CustomerDTO cusDTO = new CustomerDTO(name, address, salary);
            boolean isSaved = custModel.customerSave(cusDTO);
            
            if (isSaved) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Customer Saved Successfully!");
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Customer Not Saved!");
                alert.show();
            }

        }catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }

    @FXML
    void update(ActionEvent event) {
        
        String id = idField.getText();
        String name = nameField.getText();
        String address = addressField.getText();
        String salary = salaryField.getText();
        
        try {
            CustomerDTO custdto = new CustomerDTO(Integer.parseInt(id), name, address, Double.parseDouble(salary));
            
            boolean isResult = custModel.customerUpdate(custdto);

            if(isResult) {
                new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully!").show();
                cleanFields();
                
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();

            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }

    @FXML
    void search(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            
            String id = idField.getText();
            
            try {
                CustomerDTO cust = custModel.customerSearch(id);
                
                if(cust != null){
                    nameField.setText(cust.getName());
                    addressField.setText(cust.getAddress());
                    salaryField.setText(String.valueOf(cust.getSalary()));
                                      
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Customer Not Found");
                    alert.show();
                }
                
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void cleanFields() {
    
        idField.setText("");
        nameField.setText("");
        addressField.setText("");
        salaryField.setText("");
        
    }
}
