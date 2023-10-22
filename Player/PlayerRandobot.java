package Player;

import java.util.Random;

import main.BasePlayer;
import main.IPlayer;

public  class PlayerRandobot extends BasePlayer implements IPlayer {
    @Override
    public int attack() {
        Random random = new Random();
        int randomIndex = random.nextInt(weapons.length);
        return weapons[randomIndex];
    }
}

