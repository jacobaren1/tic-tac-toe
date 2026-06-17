Tic-Tac-Toe in Java
===================

### Purpose
This is a sandbox project for learning Java and becoming more confident with object-oriented programming.
The implementation is inspired by an old school project from KTH, originally built in Python.

### Project overview
This repository contains a simple command-line TicTacToe game.
The implementation currently uses a 5x5 board and detects when a player gets five in a row.

The main classes are:
- `BoardMatrix` — stores the board state in rows, columns, and diagonals
- `Player` — represents a player, handles moves, turn order, and win detection

### Code quality and naming conventions
- `lowerCamelCase` for variables and methods
- `UpperCamelCase` for classes
- Javadoc comments added to classes and methods where appropriate

### How to run
Compile and run the game using the Java compiler:

```shell
javac TicTacToe.java
java TicTacToe
```

### Linting
Use Checkstyle to validate code style and formatting. Example:

```shell
java -jar checkstyle-13.6.0-all.jar -c /google_checks.xml TicTacToe.java
```

### Notes
- The current implementation stores rows, columns, and diagonals explicitly to simplify win detection.
- The example game flow is hardcoded in `main` and does not yet support interactive console input.
- The `BoardMatrix` class updates a boolean win flag when a player completes five in a row.

### Future improvements
- Add interactive user input for moves instead of hardcoded plays
- Improve board display formatting and user feedback
- Add automated tests for board logic and move validation
- Integrate a build tool such as Maven or Gradle
- Add GitHub Actions to run linting and build checks automatically
