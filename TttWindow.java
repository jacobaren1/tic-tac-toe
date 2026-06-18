import java.awt.Frame;
import java.awt.Label;
import java.awt.Button;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Driver Class
public class TttWindow {
    // main function

    private static class PlayerSection {

        private Frame parent;
        public char boardChar;
        /**
         * Creates a part of a frame for as an aid to create a player
         *
         * @param parent parent awt.frame
         * @param boarChar the players char (playing piece)
         */
        public PlayerSection(Frame parent, char boardChar) {
            this.parent = parent;
            this.boardChar = boardChar;
        };

        public void render() {
            
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

            parent.add(section);
        };

    };
    public static void main(String[] args)
    {
        // Declaring a Frame and Label
        Frame frame = new Frame("Tic-Tac-Toe main menu");
        PlayerSection playerX = new PlayerSection( frame, 'X' );
        PlayerSection playerO = new PlayerSection( frame, 'O' );
        
        frame.setLayout(new GridLayout(2, 1, 0, 20));

        playerX.render();
        playerO.render();

        // Adding Label and Setting the Size of the Frame

        frame.setSize(700, 300);
        
        // Making the Frame visible
        frame.setVisible(true);
        
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
