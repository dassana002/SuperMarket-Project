/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.fxclassproject;

/**
 *
 * @author dassa
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
   
    // Database Established
    private final static String DB_URL = "jdbc:mysql://localhost:3306/supermarket";
    private final static String DB_USERNAME = "root";
    private final static String DB_PASSWORD = "DAka@24381";
   
    private static Connection connection; 
    private static DBConnection dbconnection;
   
    private DBConnection() throws SQLException {    
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
   
    //Single Connection Create Process
    public static DBConnection getInstance() throws SQLException{
        if(dbconnection == null){
            return new DBConnection();
        }
        return dbconnection;
    }
   
    // Can get Connection
    public Connection getConnection() throws SQLException {
        return connection;
    }
}
