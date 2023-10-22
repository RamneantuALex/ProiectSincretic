package Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import main.BasePlayer;
import main.IPlayer;

public final class PlayerSmartcat extends BasePlayer implements IPlayer {
	public List<Integer> enemyWeapons = new ArrayList<>();
	public List<Integer> damageLog = new ArrayList<>();
	public int weaponRange = 0;
	public int maxWeapon = 0;
	public boolean firstPlayer = false;

	public void setWeapons(int[] weapons) {
		super.setWeapons(weapons);
		// this.enemyWeapons = new ArrayList<>(weapons.length);
		for (int weapon : weapons) {
			this.enemyWeapons.add(weapon);
		}
		this.weaponRange = weapons[weapons.length - 1] + weapons[0];
		this.maxWeapon = weapons[weapons.length - 1];
	}

	public int attack() {
		int attack = getWeapon();
		System.out.println("///SmartCat Attack" + attack);
		damageLog.add(getTotalDamage() + attack);
		// removeTheElement(weapons,attack);
		if (damageLog.size() == 1) {
			firstPlayer = true;
		}

		return attack;
	}

	public void observe(int totalDamage) {
		damageLog.add(totalDamage);

		if (damageLog.size() == 2) {
			firstPlayer = true;
		}

		updateEnemyWeapons(getEnemyWeaponUsed());
		System.out.print("////Weapons ");
		for (int i = 0; i < weapons.length; i++) {
			System.out.print(weapons[i] + " ");
		}
	}

	private int getWeapon() {
		if (!firstPlayer && !isEndGame()) { // Second player plays at Copycat
			// System.out.println("//////Weapons range " + weaponRange);
			int idealWeapon = weaponRange - getEnemyWeaponUsed();
			// System.out.println("/////Ideal weapon" + idealWeapon);
			int index = 0;
			// weaponRange--;

			System.out.println("");
			// index = enemyWeapons.indexOf(idealWeapon);
			for (int i = 0; i < weapons.length; i++) {
				if (idealWeapon == weapons[i]) {
					index = i;
				}
			}
			// System.out.println("////Index " + index);
			return index >= 0 ? weapons[index] : 0;

		} else

		{
			int remainingDamage = getHealth() - getTotalDamage();
			int perfectWeaponIndex = -1;

			for (int i = 0; i < weapons.length; i++) {
				if (weapons[i] == remainingDamage) {
					perfectWeaponIndex = i;
					break;
				}
			}

			return perfectWeaponIndex >= 0 ? weapons[perfectWeaponIndex] : getBackupWeapon();
		}
	}

	public void reset() {
		super.reset();
		damageLog.clear();
		enemyWeapons.clear();
		weaponRange = 0;
		maxWeapon = 0;
	}

	private int getTotalDamage() {
		if (damageLog.size() != 0) {
			return damageLog.get(damageLog.size() - 1);
		} else
			return 1;
	}

	private void updateEnemyWeapons(int removeUsedWeapon) {
		// System.out.println("Remove " + removeUsedWeapon);
		if (enemyWeapons.contains(removeUsedWeapon)) {
			enemyWeapons.set(removeUsedWeapon - 1, 0);

			// System.out.print("Arme Bot ");
			// for (int i = 0; i < enemyWeapons.size(); i++) {
			// System.out.print(enemyWeapons.get(i) + " ");
			// }
			System.out.println("");
		}
	}

	private int getEnemyWeaponUsed() {
		if (damageLog.size() == 0)
			return 0;

		if (damageLog.size() < 2) {
			return damageLog.get(damageLog.size() - 1);
		}
		return getTotalDamage() - damageLog.get(damageLog.size() - 2);
		/*
		 * int lastIndex = damageLog.size() - 1;
		 * 
		 * if (lastIndex < 1) { // If there are not at least two elements in damageLog,
		 * return 0 or handle it as // needed.
		 * 
		 * return damageLog.get(lastIndex); // You can choose a default value or error
		 * handling strategy here. } int prevIndex = lastIndex - 1; return
		 * damageLog.get(lastIndex) - damageLog.get(prevIndex);
		 */
	}

	private int getBackupWeapon() {
		Integer midWeapon = null;
		Integer passiveWeapon = null;
		Integer safeWeapon = null;
		Integer nonSuicideWeapon = null;
		int remainingWeaponsCount = weapons.length;
		int counter = 0;

		for (int myWeapon : weapons) {
			counter++;
			int futureRemainingDamage = getHealth() - getTotalDamage() - myWeapon;

			// Decide mid weapon
			if (counter == remainingWeaponsCount / 2) {
				midWeapon = myWeapon;
			}

			// Decide passive weapon that does not end the game in the next turn
			if (futureRemainingDamage > weaponRange) {
				passiveWeapon = myWeapon;
			}

			// Decide which weapon can be used without causing suicide (exceeding health)
			if (futureRemainingDamage > 0) {
				nonSuicideWeapon = myWeapon;
			} else
				nonSuicideWeapon = weapons[0];

			// Decide a safer weapon that will not give the enemy a win next round
			if (!enemyWeapons.contains(futureRemainingDamage)) {
				// Enemy does not have the right weapon to finish the game next round
				// but at the same time, we will not commit suicide by choosing a safe weapon
				safeWeapon = futureRemainingDamage >= 0 ? myWeapon : nonSuicideWeapon;
			}
		}

		if (isEndGame()) {
			if (passiveWeapon != null) {
				return passiveWeapon;
			} else if (safeWeapon != null) {
				return safeWeapon;
			} else {
				return getLastWeapon();
			}
		} else {
			if (midWeapon != null) {
				return midWeapon;
			} else if (safeWeapon != null) {
				return safeWeapon;
			} else {
				return getLastWeapon();
			}
		}
	}

	private boolean isEndGame() {
		return getHealth() - getTotalDamage() <= maxWeapon;
	}

	private int getLastWeapon() {
		return weapons[weapons.length - 1];
	}
}
