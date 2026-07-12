import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Driver Class
public class TttWindow {
    // main function

    private static class PlayerSection {
        private final char boardChar;
        private final TextField playerNameInput;
        private final Label showPlayerName;

        /**
         * Creates a part of a frame for as an aid to create a player
         *
         * @param boarChar the players char (playing piece)
         */
        public PlayerSection(char boardChar) {
            this.boardChar = boardChar;
            this.playerNameInput = new TextField(20);
            this.showPlayerName = new Label();
        }

        public void render(Panel parentPanel) {
            Panel section = new Panel(new GridLayout(3, 1, 0, 10));
            Label inputHelp = new Label(String.format("Enter player name for player '%c'!", this.boardChar));
            Button submitButton = new Button("Submit");

            Panel inputRow = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            inputRow.add(this.playerNameInput);
            inputRow.add(submitButton);

            // Action listener for Enter key or Button click
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showPlayerName.setText("Welcome " + getPlayerName());
                }
            };

            this.playerNameInput.addActionListener(actionListener);
            submitButton.addActionListener(actionListener);

            inputHelp.setAlignment(Label.CENTER);
            this.showPlayerName.setAlignment(Label.CENTER);

            section.add(inputHelp);
            section.add(inputRow);
            section.add(this.showPlayerName);

            parentPanel.add(section);
        }

        public String getPlayerName() {
            String enteredName = this.playerNameInput.getText().trim();
            if (enteredName.isEmpty()) {
                return "Player " + this.boardChar;
            }

            return enteredName;
        }
    }

    public static void renderMainMenu()
    {
        // Declaring a Frame and Label
        Frame frame = new Frame("Tic-Tac-Toe main menu");
        PlayerSection playerX = new PlayerSection('X');
        PlayerSection playerO = new PlayerSection('O');
        Button bStartGame = new Button("Start game!");

        Panel playerPanel = new Panel(new GridLayout(2, 1, 0, 20));
        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));

        bStartGame.setPreferredSize(new Dimension(180, 30));

        playerX.render(playerPanel);
        playerO.render(playerPanel);

        frame.setLayout(new BorderLayout(0, 20));
        frame.add(playerPanel, BorderLayout.CENTER);
        buttonPanel.add(bStartGame);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Adding Label and Setting the Size of the Frame
        frame.setSize(700, 300);

        // Making the Frame visible
        frame.setVisible(true);

        // Action listener for Enter key or Button click
        bStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TicTacToe.Player[] players = TicTacToe.createPlayers(
                    playerX.getPlayerName(),
                    playerO.getPlayerName()
                );

                System.out.println("Starting game: " + players[0].getName() + " vs " + players[1].getName());
                TicTacToe.playGame(players[0], players[1]);
            }
        });

        // Using WindowListener for closing the window
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
    };
    
    public static void main(String[] args){
        renderMainMenu();
    };
}
