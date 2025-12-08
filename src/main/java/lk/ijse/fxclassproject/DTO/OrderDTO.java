package lk.ijse.fxclassproject.DTO;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private int id;
    private int customerID;
    private Date orderDate;
    private List<OrderItemDTO> orderItemDTOList;

    public OrderDTO() {
    }

    public OrderDTO(int customerID, Date orderDate, List<OrderItemDTO> orderItemDTOList) {
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.orderItemDTOList = orderItemDTOList;
    }

    public OrderDTO(int id, int customerID, Date orderDate, List<OrderItemDTO> orderItemDTOList) {
        this.id = id;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.orderItemDTOList = orderItemDTOList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItemDTO> getOrderItemDTOList() {
        return orderItemDTOList;
    }

    public void setOrderItemDTOList(List<OrderItemDTO> orderItemDTOList) {
        this.orderItemDTOList = orderItemDTOList;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", customerID=" + customerID +
                ", orderDate=" + orderDate +
                ", orderItemDTOList=" + orderItemDTOList +
                '}';
    }
}
