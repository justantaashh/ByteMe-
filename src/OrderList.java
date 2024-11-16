import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class OrderList {
    static PriorityQueue<Order> orders=new PriorityQueue<>(new OrderComparator());
    static int amount=0;
    static int total_orders=0;
    public static void addOrder(Order order) {
        orders.add(order);
    }
    public Order getNextOrder() {
        return orders.poll();
    }

    public static void viewPendingOrders() {
        System.out.println("Pending Orders:");
        for (Order order : orders) {
            if ("Pending".equals(order.status)) {
                System.out.println(order);
            }
        }
    }
    public static void remove_compOrder(Order o) {
        List<Order> tempOrders = new ArrayList<>(orders);
        boolean removed = tempOrders.remove(o);

        if (removed) {
            orders.clear();
            orders.addAll(tempOrders);
            System.out.println("Order removed successfully.");
        } else
            System.out.println("Order not found in the queue.");
    }
}

