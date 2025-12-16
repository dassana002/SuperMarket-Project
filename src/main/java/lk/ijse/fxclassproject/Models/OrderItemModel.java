package lk.ijse.fxclassproject.Models;

import lk.ijse.fxclassproject.DTO.OrderItemDTO;
import lk.ijse.fxclassproject.Utility.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderItemModel {

    private final ItemModel itemModel = new ItemModel();

    public boolean saveOrderItems(List<OrderItemDTO> orderItemDTOList, int orderId) throws SQLException {
        for (OrderItemDTO orderItemDTO : orderItemDTOList) {

            boolean result = CrudUtil.execute("INSERT INTO order_items (order_id, item_id, qty, price) VALUES (?, ?, ?, ?)",
                    orderId,
                    orderItemDTO.getItemId(),
                    orderItemDTO.getQty(),
                    orderItemDTO.getUnitPrice()
            );

            if(result) {
                boolean result1 = itemModel.decreseItemQty(orderItemDTO.getItemId(), orderItemDTO.getQty());

                if(!result1) {
                    throw new SQLException();
                }
            } else {
                throw new SQLException();
            }
        }
        return true;
    }
}
