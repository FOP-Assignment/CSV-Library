import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Table {
    private String Path, Regex;
    private int TableNum;
    private String[][] data;


    public Table(String path, int tableNum, String regex) {
        File f = new File(path);
        if (f.exists()) {
            Path = path;
        } else {
            try {
                PrintWriter newfile = new PrintWriter(new FileOutputStream(path));
                newfile.println("");
                Path = path;
            } catch (IOException e) {
                System.out.println("Problem with file output");
            }

        }
        TableNum = tableNum;
        Regex = regex;
        data = GetArray(getPath(), getTableNum(), getRegex());

    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getRegex() {
        return Regex;
    }

    public void setRegex(String regex) {
        this.Regex = regex;
    }

    public int getTableNum() {
        return TableNum;
    }

    public void setTableNum(int tableNum) {
        TableNum = tableNum;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }
//MAIN FUCTION--------------------------------------------------------------------------------------------------------------------------------

    //1)To View Table
    public void ViewTable() {
        tableWithLines(getData());
    }

    //2)To Add new Table
    public void AddTable(String[][] Input) {
        NewTable(getPath(), Input, getRegex());
    }

    //3)To Remove Certain column in view
    public void RemoveColumnView(String Header) {
        RemoveColV(getData(), Header);
    }

    //4)To Remove Certain column in object
    public void RemoveColumnSet(String Header) {
        RemoveColS(getData(), Header);
    }

    //5)To Remove Certain row with empty column in view
    public void RemoveNullSet(String Header) {
        RemovenullS(getData(), Header);
    }

    //5)To Remove Certain row with empty column in object
    public void RemoveNullView(String Header) {
        RemovenullV(getData(), Header);
    }
    //6)To Concatenate Two table


    public String[][] Concatenate(String[][] FirstTable, String[][] SecondTable, char Type_H_or_V) {

        String Concated[][] = Concat(FirstTable, SecondTable, Type_H_or_V);
        return Concated;
    }

    public  void RangeColumnView(String HeaderRange[]){
        RangeColView(getData(),HeaderRange);
    }


    //
//---------------------------------------------------------------------------------------------------------------------------------------------------

    public String[][] GetArray(String file, int TableNum, String regex) {
        int i = 0;
        int tablenum = TableNum;
        //for new table
        File newFile = new File(file);
        if (newFile.length() == 0) {
            //String[][] Element={};
            // return Element;
            return null;
        }

        int Headerlength = GetTableDetail(file, tablenum, regex)[1];
        int Tableline = GetTableDetail(file, tablenum, regex)[0];
        int l = 0;
        String[][] Element = new String[Tableline][Headerlength];


        try {
            Scanner in2 = new Scanner(new FileInputStream(file));

            while (in2.hasNextLine()) {
                String[] current = ((in2.nextLine()).split(regex));
                //System.out.println("["+l+"]");

                if (i == tablenum - 1 && l < Tableline) {

                    for (int k = 0; k < Headerlength; ) {
                        Element[l][k] = current[k];
                        //System.out.print(k);
                        k++;
                    }
                    l++;

                }

                if (current[0].equals("")) {
                    i++;
                }
            }

            in2.close();
        } catch (FileNotFoundException e) {


        }
        return Element;

    }


    public int[] GetTableDetail(String file, int TableNum, String regex) {
        int i = 0;
        int j = 0;
        int tablenum = TableNum;
        int headerlength = 0;
        int tableline = 0;

        try {


            Scanner in = new Scanner(new FileInputStream(file));

            while (in.hasNextLine()) {
                String[] current = ((in.nextLine()).split(regex));


                if (i == tablenum - 1) {
                    if (j == 0) {
                        headerlength = current.length;
                        j = 1;

                    }
                    tableline++;
                }

                if (current[0].equals("")) {
                    i++;
                }
            }

            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        tableline -= 1;//To eliminate empty line count
        int[] Details = {tableline, headerlength};
        return Details;
    }


    public void tableWithLines(String[][] table) {
        /*
         * leftJustifiedRows - If true, it will add "-" as a flag to format string to
         * make it left justified. Otherwise right justified.
         */
        boolean leftJustifiedRows = false;

        /*
         * Table to print in console in 2-dimensional array. Each sub-array is a row.
         */


        /*
         * Calculate appropriate Length of each column by looking at width of data in
         * each column.
         *
         * Map columnLengths is <column_number, column_length>
         */
        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            if (columnLengths.get(i) == null) {
                columnLengths.put(i, 0);
            }
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));
        //System.out.println("columnLengths = " + columnLengths);

        /*
         * Prepare format String
         */
        final StringBuilder formatString = new StringBuilder("");
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
        formatString.append("|\n");
        // System.out.println("formatString = " + formatString.toString());

        /*
         * Prepare line for top, bottom & below header row.
         */
        String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
            String templn = "+-";
            templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
                    (a1, b1) -> a1 + b1);
            templn = templn + "-";
            return ln + templn;
        }, (a, b) -> a + b);
        line = line + "+\n";
        //System.out.println("Line = " + line);

        /*
         * Print table
         */
        System.out.print(line);
        Arrays.stream(table).limit(1).forEach(a -> System.out.printf(formatString.toString(), a));
        System.out.print(line);

        Stream.iterate(1, (i -> i < table.length), (i -> ++i))
                .forEach(a -> System.out.printf(formatString.toString(), table[a]));
        System.out.print(line);
    }

    public void NewTable(String Outputfile, String[][] InputTable, String regex) {

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(Outputfile, true));
            for (int row = 0; row < InputTable.length; row++) {
                for (int col = 0; col < InputTable[row].length; col++) {
                    out.print(InputTable[row][col]);
                    out.print(regex);
                }
                out.println();
            }
            out.println();

            out.close();
        } catch (IOException e) {
            System.out.println("Problem with file output");
        }

        try {
            Scanner in2 = new Scanner(new FileInputStream(Outputfile));
            int i = 0;
            if (in2.hasNextLine() == false) {
                in2.close();
            }
            while (in2.hasNextLine()) {

                String[] current = ((in2.nextLine()).split(","));


                if (current[0].equals("")) {
                    i++;
                }
            }

            in2.close();

            setTableNum(i);
            System.out.println(getTableNum());
            setData(GetArray(getPath(), getTableNum(), getRegex()));
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }

        ViewTable();

    }

    //New-------------------------------------------------------------------------------------------------------------
    public void RemoveColV(String[][] array, String Header) {

        int row = array.length;
        int col = array[0].length;
        int colRemove = 0;

        String[][] newArray = new String[row][col - 1]; //new Array will have one column less
        for (int i = 0; i < row; i++) {
            if (i == 0) {
                for (int j = 0, currColumn = 0; j < col; j++) {
                    if (Header.equalsIgnoreCase(array[i][j])) {
                        colRemove = j;
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0, currColumn = 0; j < col; j++) {
                if (j != colRemove) {
                    newArray[i][currColumn++] = array[i][j];
                }
            }
        }

        tableWithLines(newArray);
    }

    //New-------------------------------------------------------------------------------------------------------------
    public void RemoveColS(String[][] array, String Header) {

        int row = array.length;
        int col = array[0].length;
        int colRemove = 0;

        String[][] newArray = new String[row][col - 1]; //new Array will have one column less
        for (int i = 0; i < row; i++) {
            if (i == 0) {
                for (int j = 0, currColumn = 0; j < col; j++) {
                    if (Header.equalsIgnoreCase(array[i][j])) {
                        colRemove = j;
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0, currColumn = 0; j < col; j++) {
                if (j != colRemove) {
                    newArray[i][currColumn++] = array[i][j];
                }
            }
        }

        setData(newArray);
        ViewTable();
    }

    //New-------------------------------------------------------------------------------------------------------------
    public void RemovenullS(String[][] array, String Header) {

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
                if (j == colRemove && array[i][j].isEmpty()) {
                    rowNew -= 1;
                    DelRow += 1;
                }
            }
        }
        int[] Deleterow = new int[DelRow];
        if (DelRow > 0) {

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

        int currrow = 0;
        String[][] newArray = new String[rowNew][col];
        for (int i = 0; i < row; i++) {
            boolean Est = true;
            for (int l = 0; l < Deleterow.length; l++) {
                if (Deleterow[l] == i) {
                    Est = false;
                }
            }
            if (Est) {
                for (int j = 0, currcolumn = 0; j < col; j++) {
                    newArray[currrow][currcolumn++] = array[i][j];
                }
                currrow++;
            }
        }
        setData(newArray);
        ViewTable();
    }

    //----------------------------------------------------------------------------------------------------------------------------------
    public void RemovenullV(String[][] array, String Header) {

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
                if (j == colRemove && array[i][j].isEmpty()) {
                    rowNew -= 1;
                    DelRow += 1;
                }
            }
        }
        int[] Deleterow = new int[DelRow];
        if (DelRow > 0) {

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

        int currrow = 0;
        String[][] newArray = new String[rowNew][col];
        for (int i = 0; i < row; i++) {
            boolean Est = true;
            for (int l = 0; l < Deleterow.length; l++) {
                if (Deleterow[l] == i) {
                    Est = false;
                }
            }
            if (Est) {
                for (int j = 0, currcolumn = 0; j < col; j++) {
                    newArray[currrow][currcolumn++] = array[i][j];
                }
                currrow++;
            }
        }
        tableWithLines(newArray);
    }


    //Concatenate method-----------------------------------------------------------------------------------------------------------------------------------
    public static String[][] Concat(String[][] FirstArray, String[][] SecondArray, char Type_H_or_V) {
        byte Type;
        if (Type_H_or_V == 'H' || Type_H_or_V == 'h') {
            Type = 1;
        } else {
            Type = 0;
        }

        String[][] data = Two_d_OtoS(arrayConcat(FirstArray, SecondArray, Type));
        return data;

    }

    public static Object[][] Two_d_StoO(String[][] Data) {
        Object[][] OData = new Object[Data.length][Data[0].length];
        for (int row = 0; row < Data.length; row++) {
            for (int col = 0; col < Data[row].length; col++) {
                OData[row][col] = Data[row][col].toString();

            }
        }
        return OData;
    }


    public static String[][] Two_d_OtoS(Object[][] Data) {
        String[][] SData = new String[Data.length][Data[0].length];
        for (int row = 0; row < Data.length; row++) {
            for (int col = 0; col < Data[row].length; col++) {
                SData[row][col] = Data[row][col].toString();

            }
        }
        return SData;


    }


    public static final byte ARRAY_CONCAT_HORIZ = 0, ARRAY_CONCAT_VERT = 1;

    public static Object[][] arrayConcat(String[][] SDataA, String[][] SDataB, byte concatDirection) {

        Object[][] a = Two_d_StoO(SDataA);
        Object[][] b = Two_d_StoO(SDataB);


        if (concatDirection == ARRAY_CONCAT_HORIZ && a[0].length == b[0].length) {
            return Arrays.stream(arrayConcat(a, b)).map(Object[].class::cast)
                    .toArray(Object[][]::new);
        } else if (concatDirection == ARRAY_CONCAT_VERT && a.length == b.length) {
            Object[][] arr = new Object[a.length][a[0].length + b[0].length];
            for (int i = 0; i < a.length; i++) {
                arr[i] = arrayConcat(a[i], b[i]);
            }

            return arr;
        } else
            throw new RuntimeException("Attempted to concatenate arrays of incompatible sizes.");
    }

    /*
     * Concatenates 2 1D arrays
     */
    public static Object[] arrayConcat(Object[] a, Object[] b) {
        Object[] arr = new Object[a.length + b.length];
        System.arraycopy(a, 0, arr, 0, a.length);
        System.arraycopy(b, 0, arr, a.length, b.length);
        return arr;
    }


//GetColumn--------------------------------------------------------------------------------------------------------------------------

    public void RangeColView(String[][] array, String[] Header) {
        int row = array.length;
        int col = array[0].length;
        int RemoveNum = 0;
        int k = 0;
        //new Array will have one column less
        for (int i = 0; i < row; i++) {
            if (i == 0) {
                for (int j = 0; j < col; j++) {
                    for (int p = 0; p < Header.length; p++) {
                        String Head = Header[p];
                        if (Head.equalsIgnoreCase(array[i][j])) {

                            RemoveNum++;
                        }
                    }
                }
            }
            break;
        }
        int[] Remove = new int[RemoveNum];
        for (int i = 0; i < row; i++) {
            if (i == 0) {
                for (int j = 0; j < col; j++) {
                    for (int p = 0; p < Header.length; p++) {
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
                for (k = 0; k < Remove.length; k++) {
                    if (j == Remove[k]) {
                        newArray[i][currColumn++] = array[i][j];
                    }
                }
                j++;

            }
        }
        tableWithLines(newArray);
    }















































}



