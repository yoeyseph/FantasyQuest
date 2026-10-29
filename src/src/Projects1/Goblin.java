package Projects1;

import java.util.ArrayList;
import java.util.List;

public class Goblin implements Monster{

    private String name = "Goblin";
    private ArrayList<Integer> currency = new ArrayList<>(List.of(50, 75, 100));
    private String item = "None";

    @Override
    public String getItem() {
        return item;
    }

    @Override
    public ArrayList<Integer> getCurrency() {
        return currency;
    }

    @Override
    public String getName(){
        return name;
    }
}
