package main;

import java.util.ArrayList;
import java.util.List;

public final class PlayerDante extends BasePlayer implements IPlayer {

	private List<Integer> damageLog = new ArrayList<>();
	private boolean first = false;

	@Override

	public int attack() {
		int remainingHealth = 100 - getTotalDamage();
		int i=0;
		// Verificăm dacă putem încheia jocul într-o singură lovitură
		for ( i=weapons.length-1;i>=0;i-- ) {
			if (weapons[i] == remainingHealth) {
				damageLog.add(getTotalDamage() + weapons[i]);
				return weapons[i];// Alegem arma care ar duce la victorie
			}

		}

		// Dacă nu  câștiga într-o singură lovitură, alegem o strategie defensivă
		for ( i = weapons.length - 1; i >= 0; i--) {
			if (remainingHealth - weapons[i] == 1) { // Smartcat nu o sa aiba in end game arma 1 niciodata
				damageLog.add(getTotalDamage() + weapons[i]);
				return weapons[i];
			}
			if (remainingHealth - weapons[i] > 20) {
				damageLog.add(getTotalDamage() + weapons[i]);
				return weapons[i];
			}
		}
		
		if (damageLog.size() == 1) {
			first= true;
		}
		
		return ArmaFatala();
		
	}

	private int ArmaFatala() {
		int arma=weapons[weapons.length-1];
		if (!first) {
			for (int weapon : weapons) {
				if (getEnemyWeaponUsed() + weapon == 20)
					return weapon;
			}
		}
		 return arma;
	}

	private int getEnemyWeaponUsed() {
		if (damageLog.size() == 0)
			return 0;

		if (damageLog.size() < 2) {
			return damageLog.get(damageLog.size() - 1);
		}
		return getTotalDamage() - damageLog.get(damageLog.size() - 2);
	}

	private int getTotalDamage() {
		if (damageLog.size() != 0) {
			return damageLog.get(damageLog.size() - 1);
		} else
			return 1;
	}

	@Override
	public void observe(int totalDamage) {
		damageLog.add(totalDamage);

		if (damageLog.size() == 2) {
			first = true;
		}
	}

	@Override
	public void reset() {
		health = 0;
		weapons = new int[0];
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
