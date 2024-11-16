import java.util.Scanner;
public class Admin { ;
    public  void add_item(Menu menu)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Name, Price, Category and availability of Item.");
        String n=sc.next();
        int p=sc.nextInt();
        String cat=sc.next();
        boolean avail=sc.nextBoolean();
        menu.set_item_menu(n,p,cat,avail);
        return;
    }

    public void update_status(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Order No.");
        int order_no=sc.nextInt();
        Order x=null;
        for(Order o : OrderList.orders)
            if(order_no==o.order_no)
                x=o;
        System.out.println("Update Status to: ");
        String s=sc.next();
        if(s.equals("Completed"))
        {
            assert x != null;
            OrderList.amount+=x.amount;
            OrderList.total_orders++;
            x.set_status(s);
            OrderList.remove_compOrder(x);
        }
    }

    public void adminInterface(Menu m)
    {
        while(true)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("1. Menu Management");
            System.out.println("2. Order Management");
            System.out.println("3. Daily Report");
            System.out.println("4. Back");
            int ch = sc.nextInt();

            if (ch == 1) {
                while(true) {
                    System.out.println("1. Add Item");
                    System.out.println("2. Update Item");
                    System.out.println("3. Remove Item");
                    System.out.println("4. Back");
                    int in_ch = sc.nextInt();
                    if (in_ch == 1) {
                        add_item(m);
                        System.out.println("Item added!");
                    } else if (in_ch == 2) {
                        System.out.println("Enter Item name to be updated");
                        String n = sc.next();
                        System.out.println("Enter updated price and availability");
                        int p = sc.nextInt();
                        boolean av = sc.nextBoolean();
                        m.update_item(n, p, av);
                        System.out.println("Item Updated");
                    } else if (in_ch == 3) {
                        System.out.println("Enter Item to be Removed");
                        String n = sc.next();
                        m.remove_item(n);
                    } else if (in_ch == 4)
                        break;
                }
            } else if (ch == 2) {
                while (true) {
                    System.out.println("1. View Pending Orders");
                    System.out.println("2. Update Order Status");
                    System.out.println("3. Process Refunds");
                    System.out.println("4. View Special Requests");
                    System.out.println("5. Back");
                    int in_ch = sc.nextInt();
                    if (in_ch == 1)
                        OrderList.viewPendingOrders();
                    else if (in_ch == 2) {
                        update_status();
                    } else if (in_ch == 3) {
                        System.out.println("Customer to Deal with: ");
                        String id = sc.next();
                        for (Customer c : Main.customers) {
                            if (id.equals(c.id)) {
                                System.out.println("Enter Order no. For Refund");
                                int x = sc.nextInt();
                                for (Order o : c.orderHistory) {
                                    if (x == o.order_no)
                                        c.set_credits(o.amount);
                                    else
                                        System.out.println("No Such Order");
                                }
                            }
                        }
                        System.out.println("Refund Successful");
                    } else if (in_ch == 4) {
                        System.out.println("Enter Order no.");
                        int x = sc.nextInt();
                        for (Order o : OrderList.orders) {
                            if (x == o.order_no)
                                System.out.println(o.special_req);
                        }
                    } else
                        break;
                }
            } else if (ch == 3) {
                System.out.println("Report for the Day: ");
                System.out.println("Total Sales: " + OrderList.amount);
                System.out.println("Total Orders: " + OrderList.total_orders);
            }
            else  break;
        }
    }
}

