/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.fxclassproject.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.fxclassproject.DBConnection.DBConnection;
import lk.ijse.fxclassproject.DTO.CustomerDTO;
import lk.ijse.fxclassproject.Models.CustomerModel;

/**
 * FXML Controller class
 *
 * @author dassa
 */
public class CustomerView{
    
    @FXML
    private TextField idField;
    
    @FXML
    private TextField addressField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField salaryField;

    @FXML
    void dalete(ActionEvent event) {
         String id = idField.getText();
        
        try {
            
            CustomerModel customerModel = new CustomerModel();
            boolean isResult = customerModel.customerDelete(id);

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
            CustomerModel customerModel = new CustomerModel();
            CustomerDTO cusDTO = new CustomerDTO(name, address, salary);
            boolean isSaved = customerModel.customerSave(cusDTO);
            
            if (isSaved) {
                System.out.println("Customer Saved");
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Customer Saved Successfully!");
                alert.show();
            }else {
                System.out.println("Customer Not Saved");

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
       
            CustomerModel custModel = new CustomerModel();
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
                CustomerModel custModel = new CustomerModel();
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
