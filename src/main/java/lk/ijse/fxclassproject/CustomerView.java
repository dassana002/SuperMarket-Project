/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.fxclassproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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

        try{
            Connection conn = DBConnection.getInstance().getConnection();
            String query = "INSERT INTO customer(name, address, salary)VALUES(?,?,?);";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, name);
            pstm.setString(2, address);
            pstm.setDouble(3, salary);

            int reset = pstm.executeUpdate();
            System.out.println(reset);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void update(ActionEvent event) {

    }
    
}
