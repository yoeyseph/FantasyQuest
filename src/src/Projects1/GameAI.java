package Projects1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class GameAI {

    private Queue<Monster> monsterEncounters;

    public GameAI(){
        this.monsterEncounters = new LinkedList<>();
    }

    // needed for adding monsters manually in the main
    public void addToQueue(Monster monster){
        monsterEncounters.add(monster);
    }

    public void addRandom(){

        for (int i = 1; i < 6; i++){
            int randomNumber = ThreadLocalRandom.current().nextInt(1, 4);

            if (randomNumber == 1){
                Skeleton skeleton = new Skeleton();
                monsterEncounters.add(skeleton);
            } else if (randomNumber == 2){
                Goblin goblin = new Goblin();
                monsterEncounters.add(goblin);
            } else {
                Orc orc = new Orc();
                monsterEncounters.add(orc);
            }

        }
    }

    public Queue<Monster> getMonsterEncounters(){
        return monsterEncounters;
    }

    public boolean fightBattle(ArrayList<Integer> playerCurrency, LinkedList<String> playerItems){

        if (monsterEncounters.isEmpty()){
            System.out.println("There are no enemies nearby");
            return false;
        }

        Monster monster = monsterEncounters.poll();
        ArrayList<Integer> monsterCurrency = monster.getCurrency();

        for (int i = 0; i < 3; i++){
            playerCurrency.set(i, playerCurrency.get(i) + monsterCurrency.get(i));
        }

        if (monster.getItem().equals("None")) {
            return true;
        }
        else {
            playerItems.add(monster.getItem());
        }
        return true;
    }

    public void display(){
        for (Monster monster : monsterEncounters){
            System.out.print(monster.getName() + " ");
        }
        System.out.println();
    }

}
