package Projects1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Player {

    private ArrayList<Integer> currency = new ArrayList<>(List.of(0,0,0));
    private LinkedList<String> items = new LinkedList<>();


    public ArrayList<Integer> getCurrency(){
        return currency;
    }

    public LinkedList<String> getItems(){
        return items;
    }

    public String displayCurrency(){
        String ans = "Gold: " + currency.get(0)
         + " -- Silver: " + currency.get(1) + " -- Copper: " + currency.get(2);
        return ans;
    }

    public String displayItems() {

        if (items.isEmpty()) {
            return "You have no items";
        }

        StringBuilder sb = new StringBuilder("Items: ");
        for (String item : items) {
            sb.append(item).append("  ");
        }
        return sb.toString().trim();
    }

}
