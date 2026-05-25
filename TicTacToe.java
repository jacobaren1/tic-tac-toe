public class TicTacToe {

    public static int countXO(char[] l, char c){
        int maxInARow = 0;
        int countC = 0;
        for (char x: l){
            boolean charInListIsC = (x==c);
            if (charInListIsC){
                countC ++;
                maxInARow=Math.max(maxInARow, countC);
            }
            else {
                countC = 0;
            };
        };

        return maxInARow;
    };

    public static boolean isFiveInARow(char[] l, char c){
        int countC = countXO(l, c);

        return (countC >= 5);
    }
    
    public static void countMyArray(){

        // Input parameters
        
        char [][] twoDimArray = {
            {'X','X','X','X','X'},
            {'O','O','O','O','O'},
            {'X','O','X','O','O'}
        };

        for (char[] myArray: twoDimArray){
            
            char myChar = 'X';
        
            // Result
            int inARowCount = countXO(myArray, myChar);
            boolean isFiveInARow = isFiveInARow(myArray, myChar);
            
            // Print
            String resultPrint = "";

            if (isFiveInARow) {
                resultPrint += "Congratulations, your array has (at least) 5 " + myChar + ":s in a row";
            }
            else {
                resultPrint += "Keep it up! Your array have " + inARowCount  + " " + myChar + ":s in a row";
            };
            
            System.out.println( resultPrint );
        }
    };

    public static void main(String[] args){
        countMyArray();
    };
};
