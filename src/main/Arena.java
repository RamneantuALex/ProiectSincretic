package main;

import java.util.Arrays;

public class Arena {
    private int turn = 0;
    private static Arena instance = null;
    private IPlayer player1;
    private IPlayer player2;
    private int health = 100;
    private int[] weapons = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};

   

    public static Arena getInstance() {
        if (instance == null) {
            instance = new Arena();
        }
        return instance;
    }

    public void initialize(IPlayer player1, IPlayer player2, int health, int[] weapons) {
        this.player1 = player1;
        this.player2 = player2;
        this.health = health;
        this.weapons = Arrays.copyOf(weapons, weapons.length);

        this.player1.setWeapons(this.weapons);
        this.player2.setWeapons(this.weapons);

        this.player1.setHealth(this.health);
        this.player2.setHealth(this.health);
    }

    public String fight() {
        int totalDamage = 0;

        while (totalDamage < health && turn < 1000) {
        	
            nextTurn();
            int playerAttack = getPlayerAtTurn().attack();
           System.out.println("Player "+getPlayerAtTurn()+" "+playerAttack);
           
            if (!isWeaponValid(playerAttack, getPlayerAtTurn())) {
                throw new RuntimeException(getPlayerAtTurn().getName() + " DISQUALIFIED for cheating!");
            }
            getPlayerAtTurn().removeWeapon(playerAttack);

            // Logging and battle actions
            totalDamage += playerAttack;
            System.out.println("Health "+ (100-totalDamage));
            getPlayerOnHold().observe(totalDamage);
        }

        if (totalDamage == health) {
            return getPlayerAtTurn().getName();
        } else {
            return getPlayerOnHold().getName();
        }
    }

    public void reset() {
        resetTurns();
      
        player1.setHealth(health);
        player2.setHealth(health);

        player1.setWeapons(Arrays.copyOf(weapons, weapons.length));
        player2.setWeapons(Arrays.copyOf(weapons, weapons.length));
    }

    private void nextTurn() {
        turn++;
    }

    private void resetTurns() {
        turn = 0;
    }

    public IPlayer getPlayerAtTurn() {
        return turn % 2 == 0 ? player1 : player2;
    }

  public IPlayer getPlayerOnHold() {
        return turn % 2 == 0 ? player2 : player1;
    }

    private boolean isWeaponValid(int playerAttack, IPlayer player) {
        return Arrays.stream(player.getWeapons()).anyMatch(weapon -> weapon == playerAttack);
    }

    @SuppressWarnings("unused")
	private boolean isGameValid() {
        return (Arrays.stream(weapons).sum() * 2) > health;
    }

    public void setShowBattleAction(boolean showBattleAction) {
    }
}
