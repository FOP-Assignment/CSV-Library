import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tets{

    public static void main(String[] args) {
        String[][] names = {{"Sam", "Carl", "joe"}, {"stra", "Robert", "Smith"}, {"james", null, "Smith"},{"kate", "Robert", "Smith"}};

        String[][] sull= names;
        System.out.println(Arrays.deepToString(sull));

        for (int row = 0; row < names.length; row++) {
            for (int col = 0; col < names[row].length; col++) {
                System.out.printf("%10s", names[row][col]);

            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

        for (int row = 0; row < sull.length; row++) {
            for (int col = 0; col < sull[row].length; col++) {
                System.out.printf("%10s", sull[row][col]);

            }
            System.out.println();
        }

    }


   /* public static String[][] removeDuplicate(String[][] matrix) {
        String[][] newMatrix = new String[matrix.length][matrix[0].length];
        int newMatrixRow = 1;

        for (int i = 0; i < matrix[0].length; i++)
            newMatrix[0][i] = matrix[0][i];

        for (int j = 1; j < matrix.length; j++) {
            List<Boolean> list = new ArrayList<>();
            for (int i = 0; newMatrix[i][0] != null; i++) {
                boolean same = true;
                for (int col = 2; col < matrix[j].length; col++) {
                    if (!newMatrix[i][col].equals(matrix[j][col])) {
                        same = false;
                        break;
                    }
                }
                list.add(same);
                System.out.println(list);
            }

            if (!list.contains(true)) {
                for (int i = 0; i < matrix[j].length; i++) {
                    newMatrix[newMatrixRow][i] = matrix[j][i];
                }
                newMatrixRow++;
            }
        }

        int i;
        for(i = 0; newMatrix[i][0] != null; i++);

        String finalMatrix[][] = new String[i][newMatrix[0].length];
        for (i = 0; i < finalMatrix.length; i++) {
            for (int j = 0; j < finalMatrix[i].length; j++)
                finalMatrix[i][j] = newMatrix[i][j];
        }

        return finalMatrix;
    }*/

    public static String[][] RemoveDuplicate (String[][] array, String Header) {

        int row = array.length;
        int temp = row;
        int col = array[0].length;
        int colRemove = 0;
        int rowNew = temp;
        int DelRow = 0;
        String[] CompareSet=new String[row];


        for (int i = 0; i < row; i++) {
            if (i == 0) {
                for (int j = 0, currColumn = 0; j < col; j++) {
                    if (Header.equalsIgnoreCase(array[i][j])) {
                        colRemove = j;
                    }
                }
            }
            for (int j = 0; j < col; j++) {
                if(j==colRemove){


                }
            }
        }
        int[] Deleterow = new int[DelRow];
        if(DelRow>0) {

            int k = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0, currColumn = 0; j < col; j++) {
                    if (j == colRemove && array[i][j].isEmpty()) {
                        Deleterow[k++] = i;

                    }

                }

            }
        }
        System.out.println(rowNew);

        int currrow=0;
        String[][] newArray = new String[rowNew][col];
        for (int i = 0; i < row; i++) {
            boolean Est = true;
            for (int l = 0; l < Deleterow.length; l++) {
                if (Deleterow[l] == i) {
                    Est = false;
                }
            }
            if (Est) {
                for (int j = 0,currcolumn=0; j < col; j++) {
                    newArray[currrow][currcolumn++] = array[i][j];
                }
                currrow++;
            }
        }
        return newArray;
    }

}