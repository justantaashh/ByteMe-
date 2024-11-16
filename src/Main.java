import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    static List<Customer> customers=new ArrayList<>();

    public static void main(String[] args) {
        Customer c=new Customer("2023105");
        customers.add(c);
        Scanner sc = new Scanner(System.in);
        Menu m =new Menu();
        Admin a=new Admin();
        while(true)
        {
            System.out.println("1. Admin ");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            int choice = sc.nextInt();
            if (choice == 2) {
                System.out.println("Enter ID: ");
                String user=sc.next();
                for(Customer i:customers)
                {
                    if(user.equals(i.id))
                        i.customerInterface(m);
                }
            }
            else if (choice == 1)
                a.adminInterface(m);
            else
                System.exit(0);
        }
    }
}
