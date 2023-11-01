package main;

import java.util.Arrays;

import Player.PlayerDescartes;
import Player.PlayerRandobotplus;
import Player.PlayerSmartcat;

public class Game {
	private static final int FIGHTS_PER_GAME = 10;
	private static final boolean FIGHTS_DETAILED = false;
	String[] players = { "PlayerDante","PlayerDescartes" };
	public void run() {
		

		int[][] globalStats = new int[players.length][];
		for (int i = 0; i < players.length; i++) {
			globalStats[i] = new int[FIGHTS_PER_GAME];
		}

		int[][] games = { { 100, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 }
				
				/*
				 * { 100, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19 }, { 100, 3, 5, 7, 9, 11, 13, 15,
				 * 17, 19 }, { 100, 4, 6, 8, 10, 12, 14, 16, 18 }, { 100, 2, 3, 5, 7, 9, 11, 15,
				 * 17, 19 }, { 100, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, { 100, 2,
				 * 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, { 100, 10, 20, 30, 40, 5 },
				 * 
				 * { 100, -1, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19 }
				 */
				         };

		Arena arena = new Arena();
		for (int gameIndex = 0; gameIndex < games.length; gameIndex++) {
			System.out.println("Game " + (gameIndex + 1));
			int health = games[gameIndex][0];
			int[] weapons = new int[games[gameIndex].length - 1];
			System.arraycopy(games[gameIndex], 1, weapons, 0, weapons.length);

			System.out.println("Health: " + health);
			System.out.print("Weapons: ");
			for (int weapon : weapons) {
				System.out.print(weapon + " ");
			}
			System.out.println();

			for (int i = 0; i < players.length; i++) {
				for (int j = i + 1; j < players.length; j++) {
					
						IPlayer player1 = createPlayerInstance(players[i]);
						IPlayer player2 = createPlayerInstance(players[j]);
						arena.initialize(player1, player2, health, weapons);
					

					int[] stats = new int[2];

					for (int fightIndex = 0; fightIndex < FIGHTS_PER_GAME; fightIndex++) {
						System.out.println("Fight#" + fightIndex);
						String fightResult = "Player" + arena.fight();

						if (fightResult.equals(players[i])) {
							stats[0]++; // player2 (second player) wins
						} else if (fightResult.equals(players[j])) {
							stats[1]++; // player1 (first player) wins
						}

						System.out.println("Fight " + (fightIndex + 1) + ": " + players[i] + " vs. " + players[j]
								+ " - Winner: " + fightResult);
						arena.reset();

					}
					int player1Index = Arrays.asList(players).indexOf(players[i]);
					int player2Index = Arrays.asList(players).indexOf(players[j]);

					globalStats[player1Index][gameIndex] += stats[0];
					globalStats[player2Index][gameIndex] += stats[1];

					System.out.println(
							"Results: " + players[i] + " (" + stats[0] + ") " + players[j] + " (" + stats[1] + ")");
				}
			}

			System.out.println();
			System.out.println("TOP (#" + (gameIndex + 1) + "):");
			int[] gameStats = new int[players.length];
			for (int i = 0; i < players.length; i++) {
				for (int j = 0; j < FIGHTS_PER_GAME; j++) {
					gameStats[i] += globalStats[i][j];

				}
				String playerName = players[i];
				System.out.println((i + 1) + ". " + playerName + " (" + gameStats[i] + ")");
			}
			System.out.println("-------------------------------------------------------------");
		}

		System.out.println("=============================================================");
		System.out.println("TOP OVERALL:");
		String[] playerNamesWithWins = new String[players.length];
		for (int playerIndex = 0; playerIndex < players.length; playerIndex++) {
			String playerName = players[playerIndex];
			int totalWins = 0;
			for (int gameWins : globalStats[playerIndex]) {
				totalWins += gameWins;
			}
			playerNamesWithWins[playerIndex] = playerName + " (" + totalWins + ")";
		}

		// Sort the array in descending order based on total wins
		Arrays.sort(playerNamesWithWins, (a, b) -> {
			int winsA = Integer.parseInt(a.substring(a.lastIndexOf("(") + 1, a.lastIndexOf(")")));
			int winsB = Integer.parseInt(b.substring(b.lastIndexOf("(") + 1, b.lastIndexOf(")")));
			return Integer.compare(winsB, winsA);
		});

		for (int i = 0; i < playerNamesWithWins.length; i++) {
			System.out.println((i + 1) + ". " + playerNamesWithWins[i]);
		}
		System.out.println("=============================================================");
	}

	private IPlayer createPlayerInstance(String className) {
		if ("PlayerDante".equals(className)) {
			return new PlayerDante();
		} else if ("PlayerDescartes".equals(className)) {
			return new PlayerDescartes();
		} else {
			System.err.println("Error: Invalid player class name - " + className);
			return null;
		}
	}
}
