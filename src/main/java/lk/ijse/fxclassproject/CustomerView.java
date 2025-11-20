/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.fxclassproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author dassa
 */
public class CustomerView{
    
    @FXML
    private TextField addressField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField salaryField;

    @FXML
    void dalete(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {
        String name = nameField.getText();
        String address = addressField.getText();
        double salary = Double.parseDouble(salaryField.getText());
        
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Salary: " + salary);
    }

    @FXML
    void update(ActionEvent event) {

    }
    
}
