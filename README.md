### Java Game - Weapon Arena

This Java program simulates a weapon arena game where different players compete against each other. The program includes various player classes with distinct strategies for attacking and defending. The game maintains a record of each player's wins and evaluates their overall performance.

#### Setup and Execution

To run the program, you'll need Java installed on your system. After compiling the code, execute the `Game` class. Here's an overview of the primary components and functionalities of the program:

#### Components

1. **Game class**: Manages the entire execution of the game, including initializing players, executing fights, and maintaining game statistics.
2. **Arena class**: Controls the flow of the game, handles player turns, checks the validity of the game, and resets the game state.
3. **BasePlayer interface**: Serves as a template for player classes, specifying methods for attack, observation, health, weapons, and other functionalities.
4. **PlayerDescartes class**: Implements a player with a complex strategy that considers the opponent's previous moves and the game state.
5. **PlayerSmartcat class**: Implements a player with a sophisticated strategy that adapts based on the opponent's moves and the game's progress.
6. **PlayerConfucius class**: Implements a player with a thoughtful strategy that focuses on maintaining the optimal weapon for future attacks.
7. **AlexBot class**: Implements a player with an evolving strategy based on the opponent's moves and the game state.

#### Running the Game

To run the game, compile the Java code and execute the `Game` class. Ensure that the necessary player classes are compiled as well. The program will simulate a series of fights based on the predefined rules and player strategies, providing detailed statistics and the overall performance of each player.

Feel free to customize the game rules, add new player classes, or modify the existing strategies to explore different gameplay dynamics. Enjoy the weapon arena game!
