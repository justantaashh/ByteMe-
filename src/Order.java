import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {
    int order_no;
    HashMap<Item,Integer> i;
    int type;
    String status;
    String special_req;
    int amount;
    Order(int n, HashMap<Item,Integer> item, int type, String special_req, int amt)
    {
        this.order_no=n;
        this.i=item;
        this.type=type;
        this.special_req=special_req;
        this.amount=amt;
    }
    public void set_status(String status)
    {
        this.status=status;
    }
}
