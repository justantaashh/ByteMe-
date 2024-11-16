import java.util.ArrayList;
import java.util.List;

public class Item {
    String name;
    int price;
    String category;
    boolean avail;
    List<String> reviews;
    Item(String n,int p,String c,boolean a)
    {
        this.name=n;
        this.price=p;
        this.category=c;
        this.avail=a;
        this.reviews=new ArrayList<>();
    }
    public  void set_review(String r)
    {
        this.reviews.add(r);
    }
    public void get_review(){
        for(String s:reviews)
        {
            System.out.println(s);
        }
    }

}
