/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.fxclassproject.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lk.ijse.fxclassproject.DBConnection.DBConnection;

/**
 *
 * @author dassa
 */
public class CustomerModel {
    
    public boolean customerSave(String name, String address, double salary) throws SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO customer(name, address, salary)VALUES(?,?,?);";
        PreparedStatement pstm = conn.prepareStatement(query);
        
        pstm.setString(1, name);
        pstm.setString(2, address);
        pstm.setDouble(3, salary);

        int reset = pstm.executeUpdate();     
       
        return reset > 0;
    }
}
