
public class tets{

    public static void main(String[] args) {
        String[][] names = {{"Sam", "Carl", "joe"}, {"stra", "Robert", "Smith"}, {"stra","james", "Carl", "Smith"},{"kate", "Robert", "Smith"}};
        String[][] sull= RemoveDuplicate(names,"joe");

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
    public static String[][] RemoveDuplicate (String[][] array, String Header) {
        int row = array.length;
        int col = array[0].length;
        int colHeader = 0;
        int Duplicatecnt=0;
        String[] CompareSet = new String[row];
        for (int i = 0; i < row; i++) {
            if (i == 0) {
                for (int j = 0, currColumn = 0; j < col; j++) {
                    if (Header.equalsIgnoreCase(array[i][j])) {
                        colHeader = j;
                    }
                }
            }
            for (int j = 0; j < col; j++) {
                if (j == colHeader) {
                    CompareSet[i] = array[i][j];
                }
            }
        }
            for (int i = 0; i < CompareSet.length; i++) {
                for (int j = 0; j < CompareSet.length; j++) {
                    if (CompareSet[i].equals(CompareSet[j]) && i != j) {
                        Duplicatecnt++;
                    }
                }
            }
        Duplicatecnt/=2;
            int[] OrRow = new int[CompareSet.length-Duplicatecnt];
            String[] CompareSet2 = new String[CompareSet.length-Duplicatecnt];
            int currRow=0;
        for (int i = 0; i < CompareSet.length; i++) {
            boolean Exist =false;
            for (int j=0; j < CompareSet2.length; j++) {
                if (CompareSet[i].equalsIgnoreCase(CompareSet2[j])) {
                   Exist=true;
                }
            }
            if(Exist==false){
                OrRow[currRow]=i;
                CompareSet2[currRow]=CompareSet[i];
                currRow++;
            }
        }
        String[][] newArray =new String[OrRow.length][col];
        int newRow=0;
        for (int i = 0; i< array.length; i++) {
            for(int k =0;k<OrRow.length;k++) {
                if(i==OrRow[k]) {
                    for (int j = 0; j < col; j++) {
                        newArray[newRow][j]=array[i][j];
                    }
                    newRow++;
                }
            }
        }
       return newArray;
    }
}