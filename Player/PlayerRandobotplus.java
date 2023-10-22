package Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.BasePlayer;
import main.IPlayer;

public class PlayerRandobotplus extends BasePlayer implements IPlayer {
    private List<Integer> damageLog = new ArrayList<>();

    @Override
    public int attack() {
        // Randomly choose 1 weapon - FIRST TRY
        int weapon = weapons[new Random().nextInt(weapons.length)];

        if (isWeaponSafe(weapon)) {
            return weapon;
        } else {
            // Randomly choose 1 weapon - SECOND TRY
            weapon = weapons[new Random().nextInt(weapons.length)];

            if (isWeaponSafe(weapon)) {
                return weapon;
            } else {
                // Randomly choose 1 weapon - THIRD TRY
                return weapons[new Random().nextInt(weapons.length)];
            }
        }
    }

    @Override
    public void observe(int totalDamage) {
        damageLog.add(totalDamage); // Add observed damage to the list
    }

    private int getTotalDamage() {
        if (!damageLog.isEmpty()) {
            return damageLog.get(damageLog.size() - 1);
        }
        return 0;
    }

    private boolean isWeaponSafe(int weapon) {
        // Remaining health after weapon use should be positive or zero
        return getHealth() - getTotalDamage() - weapon >= 0;
    }
}
