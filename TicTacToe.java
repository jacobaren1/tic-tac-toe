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
        char[] myArray = {'X','O','X','0','0'};
        
        char myChar = 'X';
        int myArrayCount = countXO(myArray, myChar);
        System.out.println("My array with chars has " + myArrayCount + " " + myChar + ":s in a row");
        
    };

    public static void main(String[] args){
        countMyArray();
    };
};
