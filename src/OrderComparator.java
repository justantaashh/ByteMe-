import java.util.Comparator;

public class OrderComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        if (o1.type==1 && !(o2.type==1))
            return -1;
        else if (!(o1.type ==1) && o2.type==1)
            return 1;
        else
            return 0;
    }
}
