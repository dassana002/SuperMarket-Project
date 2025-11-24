/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.fxclassproject.DTO;

/**
 *
 * @author dassa
 */
public class CustomerDTO {
    
    private int id;
    private String name;
    private String address;
    private double salary;

    public CustomerDTO() {
    }

    public CustomerDTO(String name, String address, double salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }
    
    public CustomerDTO(int id, String name, String address, double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" + "id=" + id + ", name=" + name + ", address=" + address + ", salary=" + salary + '}';
    }
    
}
