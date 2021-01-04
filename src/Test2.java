public class Test2 {
    public static void main(String[] args) {
        String[][] names = {{"Sam", "Carl", "Smith"}, {"stra", "Robert", "Smith"}, {"james", null, "Smith"},{"kate", "Robert", "Smith"}};

        String[][] board = Removenull(names, "Carl");
        //System.out.println(names[1][1] == null);


        for (int row = 0; row < names.length; row++) {
            for (int col = 0; col < names[row].length; col++) {
                System.out.printf("%10s", names[row][col]);

            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.printf("%10s", board[row][col]);

            }
            System.out.println();
        }


    }

    public static String[][] Removenull(String[][] array, String Header) {

        int row = array.length;
        int temp = row;
        int col = array[0].length;
        int colRemove = 0;
        int rowNew = temp;
        int DelRow = 0;


        for (int i = 0; i < row; i++) {
            if (i == 0) {
                for (int j = 0, currColumn = 0; j < col; j++) {
                    if (Header.equalsIgnoreCase(array[i][j])) {
                        colRemove = j;
                    }
                }
            }
            for (int j = 0; j < col; j++) {
                if (j == colRemove && array[i][j] == null) {
                    rowNew -= 1;
                    DelRow += 1;
                }
            }
        }
        int[] Deleterow = new int[DelRow];
        if(DelRow>0) {

            int k = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0, currColumn = 0; j < col; j++) {
                    if (j == colRemove && array[i][j] == null) {
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

