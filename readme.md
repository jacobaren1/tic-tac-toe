Tic-Tac-Toe in Java
===================

### Purpose
This is a sandbox project for learning Java and becoming more confident with object-oriented programming.
The implementation is inspired by an old school project from KTH, originally built in Python.

### Project overview
This repository contains a simple Java TicTacToe implementation with a 5x5 board and win detection for five consecutive marks.
The project now includes a small AWT menu window (`TttWindow`) where the user can enter the two player names and then start the game from the GUI.

The main classes are:
- `BoardMatrix` — stores the board state in rows, columns, and diagonals
- `Player` — represents a player, handles moves, turn order, and win detection
- `TttWindow` — opens the main menu, collects player names, and starts the game on button press

### Code quality and naming conventions
- `lowerCamelCase` for variables and methods
- `UpperCamelCase` for classes
- Javadoc comments added to classes and methods where appropriate

### How to run
Compile and run the GUI entry point using the Java compiler:

```shell
javac TicTacToe.java TttWindow.java
java -cp . TttWindow
```

When the window opens:
1. Enter the name for player `X` and player `O`
2. Press `Submit` or hit `Enter` while a button is focused
3. Press `Start game!` to begin the demo match

### Linting
Use Checkstyle to validate code style and formatting. Example:

```shell
java -jar checkstyle-13.6.0-all.jar -c /google_checks.xml TicTacToe.java
```

### Notes
- The current implementation stores rows, columns, and diagonals explicitly to simplify win detection.
- The example game sequence is still hardcoded in the game runner, but the player names now come from the GUI menu.
- The `BoardMatrix` class updates a boolean win flag when a player completes five in a row.
- The menu currently uses AWT components and is designed as a small interactive front-end for the existing game logic.

### Future improvements
- Add interactive user input for moves instead of hardcoded plays
- Improve board display formatting and user feedback
- Add automated tests for board logic and move validation
- Integrate a build tool such as Maven or Gradle
- Add GitHub Actions to run linting and build checks automatically
