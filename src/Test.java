import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Test{


    public static void main(String[] args) {
        String[][] names = {{"Sam", "Carl", "joe","Smith"}, {"stra", "Robert", "Smith","Sam", "Carl", "Smith","joe"}, {"james", null, "Smith","Sam", "Carl", "Smith"},{"kate", "Robert", "Smith","Sam", "Carl", "Smith"}};
        String[] Header ={"Sam","Smith","carl","joe"};
        String[][] board = RangeCol(names, Header);
        //System.out.println(names[1][1] == null);


       /* for (int row = 0; row < names.length; row++) {
            for (int col = 0; col < names[row].length; col++) {
                System.out.printf("%10s", names[row][col]);

            }
            System.out.println();
        }*/
        System.out.println();
        System.out.println();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.printf("%10s", board[row][col]);

            }
            System.out.println();
        }

    }
    public static String[][] RangeCol(String[][] array,String[] Header) {
        int row = array.length;
        int col = array[0].length;
        int RemoveNum=0;
        int k =0;
        //new Array will have one column less
        for (int i = 0; i < row; i++) {
            if (i == 0) {
                for (int j = 0; j < col ; j++) {
                    for (int p = 0; p < Header.length;p++) {
                        String Head = Header[p];
                        if (Head.equalsIgnoreCase(array[i][j])) {

                            RemoveNum++;
                        }
                    }
                }
            }
            break;
        }
        int[] Remove= new int[RemoveNum];
        for (int i = 0; i < row; i++) {
            if (i == 0) {
                for (int j = 0; j < col ; j++) {
                    for (int p = 0; p < Header.length;p++) {
                        String Head = Header[p];
                        if (Head.equalsIgnoreCase(array[i][j])) {

                            Remove[k] = j;
                            k++;
                        }
                    }
                }
            }
            break;
        }
        System.out.println(Arrays.toString(Remove));
        String[][] newArray = new String[row][RemoveNum];
        System.out.println();

        for (int i = 0; i < row; i++) {
            for (int j = 0, currColumn = 0; j < col; ) {
                for ( k = 0; k < Remove.length; k++) {
                    if (j == Remove[k]) {
                        newArray[i][currColumn++] = array[i][j];
                    }
                }
                j++;

            }
        }




        return newArray;
    }
}