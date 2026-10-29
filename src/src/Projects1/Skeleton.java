package Projects1;

import java.util.ArrayList;
import java.util.List;
public class Skeleton implements Monster{

    private String name = "Skeleton";
    private ArrayList<Integer> currency = new ArrayList<>(List.of(10, 20, 30));
    private String item = "Bone Knife";

    @Override
    public String getItem(){
        return item;
    }

    @Override
    public ArrayList<Integer> getCurrency(){
        return currency;
    }

    @Override
    public String getName(){
        return name;
    }
}
