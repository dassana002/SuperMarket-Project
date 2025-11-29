/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.fxclassproject.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.fxclassproject.DBConnection.DBConnection;
import lk.ijse.fxclassproject.DTO.CustomerDTO;
import lk.ijse.fxclassproject.Utility.CrudUtil;

/**
 *
 * @author dassa
 */
public class CustomerModel {
    
    public boolean customerSave(CustomerDTO customerdto) throws SQLException{
        String query = "INSERT INTO customer(name, address, salary)VALUES(?,?,?);";      
        return CrudUtil.execute(query, customerdto.getName(), customerdto.getAddress(), customerdto.getSalary());
    }
    
    public boolean customerDelete(String id) throws SQLException{
        String sql = "DELETE FROM customer WHERE id=?";
        return CrudUtil.execute(sql, id);
    }
    
    public boolean customerUpdate(CustomerDTO customerdto) throws SQLException{
        String sql = "UPDATE customer SET name=?, address=?, salary=? WHERE id=?";
        return CrudUtil.execute(sql, customerdto.getName(), customerdto.getAddress(), customerdto.getSalary());
    }
    
    public CustomerDTO customerSearch(String id) throws SQLException{
        String query = "SELECT * FROM customer WHERE id = ?";
        
        ResultSet rs = CrudUtil.execute(query, id);
        
        CustomerDTO customerDTO = null;
        
        if(rs.next()){
            return customerDTO = new CustomerDTO(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getDouble("salary")
            );
        }
        
        return null;
    }
    
    public ArrayList<CustomerDTO> customerAll () throws SQLException {
        String query = "SELECT * FROM customer";
        
        ResultSet rs = CrudUtil.execute(query);
        
        ArrayList<CustomerDTO> custdtos = new ArrayList<>();
        
        while(rs.next()) {
            CustomerDTO custdto = new CustomerDTO(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getDouble("salary")
            );
            
            custdtos.add(custdto);
        }
        
        return custdtos;
    }
}
