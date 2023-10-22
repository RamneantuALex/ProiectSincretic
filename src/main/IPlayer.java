package main;

public interface IPlayer {
    void setName(String name);

    String getName();

    int attack();

    void observe(int totalDamage);

    int getHealth();

    void setHealth(int health);

    int[] getWeapons();

    void setWeapons(int[] weapons);

    void removeWeapon(int weapon);

    void reset();
}
