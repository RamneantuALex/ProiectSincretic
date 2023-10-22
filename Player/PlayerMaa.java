package Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.BasePlayer;
import main.IPlayer;


public final class PlayerMaa extends BasePlayer implements IPlayer {
    private List<Integer> damageHistory = new ArrayList<>();
    private int lastOpponentDamage = 0;
    private int remainingHealth = 100;
    private int remainingHealth2 = 99;
    private int turn = 0;
    private int maxValue = 0;
  

    

    private int getWeapon() {
        int exactDamageIndex;
        if ((exactDamageIndex = indexOfWeapon(remainingHealth)) != -1) {
            return weapons[exactDamageIndex];
        } else if ((exactDamageIndex = indexOfWeapon(remainingHealth2)) != -1) {
            return weapons[exactDamageIndex];
        }
        return weapons[weapons.length - 1];
    }

    @Override
    public int attack() {
        turn++;
        if (turn == 1 || turn == 2) {
            maxValue = Arrays.stream(weapons).max().orElse(0);
        }

        int myAttackDamage = weapons[weapons.length - 1];
        int exactDamageIndex = indexOfWeapon(remainingHealth);
        int exactDamageIndex2 = indexOfWeapon(remainingHealth2);

        if (isWeaponSafe(myAttackDamage)) {
            myAttackDamage = weapons[weapons.length - 1];
        } else {
            myAttackDamage = weapons[indexOfWeapon(health)];
        }

        if (remainingHealth > maxValue) {
            isWeaponSafe(myAttackDamage);
        }

        if (remainingHealth < maxValue && exactDamageIndex != -1) {
            myAttackDamage = getWeapon();
        } else if (remainingHealth < maxValue && remainingHealth % 2 == 0 && exactDamageIndex2 != -1) {
            myAttackDamage = getWeapon();
        }

        damageHistory.add(myAttackDamage);
       // System.out.println("Attack Maa "+myAttackDamage);
        return myAttackDamage;
    }
    public void setWeapons(int[] weapons) {
        this.weapons = weapons;
    }

    @Override
    public void observe(int totalDamage) {
        lastOpponentDamage = totalDamage - damageHistory.stream().mapToInt(Integer::intValue).sum();
        remainingHealth = health - (lastOpponentDamage + damageHistory.stream().mapToInt(Integer::intValue).sum());
        remainingHealth2 = (health - 1) - (lastOpponentDamage + damageHistory.stream().mapToInt(Integer::intValue).sum());
    }

    private boolean isWeaponSafe(int myAttackDamage) {
        return getHealth() - myAttackDamage >= 0;
    }

    @Override
    public void reset() {
        turn = 0;
        maxValue = 0;
        health = 0;
        weapons = new int[0];
        damageHistory = new ArrayList<>();
        lastOpponentDamage = 0;
        remainingHealth = 100;
        remainingHealth2 = 99;
    }

    private int indexOfWeapon(int weapon) {
        for (int i = 0; i < weapons.length; i++) {
            if (weapons[i] == weapon) {
                return i;
            }
        }
        return -1;
    }
}



