package Projects1;

import java.util.ArrayList;
import java.util.List;

public class Orc implements Monster {

    private String name = "Orc";
    private ArrayList<Integer> currency = new ArrayList<>(List.of(20, 50, 75));
    private String item = "Orc hammer";

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
