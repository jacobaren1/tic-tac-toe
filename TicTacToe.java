/**
 * A simple TicTacToe game implementation with a nested board matrix and players.
 *
 * <p>This class contains the game entry point and the supporting nested classes
 * for maintaining the board state and handling player moves.</p>
 */
public class TicTacToe {

    /**
     * Represents the board data structure for the TicTacToe game.
     */
    private static class BoardMatrix{

        private int n; // no columns in matrix
        private char[][] rows;
        private char[][] cols;
        private char[][] diagsPos;
        private char[][] diagsNeg;
        private boolean fiveInARow;

        /**
         * Creates a board matrix with the given number of rows and columns.
         *
         * @param m the number of rows
         * @param n the number of columns
         */
        public BoardMatrix(int m, int n) {
            this.n = n;
            this.rows = new char[m][n];
            this.cols = new char[n][m];
            this.diagsPos = new char[m+n-1][];
            this.diagsNeg = new char[m+n-1][];
            this.fiveInARow = false;

            int maxDLength = Math.max(m,n);

            for (int i = 0; i < m; i++){

                for (int j = 0; j < n; j++){

                    // Diagonals from bottom left to upper right
                    this.diagsPos[i + j] = new char[
                        Math.min(i+j+1, maxDLength)
                    ];

                    // Diagonals from bottom right to upper left
                    this.diagsNeg[i + (n-1-j)] = new char[
                        Math.min(i + (n-j), maxDLength)
                    ];
                };
            };
        };

        /**
         * Returns a string representation of the board.
         *
         * @return the board as a formatted string
         */
        @Override
        public String toString(){
            
            String finalOutput = "";
            String rowString = "";
            
            finalOutput = "i/j";

            for (int j = 0; j < this.n; j++){
                finalOutput += " " + j;
            };
            finalOutput += "\n";

            for (int i = 0; i < 5; i++){
                
                rowString = i + ": ";

                for (int j = 0; j < 5; j++){
                    if ( this.rows[i][j] != Character.MIN_VALUE){
                        rowString += " " + this.rows[i][j];
                    } else {
                        rowString += " _";
                    };
                }

                finalOutput += rowString + "\n";
            };

            return finalOutput;
        };

        /**
         * Counts the longest consecutive run of the specified character.
         *
         * @param a the array to inspect
         * @param c the character to count
         * @return the maximum number of consecutive c values in the array
         */
        private static int countChar(char[] a, char c){
            int maxInARow = 0;
            int charCount = 0;
            for (char x: a){
                boolean charInListIsC = (x==c);
                if (charInListIsC){
                    charCount ++;
                    maxInARow=Math.max(maxInARow, charCount);
                }
                else {
                    charCount = 0;
                };
            };

            return maxInARow;
        };

        /**
         * Returns true when the array contains five or more consecutive occurrences
         * of the specified character.
         *
         * @param l the array to inspect
         * @param c the character to check
         * @return true if five or more consecutive characters are found
         */
        private static boolean isFiveInARow(char[] l, char c){
            int charCount = countChar(l, c);
            return (charCount >= 5);
        };

        /**
         * Updates the winning state when the specified character appears five in a row
         * in any row, column, or diagonal.
         *
         * @param c the character to check for a winning run
         */
        private void updateFiveInARow(char c){

            char[][][] cubicArray = {
                this.rows,
                this.cols,
                this.diagsPos,
                this.diagsNeg
            };

            int cubicArrayIter = 0;
            int charArraysIter;
            char[][] charArrays;
            char[] charArray;

            while (!this.fiveInARow && cubicArrayIter < cubicArray.length){

                charArraysIter = 0;
                charArrays = cubicArray[ cubicArrayIter ];

                while (!this.fiveInARow && charArraysIter < charArrays.length){
                    
                    charArray = charArrays[ charArraysIter ];
                    
                    this.fiveInARow = isFiveInARow(charArray, c);

                    charArraysIter++;
                };
                cubicArrayIter++;
            };
        };

        /**
         * Places a character on the board and updates the win state.
         *
         * @param i the row index
         * @param j the column index
         * @param c the character to place
         */
        public void enterChar (int i, int j, char c){

            this.rows[i][j] = c;
            this.cols[j][i] = c;
            this.diagsNeg[i + j][i] = c;
            this.diagsNeg[i + (n-1-j)][i] = c;

            updateFiveInARow(c);
        };
    };

    /**
     * Represents a player in the TicTacToe game.
     */
    private static class Player{

        private String name;
        private char boardChar; // X or O
        private BoardMatrix board;
        private boolean theirTurn;

        /**
         * Creates a new player.
         *
         * @param name the player's name
         * @param boardChar the character the player places on the board
         * @param board the shared game board
         * @param theirTurn true when it is this player's turn
         */
        public Player(
            String name,
            char boardChar,
            BoardMatrix board,
            boolean theirTurn
        ){
            this.name = name;
            this.boardChar = boardChar;
            this.board = board;
            this.theirTurn = theirTurn;
        }
            
        /**
         * Attempts to play a move at the given coordinates.
         *
         * @param i the row index
         * @param j the column index
         * @param opponent the opposing player
         */
        public void play(int i, int j, Player opponent){

            boolean gameOver = board.fiveInARow;
            if (!gameOver) {
                if (this.theirTurn && board.rows[i][j] == Character.MIN_VALUE) {
                    board.enterChar(i, j, this.boardChar);

                    this.theirTurn = false;

                    if (board.fiveInARow) {
                        System.out.println(this.name + " won the game!");
                    } else {
                        opponent.theirTurn = true;
                    };


                } else if (!this.theirTurn){
                    System.out.println( "For gods sake, " + this.name + "! Wait for your turn!" );
                } else if (board.rows[i][j] != Character.MIN_VALUE) {
                    System.out.println( String.format("You have to pick an empty spot, %s. Please try again!", this.name ));
                };
            };

        };
    };
            
    /**
     * Starts the TicTacToe game.
     *
     * @param args optional command line arguments
     */
    public static void main(String[] args){

        BoardMatrix boardMatrix = new BoardMatrix(5, 5);

        Player spiderman = new Player("Superman", 'X', boardMatrix, true);
        Player batman = new Player("Batman", 'O', boardMatrix, false);

        spiderman.play(4,2, batman);
        batman.play(4,1, spiderman);

        spiderman.play(3,2, batman);
        batman.play(4,1,spiderman);
        batman.play(3,1,spiderman);

        spiderman.play(2,2, batman);
        batman.play(2,1,spiderman);

        spiderman.play(1,2, batman);
        batman.play(1,1,spiderman);
        
        // Sneaky batman
        batman.play(0,0, spiderman);

        if (!boardMatrix.fiveInARow){
            spiderman.play(0,2, batman);
        };

        if (!boardMatrix.fiveInARow){
            batman.play(0,1,spiderman);
        };

        System.out.println( boardMatrix );

    };
};
