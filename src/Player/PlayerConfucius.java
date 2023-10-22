package Player;

import main.BasePlayer;
import main.IPlayer;

public final class PlayerConfucius extends BasePlayer implements IPlayer {
    private int[] damageHistory = new int[10]; // Assuming a maximum of 10 attacks
    
    private int remainingLife = 100;
    

    @Override
    public int attack() {
        int myAttackDamage = weapons[weapons.length - 1];
        int i = 1;

        while (i <= 9 && i < weapons.length) {
            if (remainingLife - weapons[i] == 0 || remainingLife - weapons[i] == 2) {
                myAttackDamage = weapons[i];
                break;
            }
            i++;
        }

        damageHistory[0] = myAttackDamage; // Store the most recent damage
        return myAttackDamage;
    }

    @Override
    public void observe(int totalDamage) {
        remainingLife = 100 - totalDamage;
    }

    @Override
    public void reset() {
        health = 0;
        weapons = new int[0]; // Reset weapons as an empty array
        remainingLife = 100;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int[] getWeapons() {
        return weapons;
    }

    @Override
    public void setWeapons(int[] weapons) {
        this.weapons = weapons;
    }

    @Override
    public void removeWeapon(int weapon) {
        int[] newWeapons = new int[weapons.length - 1];
        int j = 0;

        for (int i = 0; i < weapons.length; i++) {
            if (weapons[i] != weapon) {
                newWeapons[j] = weapons[i];
                j++;
            }
        }

        weapons = newWeapons;
    }
}
