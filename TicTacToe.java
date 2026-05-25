public class TicTacToe {

    private static class BoardMatrix{

        private int m; // no rows in matrix
        private int n; // no columns in matrix
        private char[][] rows;
        private char[][] cols;
        private char[][] diagsPos;
        private char[][] diagsNeg;
        private boolean fiveInaRow;

        public BoardMatrix(int m, int n) {
            this.m = m;
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
            String outEnd;
            if (this.fiveInaRow){
                outEnd = "with five in a row";
            }
            else {
                outEnd = "with less than five in a row";
            }
            return "This is a " + m + "x" + n + " board-matrix " + outEnd;
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

                System.out.println("Checking five in a row...");

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

            System.out.println("Entering " + c + " at [" + i + "][" + j + "]");

            this.rows[i][j] = c;
            this.cols[j][i] = c;
            this.diagsNeg[i + j][i] = c;
            this.diagsNeg[i + (n-1-j)][i] = c;

            updateFiveInARow(c);
        };
    };

    public static void main(String[] args){

        BoardMatrix boardMatrix = new BoardMatrix(5, 5);

        for (int j = 0; j < 5; j++){
            boardMatrix.enterChar(0, j, 'X');
        };

        for (int i = 0; i < 5; i++){
            String rowString = i + ": ";
            for (int j = 0; j < 5; j++){
                rowString += boardMatrix.rows[i][j] + " ";
            }
            System.out.println( rowString );
        };

        System.out.println( boardMatrix );

    };
};
