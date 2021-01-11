import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Test{


    public static void main(String[] args) {
        String[][] Data = {{"Sam", "Carl", "Smith"}, {"1", "3", "9"}, {"7","2", "7"},{"2", "4", "6"}};

        for (int i = 0; i < Data.length; i++) {
            for (int j = 0; j < Data[i].length; j++) {
                System.out.print(Data[i][j]);
                if(j!= Data.length-2){
                    System.out.print(",");

                }

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