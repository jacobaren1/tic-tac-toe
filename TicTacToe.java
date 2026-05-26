public class TicTacToe {

    private static class BoardMatrix{

        private int n; // no columns in matrix
        private char[][] rows;
        private char[][] cols;
        private char[][] diagsPos;
        private char[][] diagsNeg;
        private boolean fiveInaRow;

        public BoardMatrix(int m, int n) {
            this.n = n;
            this.rows = new char[m][n];
            this.cols = new char[n][m];
            this.diagsPos = new char[m+n-1][];
            this.diagsNeg = new char[m+n-1][];
            this.fiveInaRow = false;

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

            if (this.fiveInaRow) {
                finalOutput += "\nThis matrix has at least five in a row!";
            } else {
                finalOutput += "\nThis matrix does not have five in a row!";
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

            while (!this.fiveInaRow && cubicArrayIter < cubicArray.length){

                charArraysIter = 0;
                charArrays = cubicArray[ cubicArrayIter ];

                while (!this.fiveInaRow && charArraysIter < charArrays.length){
                    
                    charArray = charArrays[ charArraysIter ];
                    
                    this.fiveInaRow = isFiveInARow(charArray, c);

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

    public static void main(String[] args){

        BoardMatrix boardMatrix = new BoardMatrix(5, 5);

        for (int i = 0; i < 5; i++){

            for (int j= 0; j < 5; j++){
                if (i + (5-1-j) == 4) {
                    boardMatrix.enterChar(i, j, 'X');
                } else {
                    boardMatrix.enterChar(i,j, '_');
                }

            }
        };

        System.out.println( boardMatrix );

    };
};
