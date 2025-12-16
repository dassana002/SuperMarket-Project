package lk.ijse.fxclassproject.Models;

import lk.ijse.fxclassproject.DTO.CustomerDTO;
import lk.ijse.fxclassproject.DTO.ItemDTO;
import lk.ijse.fxclassproject.Utility.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {

    public ItemDTO itemSearch(String id) throws SQLException{
        String query = "SELECT * FROM item WHERE id = ?";

        ResultSet rs = CrudUtil.execute(query, id);

        ItemDTO itemDTO = null;

        if(rs.next()){
            return itemDTO = new ItemDTO(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("qty"),
                    rs.getDouble("unit_price")
            );
        }

        return null;
    }

    public ArrayList<ItemDTO> itemAll () throws SQLException {
        String query = "SELECT * FROM item";

        ResultSet rs = CrudUtil.execute(query);

        ArrayList<ItemDTO> custdtos = new ArrayList<>();

        while(rs.next()) {
            ItemDTO itemdto = new ItemDTO(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("qty"),
                rs.getDouble("unit_price")
            );
            custdtos.add(itemdto);
        }
        return custdtos;
    }

    public boolean decreseItemQty(int itemId, int qty) throws SQLException {
        return CrudUtil.execute("UPDATE item SET qty=qty - ? WHERE id = ? AND qty >= ?", qty, itemId, qty);
    }
}
