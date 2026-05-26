public class TicTacToe {

    private static class BoardMatrix{

        private int n; // no columns in matrix
        private char[][] rows;
        private char[][] cols;
        private char[][] diagsPos;
        private char[][] diagsNeg;
        private boolean fiveInARow;

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

                    // Diagnoals from bottom left to upper right
                    this.diagsPos[i + j] = new char[
                        Math.min(i+j+1, maxDLength)
                    ];

                    // Diagnoals from bottom right to upper left
                    this.diagsNeg[i + (n-1-j)] = new char[
                        Math.min(i + (n-j), maxDLength)
                    ];
                };
            };
        };

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

        private static boolean isFiveInARow(char[] l, char c){
            int charCount = countChar(l, c);
            return (charCount >= 5);
        };

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

        public void enterChar (int i, int j, char c){

            this.rows[i][j] = c;
            this.cols[j][i] = c;
            this.diagsNeg[i + j][i] = c;
            this.diagsNeg[i + (n-1-j)][i] = c;

            updateFiveInARow(c);
        };
    };

    private static class Player{

        private String name;
        private char boardChar; // X or O
        private BoardMatrix board;
        private boolean theirTurn;

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
                };
            };

        };
    };
            
    public static void main(String[] args){

        BoardMatrix boardMatrix = new BoardMatrix(5, 5);

        Player spiderman = new Player("Superman", 'X', boardMatrix, true);
        Player batman = new Player("Batman", 'O', boardMatrix, false);

        spiderman.play(4,2, batman);
        batman.play(4,1, spiderman);

        spiderman.play(3,2, batman);
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
