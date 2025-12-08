package lk.ijse.fxclassproject.DTO;

public class ItemDTO {
    private int id;
    private String name;
    private int qty;
    private double unit_price;

    public ItemDTO(int id, String name, int qty, double unit_price) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.unit_price = unit_price;
    }

    public ItemDTO() {
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                ", unit_price=" + unit_price +
                '}';
    }
}
