package main;
public abstract class BasePlayer implements IPlayer {
    protected int health;
    protected int[] weapons;
    protected String name;

    public BasePlayer() {
        this.name = getClass().getSimpleName().replace("Player", "");
    }

    public int attack() {
        return weapons[(int) (Math.random() * weapons.length)];
    }

    public void observe(int totalDamage) {
    
    }

    public void reset() {
        health = 0;
        weapons = new int[0];
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int[] getWeapons() {
        return weapons;
    }

    public void setWeapons(int[] weapons) {
        this.weapons = weapons;
    }

    public void removeWeapon(int weapon) {
        int index = -1;
        for (int i = 0; i < weapons.length; i++) {
            if (weapons[i] == weapon) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            int[] newWeapons = new int[weapons.length - 1];
            System.arraycopy(weapons, 0, newWeapons, 0, index);
            System.arraycopy(weapons, index + 1, newWeapons, index, weapons.length - index - 1);
            weapons = newWeapons;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
