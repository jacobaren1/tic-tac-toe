import java.awt.Frame;
import java.awt.Label;
import java.awt.Button;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Driver Class
public class TttWindow {
    // main function

    private static class PlayerSection {
        public char boardChar;

        /**
         * Creates a part of a frame for as an aid to create a player
         *
         * @param boarChar the players char (playing piece)
         */
        public PlayerSection(char boardChar) {
            this.boardChar = boardChar;
        }

        public void render(Panel parentPanel) {
            Panel section = new Panel(new GridLayout(3, 1, 0, 10));
            Label inputHelp = new Label(String.format("Enter player name for player '%c'!", this.boardChar));
            TextField playerNameInput = new TextField(20);
            Button submitButton = new Button("Submit");
            Label showPlayerName = new Label();

            Panel inputRow = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            inputRow.add(playerNameInput);
            inputRow.add(submitButton);

            // Action listener for Enter key or Button click
            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showPlayerName.setText("Welcome " + playerNameInput.getText());
                }
            };

            playerNameInput.addActionListener(actionListener);
            submitButton.addActionListener(actionListener);

            inputHelp.setAlignment(Label.CENTER);
            showPlayerName.setAlignment(Label.CENTER);

            section.add(inputHelp);
            section.add(inputRow);
            section.add(showPlayerName);

            parentPanel.add(section);
        }
    }

    public static void main(String[] args)
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
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        };

        bStartGame.addActionListener(actionListener);

        // Using WindowListener for closing the window
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
    }
}
