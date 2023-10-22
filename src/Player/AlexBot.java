package Player;

import java.util.ArrayList;
import java.util.List;

import main.BasePlayer;
import main.IPlayer;

public class AlexBot extends BasePlayer implements IPlayer {
    private int remainingHealth = 0;
    private int opponentTotalDamage = 0;
    private int totalDamage = 0;
    private List<Integer> damageHistory = new ArrayList<>();
    private int maxWeapon = 0;
    private int turn = 0;

    @Override
    public int attack() {
        int maxWeapon = weapons[weapons.length - 1];
        turn++;

        if (turn == 1 || turn == 2) {
        	
        	for (int weapon : weapons) {
        	    maxWeapon = Math.max(maxWeapon, weapon);
        	}
        }

        List<Integer> arr = new ArrayList<>();
        for (int weapon : weapons) {
            arr.add(weapon);
        }
        int myDamage = weapons[weapons.length - 1];

        if (remainingHealth <= maxWeapon) {
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i) <= remainingHealth) {
                    myDamage = arr.get(i);
                }
            }
        }

        damageHistory.add(myDamage);
        return myDamage;
    }

    @Override
    public void observe(int totalDamage) {
        turn++;
        opponentTotalDamage = totalDamage - damageHistory.stream().mapToInt(Integer::intValue).sum();
        remainingHealth = health - totalDamage;
    }

    
}

