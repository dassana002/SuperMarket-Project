/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.fxclassproject;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author dassa
 */
public class LoginController {
    @FXML
    private TextField passwordField;

    @FXML
    private TextField userNameField;
    
    @FXML
    private void login() throws IOException{
        String realUserName = "Dassana";
        String realPassword = "1234";
        
        String username = userNameField.getText();
        String password = passwordField.getText();
        
        if(username.equals(realUserName) & password.equals(realPassword)){
            App.setRoot("primary");
        }
        
    }
}
