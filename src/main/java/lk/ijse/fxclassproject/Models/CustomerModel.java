/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.fxclassproject.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lk.ijse.fxclassproject.DBConnection.DBConnection;
import lk.ijse.fxclassproject.DTO.CustomerDTO;

/**
 *
 * @author dassa
 */
public class CustomerModel {
    
    public boolean customerSave(CustomerDTO customerdto) throws SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO customer(name, address, salary)VALUES(?,?,?);";
        PreparedStatement pstm = conn.prepareStatement(query);
        
        pstm.setString(1, customerdto.getName());
        pstm.setString(2, customerdto.getAddress());
        pstm.setDouble(3, customerdto.getSalary()   );

        int reset = pstm.executeUpdate();     
       
        return reset > 0;
    }
    
    public boolean customerDelete(String id) throws SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM customer WHERE id=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, Integer.parseInt(id));
        int result = pstm.executeUpdate();
        
        return result > 0;
    }
    
}
