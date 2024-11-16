import java.sql.Array;
import java.util.*;

public class Menu {
    public static ArrayList<Item> i;
    public static HashMap<String, List<Item>> category_i;
    public static TreeMap<Integer,List<Item>> price_i;
    public Menu()
    {
        i=new ArrayList<>();
        category_i=new HashMap<>();
        price_i=new TreeMap<>();
    }
    public void update_item(String name,int price,boolean avail)
    {
        for(Item x: i)
        {
            if(Objects.equals(x.name, name)) {
                price_i.get(x.price).remove(x);
                if(!price_i.containsKey(price))
                    price_i.put(price,new ArrayList<>());
                Menu.price_i.get(price).add(x);
                x.price = price;
                x.avail = avail;
            }
        }


    }
    public void remove_item(String name)
    {
        for (Iterator<Item> iterator = i.iterator(); iterator.hasNext();) {
            Item x = iterator.next();
            if (Objects.equals(x.name, name))
            {
                iterator.remove();
                category_i.get(x.category).remove(x);
                price_i.get(x.price).remove(x);
            }
        }

    }

    public static void set_item_menu(String name, int price,String Category, boolean avail){
        Item item = new Item(name, price, Category, avail);
        if(!category_i.containsKey(Category))
            category_i.put(item.category,new ArrayList<>());
        if(!price_i.containsKey(price))
            price_i.put(item.price,new ArrayList<>());
        Menu.i.add(item);
        Menu.category_i.get(Category).add(item);
        Menu.price_i.get(price).add(item);
    }

}
