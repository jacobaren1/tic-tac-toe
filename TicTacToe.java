public class TicTacToe {

    public static int countXO(char[] l, char c){
        int countC = 0;
        for (char x: l){
            boolean charInListIsC = (x==c);
            if (charInListIsC){
                countC ++;
            }
        }
        return countC;
    };
    
    public static void countMyArray(){
        char[] myArray = {'0','0','X','0','0'};
        
        char myChar = 'X';
        int myArrayCount = countXO(myArray, myChar);
        System.out.println("My array with chars has " + myArrayCount + " " + myChar + ":s");
    };

    public static void main(String[] args){
        countMyArray();
    };
};