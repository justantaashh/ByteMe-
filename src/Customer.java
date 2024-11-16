
import java.util.*;

public class Customer {
    static int count=0;
    String id;
    int type;
    List<Order> orderHistory;
    int credits;
    Scanner sc;
    Customer(String s)
    {
        this.id=s;
        credits=0;
        this.orderHistory=new ArrayList<>();
        sc=new Scanner(System.in);
    }
    public void set_credits(int x)
    {
        this.credits=x;
    }
    public void browse_menu()
    {
        while(true) {
            System.out.println("1. View all Items. ");
            System.out.println("2. Items Sorted by Price. ");
            System.out.println("3. Filter by Category. ");
            System.out.println("4. Back");
            int ch = sc.nextInt();
            if (ch == 1) {
                for (Item i : Menu.i)
                    System.out.println(i.name + " " + i.price);
            } else if (ch == 2) {
                for (Map.Entry<Integer, List<Item>> entry : Menu.price_i.entrySet()) {
                    int price = entry.getKey();
                    List<Item> items = entry.getValue();
                    if (items.isEmpty())
                        continue;
                    System.out.println("Price: " + price);
                    for (Item item : items) {
                        System.out.print(item.name + " ");
                    }
                    System.out.println();
                }
            } else if(ch==3){
                for (Map.Entry<String, List<Item>> entry : Menu.category_i.entrySet()) {
                    String category = entry.getKey();
                    List<Item> items = entry.getValue();
                    System.out.println("Category: " + category);
                    for (Item item : items) {
                        System.out.println(item.name + " " + item.price);
                    }
                }
            }
            else
                return;
        }
    }
    public int amt(HashMap<Item,Integer> x,int type)
    {
        int a=type*50;
        for (Map.Entry<Item, Integer> entry : x.entrySet()) {
            Integer quantity = entry.getValue();
            a += quantity;
        }
        return a;
    }
    public  void customerInterface(Menu m)
    {
        Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("1. Browse Menu");
            System.out.println("2. Cart Options");
            System.out.println("3. Track Order");
            System.out.println("4. Item Review");
            System.out.println("5. Back");
            int choice = sc.nextInt();
            if (choice == 1)
                browse_menu();
            else if (choice == 2) {
                HashMap<Item, Integer> cart = new HashMap<>();
                while(true) {
                    System.out.println("1. Add Items");
                    System.out.println("2. Modify Quantity");
                    System.out.println("3. Remove Item from Cart");
                    System.out.println("4. View Totals");
                    System.out.println("5. Checkout");
                    System.out.println("6. Back");
                    int in_ch = sc.nextInt();
                    if (in_ch == 1) {
                        System.out.println("Enter Item name");
                        String n = sc.next();
                        for (Item i : m.i) {
                            if (n.equals(i.name))
                                cart.put(i, 1);
                        }
                    } else if (in_ch == 2) {
                        System.out.println("Enter Item name and its quantity ");
                        String n = sc.next();
                        int q = sc.nextInt();
                        for (Map.Entry<Item, Integer> entry : cart.entrySet()) {
                            Item item = entry.getKey();
                            if (n.equals(item.name))
                                cart.put(item, q);
                        }
                    } else if (in_ch == 3) {
                        System.out.println("Enter name of Item To remove");
                        String n = sc.next();
                        for (Map.Entry<Item, Integer> entry : cart.entrySet()) {
                            Item item = entry.getKey();
                            if (n.equals(item.name))
                                cart.remove(item);
                        }
                    } else if (in_ch == 4) {
                        for (Map.Entry<Item, Integer> entry : cart.entrySet()) {
                            Item item = entry.getKey();
                            Integer quantity = entry.getValue();
                            System.out.println(quantity + " " + item.name + " " + item.price);
                        }
                    } else if (in_ch == 5) {
                        System.out.println("VIP Order?(Enter 1.VIP or 0.Regular)");
                        int type = sc.nextInt();
                        System.out.println("Special Request if any: ");
                        String req = sc.nextLine();
                        this.type = type;
                        int amount = amt(cart, this.type);
                        Order o = new Order(count++, cart, type, req, amount);
                        OrderList.addOrder(o);
                        orderHistory.add(o);
                    }
                    else
                        break;
                }
            } else if (choice == 3) {
                while(true) {
                    System.out.println("1. View Order Status");
                    System.out.println("2. Cancel Order");
                    System.out.println("3. Order History");
                    System.out.println("4. Back");
                    int in_ch = sc.nextInt();
                    if (in_ch == 1) {
                        System.out.println("Enter Order no.");
                        int o_no = sc.nextInt();
                        for (Order o : orderHistory) {
                            if (o_no == o.order_no)
                                System.out.println(o.status);
                        }
                    } else if (in_ch == 2) {
                        System.out.println("Enter Order No.");
                        int o_no = sc.nextInt();
                        for (Order o : orderHistory) {
                            if (o_no == o.order_no) {
                                o.status = "Cancel";
                                OrderList.orders.remove(o);
                            }
                        }
                    } else if (in_ch == 3) {
                        for(Order o:orderHistory)
                        {
                            System.out.println(o.order_no);
                            HashMap<Item, Integer> i = new HashMap<>();
                            for (Map.Entry<Item, Integer> entry : o.i.entrySet()) {
                                Item item = entry.getKey();
                                Integer quantity = entry.getValue();
                                System.out.println("Item: " + item.name + ", Quantity: " + quantity);
                            }
                        }
                    } else
                        break;
                }
            } else if (choice == 4) {
                while(true) {
                    System.out.println("1. Provide Review");
                    System.out.println("2. View Reviews");
                    System.out.println("3. Back");
                    int in_choice = sc.nextInt();
                    if (in_choice == 1) {
                        System.out.print("Item: ");
                        String n = sc.next();
                        System.out.println();
                        System.out.print("Review: ");
                        String rev = sc.nextLine();
                        for (Item x : m.i) {
                            if (Objects.equals(x.name, n)) {
                                x.set_review(rev);
                            }
                        }
                        customerInterface(m);
                    } else if (in_choice == 2) {
                        System.out.print("Review of Item: ");
                        String n = sc.next();
                        for (Item x : m.i) {
                            if (Objects.equals(x.name, n)) {
                                x.get_review();
                            }
                        }
                    } else
                        break;
                }
            }else break;
        }
    }

}
