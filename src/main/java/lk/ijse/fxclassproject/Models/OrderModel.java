package lk.ijse.fxclassproject.Models;

import lk.ijse.fxclassproject.DBConnection.DBConnection;
import lk.ijse.fxclassproject.DTO.OrderDTO;
import lk.ijse.fxclassproject.Utility.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {

    private final OrderItemModel orderItemModel = new OrderItemModel();

    public boolean placeOrder(OrderDTO orderDTO) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        try {

            conn.setAutoCommit(false);

            // orders table
            boolean result = CrudUtil.execute(
                    "INSERT INTO orders (date, customer_id) VALUES (?,?)",
                    orderDTO.getOrderDate(),
                    orderDTO.getCustomerID()
            );

            // order_items table
            if(result) {
                ResultSet rs = CrudUtil.execute("SELECT id FROM orders ORDER BY id DESC LIMIT 1");
                if(rs.next()) {
                    int orderId = rs.getInt("id");
                    boolean result2 = orderItemModel.saveOrderItems(orderDTO.getOrderItemDTOList(), orderId);
                }
            } else {
                throw new SQLException();
            }

            conn.commit();
            return true;

        } catch(Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
