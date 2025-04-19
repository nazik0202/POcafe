import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Item> items;
    private  double totalPrice;
    private int numder;

    public Order() {
        this.items = new ArrayList<>();
        this.totalPrice = 0;
    }

    public List<Item> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addOrder(Item item){
        this.items.add(item);
        this.totalPrice += item.getPrice();
    }

    public void delete (Item itemdel){
        this.items.remove(itemdel);
        this.totalPrice = this.totalPrice - itemdel.getPrice();
    }
}
