package Player;

import java.util.Arrays;

import main.BasePlayer;
import main.IPlayer;



public final class PlayerDescartes extends BasePlayer implements IPlayer {
    private int[] damageHistory = new int[21];
    private int[] damageHistoryOpponent = new int[21];
    private int lastOpponentDamage = 0;
    private int remainingLife = 100;
   

    @Override
    public int attack() {
        int ok = 0;
        int myAttackDamage = weapons[weapons.length - 1];

        for (int i = 0; i < weapons.length; i++) {
            if (weapons[i] != 0) {
                if (remainingLife - weapons[i] == 0) {
                    myAttackDamage = weapons[i];
                    damageHistory[i] = myAttackDamage;
                    return myAttackDamage;
                }
            }
        }

        for (int i = 0; i < weapons.length; i++) {
            if (weapons[i] != 0) {
                if (remainingLife - weapons[i] == 2) {
                    myAttackDamage = weapons[i];
                    damageHistory[i] = myAttackDamage;
                    return myAttackDamage;
                }
            }
        }

        if (remainingLife - weapons[weapons.length - 1] >= 20) {
            myAttackDamage = weapons[weapons.length - 1];
            damageHistory[weapons.length - 1] = myAttackDamage;
            return myAttackDamage;
        }

        if ((remainingLife % 2 == 1) && (remainingLife - weapons[weapons.length - 1] > 0)) {
            myAttackDamage = weapons[weapons.length - 1];
            damageHistory[weapons.length - 1] = myAttackDamage;
            return myAttackDamage;
        }

        if (remainingLife % 2 == 1 && remainingLife - weapons[weapons.length - 1] <= 0) {
            for (int i = weapons.length - 1; i >= 0; i--) {
                if (weapons[i] != 0) {
                    if (remainingLife - weapons[i] >= 2) {
                        myAttackDamage = weapons[i];
                        damageHistory[i] = myAttackDamage;
                        return myAttackDamage;
                    }
                }
            }
        }

        if (remainingLife % 2 == 0) {
            for (int i = 0; i < damageHistoryOpponent.length; i++) {
                if (damageHistoryOpponent[i] == 1) {
                    ok = 1;
                    break;
                }
            }

            if (ok == 1) {
                for (int i = 0; i < weapons.length; i++) {
                    if (weapons[i] != 1 && weapons[i] != 0) {
                        myAttackDamage = weapons[i];
                        damageHistory[i] = myAttackDamage;
                        return myAttackDamage;
                    }
                }
            }

            if (ok == 0) {
                for (int i = weapons.length - 1; i >= 0; i--) {
                    if (weapons[i] != 0) {
                        if (remainingLife - weapons[i] == 1) {
                            myAttackDamage = weapons[i];
                            damageHistory[i] = myAttackDamage;
                            return myAttackDamage;
                        }
                    }
                }
            }
        }

        damageHistory[weapons.length - 1] = myAttackDamage;
        return myAttackDamage;
    }

    @Override
    public void observe(int totalDamage) {
        remainingLife = 100 - totalDamage;
        lastOpponentDamage = totalDamage - Arrays.stream(damageHistory).sum();

        // Update the damage history opponent array
        System.arraycopy(damageHistoryOpponent, 0, damageHistoryOpponent, 1, damageHistoryOpponent.length - 1);
        damageHistoryOpponent[0] = lastOpponentDamage;
    }

    @Override
    public void reset() {
        health = 0;
        weapons = new int[0];
        remainingLife = 100;
        Arrays.fill(damageHistory, 0);
        Arrays.fill(damageHistoryOpponent, 0);
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
