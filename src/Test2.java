public class Test2 {
    public static void main(String[] args) {
        String[][] names = {{"Sam", "Carl", "Smith"}, {"stra", "Robert", "Smith"}, {"james", null, "Smith"},{"kate", "Robert", "Smith"}};

        String[][] board = RangeRow(names, 3,1);
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

    public static String[][] RangeRow(String[][] array,int Max,int Min ) {

        int row = array.length;
        int temp = row;
        int col = array[0].length;
        String[][] newArray= new String[Max-Min+1][col];
        int currRow=1;
        for (int i = 0; i < row; i++) {
            if (i == 0) {
                for (int j = 0; j < col; j++) {
                    newArray[0][j]=array[i][j];
                }
            }
            if (i>=Min && i<Max) {
                for (int j = 0; j < col; j++) {
                    newArray[currRow][j]=array[i][j];
                    }
                currRow++;
                }
            }
        return newArray;


    }
}

