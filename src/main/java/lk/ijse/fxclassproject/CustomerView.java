/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.fxclassproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
       
            Connection conn = DBConnection.getInstance().getConnection();
       
            String sql = "DELETE FROM customer WHERE id=?";
                
            PreparedStatement pstm = conn.prepareStatement(sql);
          
            pstm.setInt(1, Integer.parseInt(id));
                
            int result = pstm.executeUpdate();

            if(result > 0) {
                
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted successfully!").show();
                
                
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
            
            if (reset > 0) {
                System.out.println("Customer Saved");
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Customer Saved Successfully!");
            }else {
                System.out.println("Customer Not Saved");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Customer Not Saved!");
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
       
            Connection conn = DBConnection.getInstance().getConnection();
       
            String sql = "UPDATE customer SET name=?, address=?, salary=? WHERE id=?";
                
            PreparedStatement pstm = conn.prepareStatement(sql);
                
            pstm.setString(1, name);
            pstm.setString(2, address);
            pstm.setDouble(3, Double.parseDouble(salary));
            pstm.setInt(4, Integer.parseInt(id));
                
            int result = pstm.executeUpdate();

            if(result > 0) {
                
                new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully!").show();
                
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
                Connection conn = DBConnection.getInstance().getConnection();
                String query = "SELECT * FROM customer WHERE id = ?";
                PreparedStatement ptsm = conn.prepareStatement(query);
                ptsm.setInt(1, Integer.parseInt(id));
                ResultSet rs = ptsm.executeQuery();
                
                if(rs.next()){
                    int custId = rs.getInt("id");
                    String custName = rs.getString("name");
                    String address = rs.getString("address");
                    double salary = rs.getDouble("salary");
                    
                    nameField.setText(custName);
                    addressField.setText(address);
                    salaryField.setText(String.valueOf(salary));
                    
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
    
}
