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

    //7)To select certain column of the table
    public void SelectColumnView(String HeaderRange[]) {
        SelectColumn(getData(), HeaderRange);
    }

    //7)To select range of row from the table
    public void RangeOfRowView(int Min, int Max) {
        RangeRow(getData(), Min, Max);
    }
    //7)To select range of row from the table
    public void RemoveDuplicateView(String Header) {
       RemoveDupView(getData(),Header);
    }
    //7)To select range of row from the table
    public void RemoveDuplicateSet(String Header) {
        RemoveDupSet(getData(),Header);
    }

    public  void ComputeData(String Operation,String Header,int Min,int Max){
        System.out.println(ComputeStatistic(getData(),Operation,Header,Min,Max));
    }
    public void FillBlankView (String Header,String Input){
        tableWithLines(fillInBlank(getData(),Header,Input));
    }
    public void FillBlankSet (String Header,String Input){
        setData(fillInBlank(getData(),Header,Input));
    }
    public void SorterView (String Header){
        tableWithLines(Sort(getData(),Header));
    }
    public void SorterSet (String Header){
        setData(Sort(getData(),Header));
        tableWithLines(getData());

    }
    public void StandardScaling(String Header,int Min,int Max){
       double[] Scaled=stdscale(getData(),Header,Min,Max);
       for(int i=0;i< Scaled.length;i++){
           System.out.println(Scaled[i]);
       }
    }
    public void MinMAxScalling(String Header,int Min,int Max){
        double[] Scaled=minmaxscale(getData(),Header,Min,Max);
        for(int i=0;i< Scaled.length;i++){
            System.out.println(Scaled[i]);
        }
    }
    public void KNNClassifier(int X1,int Y1,int X2,int Y2,double[] SampleCoordinate,String FirstClassName,String SecondClassName,int k){
        KnnClassifier(X1,Y1,X2,Y2,SampleCoordinate,FirstClassName,SecondClassName,getData(),k);
    }

    public  void KNNRegressor(String Header1,String Header2,double CoordinateAxisX,int k){
        System.out.println(Arrays.toString(KnnRegressor(getData(),Header1,Header2,CoordinateAxisX,k)));

    }

    public void UpdateCSV(){
        save(getPath(),getTableNum(),getData());
    }


//---------------------------------------------------------------------------------------------------------------------------------------------------


    public String[][] GetArray(String file, int TableNum, String regex) {
        int i = 0;
        int tablenum = TableNum;

        File newFile = new File(file);
        if (newFile.length() == 0) {
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

    //RemoveNull View----------------------------------------------------------------------------------------------------------------------------------
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

    public void SelectColumn(String[][] array, String[] Header) {
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

    //RangeRow----------------------------------------------------------------------------------------------------------------------------------------
    public void RangeRow(String[][] array, int Min, int Max) {

        int row = array.length;
        int temp = row;
        int col = array[0].length;
        String[][] newArray = new String[Max - Min + 1][col];
        int currRow = 1;
        for (int i = 0; i < row; i++) {
            if (i == 0) {
                for (int j = 0; j < col; j++) {
                    newArray[0][j] = array[i][j];
                }
            }
            if (i >= Min && i < Max) {
                for (int j = 0; j < col; j++) {
                    newArray[currRow][j] = array[i][j];
                }
                currRow++;
            }
        }
        tableWithLines(newArray);
    }

    //RemoveDuplicateSet-------------------------------------------------------------------------------------------------------------------------
    public void RemoveDupSet(String[][] array, String Header) {
        int row = array.length;
        int col = array[0].length;
        int colHeader = 0;
        int Duplicatecnt = 0;
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
        Duplicatecnt /= 2;
        int[] OrRow = new int[CompareSet.length - Duplicatecnt];
        String[] CompareSet2 = new String[CompareSet.length - Duplicatecnt];
        int currRow = 0;
        for (int i = 0; i < CompareSet.length; i++) {
            boolean Exist = false;
            for (int j = 0; j < CompareSet2.length; j++) {
                if (CompareSet[i].equalsIgnoreCase(CompareSet2[j])) {
                    Exist = true;
                }
            }
            if (Exist == false) {
                OrRow[currRow] = i;
                CompareSet2[currRow] = CompareSet[i];
                currRow++;
            }
        }
        String[][] newArray = new String[OrRow.length][col];
        int newRow = 0;
        for (int i = 0; i < array.length; i++) {
            for (int k = 0; k < OrRow.length; k++) {
                if (i == OrRow[k]) {
                    for (int j = 0; j < col; j++) {
                        newArray[newRow][j] = array[i][j];
                    }
                    newRow++;
                }
            }
        }
        tableWithLines(newArray);
        setData(newArray);
    }

    //RemoveDuplicateView-------------------------------------------------------------------------------------------------------------------------
    public void RemoveDupView(String[][] array, String Header) {
        int row = array.length;
        int col = array[0].length;
        int colHeader = 0;
        int Duplicatecnt = 0;
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
        Duplicatecnt /= 2;
        int[] OrRow = new int[CompareSet.length - Duplicatecnt];
        String[] CompareSet2 = new String[CompareSet.length - Duplicatecnt];
        int currRow = 0;
        for (int i = 0; i < CompareSet.length; i++) {
            boolean Exist = false;
            for (int j = 0; j < CompareSet2.length; j++) {
                if (CompareSet[i].equalsIgnoreCase(CompareSet2[j])) {
                    Exist = true;
                }
            }
            if (Exist == false) {
                OrRow[currRow] = i;
                CompareSet2[currRow] = CompareSet[i];
                currRow++;
            }
        }
        String[][] newArray = new String[OrRow.length][col];
        int newRow = 0;
        for (int i = 0; i < array.length; i++) {
            for (int k = 0; k < OrRow.length; k++) {
                if (i == OrRow[k]) {
                    for (int j = 0; j < col; j++) {
                        newArray[newRow][j] = array[i][j];
                    }
                    newRow++;
                }
            }
        }
        tableWithLines(newArray);
    }

    public static String[] SelectRange(String[][] array,String  Header,int Min,int Max) {
        int row = array.length;
        int col = array[0].length;
        int RemoveNum = 0;

        String[] newArray = new String[Max-Min+1];
        int currRow=0;
        for (int i = 0; i < row; i++) {
            if (i == 0) {
                for (int j = 0; j < col; j++) {

                    if (Header.equalsIgnoreCase(array[i][j])) {
                        RemoveNum=j;

                    }

                }
            }
            if (i >= Min && i <= Max) {
                for (int j = 0; j < col; j++) {

                    if (j == RemoveNum) {
                        newArray[currRow++] = array[i][j];

                    }

                }
            }
        }
        return newArray;
    }

    public static double[] SarrayTodouble (String[] arrString) {
        double[] arrDouble = new double[arrString.length];
        for(int i=0; i<arrString.length; i++)
        {
            arrDouble[i] = Double.parseDouble(arrString[i]);
        }
        return arrDouble;
    }


    public static double ComputeStatistic (String[][] array,String Operation,String Header,int Min,int Max) {

        String[] range = SelectRange(array,Header,Min,Max);
        double[] Value =SarrayTodouble(range);
        double Answer =0;

        if(Operation.equalsIgnoreCase("Varience")){
            Answer=variance(Value);

        }
        else if(Operation.equalsIgnoreCase("StandardDeviation")){
            Answer=standardDeviation(Value);

        }
        else if(Operation.equalsIgnoreCase("Max")){
            Answer=max(Value);

        }
        else if(Operation.equalsIgnoreCase("Min")){
            Answer=min(Value);

        }
        else if(Operation.equalsIgnoreCase("Median")){
            Answer=median(Value);

        }
        else if(Operation.equalsIgnoreCase("Mode")){
            Answer=mode(Value);

        }
        else if(Operation.equalsIgnoreCase("Mean")){
            Answer=mean(Value);

        }
        return Answer;

    }


    // Method for getting the maximum value-----------------------------------------------------------------------------------------------------------
    public static double max(double[] inputArray){
        double maxValue = inputArray[0];
        for(int i=1;i < inputArray.length;i++){
            if(inputArray[i] > maxValue){
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }
    // Method for getting the minimum value----------------------------------------------------------------------------------------------------------
    public static double min(double[] inputArray){
        double minValue = inputArray[0];
        for(int i=1;i<inputArray.length;i++){
            if(inputArray[i] < minValue){
                minValue = inputArray[i];
            }
        }
        return minValue;
    }
//Compute variance-------------------------------------------------------------------------------------------------------------------------------
// Java program to find variance
// and standard deviation of
// given array.

    // Function for calculating
    // variance
    public static double variance(double a[])
    {
        // Compute mean (average
        // of elements)
        double sum = 0;

        for (int i = 0; i < a.length; i++)
            sum += a[i];
        double mean = (double)sum /
                (double)a.length;

        // Compute sum squared
        // differences with mean.
        double sqDiff = 0;
        for (int i = 0; i < a.length; i++)
            sqDiff += (a[i] - mean) *
                    (a[i] - mean);

        return (double)sqDiff / a.length;
    }

    public static double standardDeviation(double b[])
    {
        return Math.sqrt(variance(b));
    }

//MEAN------------------------------------------------------------------------------------------------------------------------------------------

    static double mean(double[] m) {
        double sum = 0;
        for (int i = 0; i < m.length; i++) {
            sum += m[i];
        }
        return (sum / m.length);
    }
//MEDIAN----------------------------------------------------------------------------------------------------------------------------------------

    // the array double[] m MUST BE SORTED
    public static double median(double[] m) {
        int middle = m.length/2;
        if (m.length%2 == 1) {
            return m[middle];
        } else {
            return (m[middle-1] + m[middle]) / 2.0;
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------------
    public static double mode(double[]data) {
        if (data.length != 0) {

            double maxValue = -1;
            int maxCount = 0;
            for (int i = 0; i < data.length; i++) {
                int count = 0;
                for (int j = 0; j < data.length; j++) {
                    if (data[j] == data[i]) {
                        count++;
                    }
                }

                if (count > maxCount) {
                    maxValue = (int) data[i];
                    maxCount = count;
                }
            }
            return maxValue;

        } else {

            return Double.NaN;
        }
    }
    //3.b)FillIn TheBlank---------------------------------------------------------------------------------------------------------------
    public static String[][] fillInBlank (String[][] v,String Header, String c) {

        int SelectedColumnIndex=0;
        for(int i=0; i<v[0].length;i++) {
            if (Header.equalsIgnoreCase(v[0][i])) {
                SelectedColumnIndex = i;
                break;
            }
        }
        for (int i = 0; i < v.length; i++) {
            for (int j = 0;j< v[0].length;j++) {

                if (v[i][j].isEmpty() && j == SelectedColumnIndex) {
                    v[i][j] = c;
                }
            }
        }
       return v;
    }

    //----------------------------------------------------------------------------------------------------------------
    public static String[][] Sort(String[][] unsort,String objtosort)
    {
        int column = unsort[0].length, row = unsort.length;

        for (int i=0; i<column; i++)
        {
            if(unsort[0][i].equalsIgnoreCase(objtosort))
            {
                for(int j=1; j<row; j++)
                {
                    for(int k=1; k<row-j; k++)
                    {
                        if(unsort[k][i].compareTo(unsort[k+1][i])>0)
                        {
                            for(int l=0; l<column; l++)
                            {
                                String temp = unsort[k][l];
                                unsort[k][l] = unsort[k+1][l];
                                unsort[k+1][l] = temp;
                            }
                        }
                    }
                }
            }
        }
        String[][] sorted = new String[row][column];
        for(int i=0; i<row; i++)
        {
            for (int j=0; j<column; j++)
            {
                sorted[i][j] = unsort[i][j];
            }
        }

        return sorted;
    }
    //-------------------------------------------------------------------------------------------------------------------------
//StandardScaling-------------------------------------------------------------------------------------------------
    public static double[] stdscale(String[][] array,String Header,int Min,int Max){
        String[] range = SelectRange(array,Header,Min,Max);
        double [] inputArray=SarrayTodouble(range);
        double[] newarray = new double [inputArray.length];
        double MeanofArray =mean(inputArray);
        double SDofArray = standardDeviation(inputArray);


        for (int i=0;i< inputArray.length;i++){
            newarray[i] = ((inputArray [i] - MeanofArray)/SDofArray);
        }
        return newarray;
    }


    // Method to perform min max scale----------------------------------------------------------------------------------
    public static double[] minmaxscale (String[][] array,String Header,int Min,int Max) {
        String[] range = SelectRange(array,Header,Min,Max);
        double [] inputArray=SarrayTodouble(range);
        double[] newArray =new double[inputArray.length];
        double minOfArray=min(inputArray);
        double rangeOfArray=max(inputArray)-min(inputArray);
        for (int i=0;i< inputArray.length;i++){
            newArray[i] = (inputArray[i] - min(inputArray))/ rangeOfArray;
        }

        return newArray;
    }
//KnnClassifier-------------------------------------------------------------------------------------------------
public static void KnnClassifier(int X1,int Y1,int X2,int Y2,double[] Sample,String firstClassname,String secondClassname, String[][] arraytest,int k) {

    double[][] firstarray=KnnClassifiergetfirstarray(X1,Y1,arraytest);
    double[][] secondarray=KnnClassifiergetfirstarray(X2,Y2,arraytest);
    double[] distancefirstdata = distantcalc(firstarray,Sample);
    double[] distanceseconddata = distantcalc(secondarray,Sample);
    int[] data = classifier(distancefirstdata,distanceseconddata,k);                                                                                    
    classselect(data,firstClassname,secondClassname);
    if(k!=data[2])
    {
        System.out.println("k is adjusted to" + data[2] + "due to the amount of both class to classify the unknown is the same when k = " + k);
    }
    System.out.printf("The accuracy by Classification Accuracy is %.2f\n",Classifieraccuracy(data));
    System.out.printf("The accuracy by Confusion Matrix is %.2f\n",Confusionmatrix(data,firstarray.length,secondarray.length));                         
    
}



    public static double[][] KnnClassifiergetfirstarray(int X,int Y,String[][] arraytest) {

        double[][] FirstClass=toDouble(arraytest);
        double[][] SampleArray= new double[FirstClass.length+1][2];
        int k=0;

        for(int i=0;i< FirstClass.length;i++){
            for(int j=0;j<FirstClass[0].length;j++){

                if(j==X){
                    SampleArray[k][0]=FirstClass[i][j];

                }
                else if(j==Y){
                    SampleArray[k][1]=FirstClass[i][j];
                    k++;

                }

            }
        }
        return SampleArray;
    }

    public static double[][] toDouble(String[][] array) {
        double[][] newAray=new double[array.length][array[0].length];
        for(int i =0;i< array.length;i++){
            for (int j=0;j< array[0].length;j++){
                newAray[i][j]=Double.parseDouble(array[i][j]);
            }
        }

        return newAray;

    }

    public static double[] distantcalc(double[][] coordinate,double[] coordinateinput)
    {
        double[] newdistance = new double[coordinate.length];
        for(int i=0; i<coordinate.length; i++ )
        {
            double x1 = coordinate[i][0];
            double y1 = coordinate[i][1];
            double x2 = coordinateinput[0];
            double y2 = coordinateinput[1];
            newdistance[i] = Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
        }
        sortdistance(newdistance);
        return newdistance;
    }

    public static void sortdistance(double[] unsort)
    {
        for(int i=1; i<unsort.length;i++)
        {
            for (int j=0; j<unsort.length-i;j++ )
            {
                if(unsort[j]>unsort[j+1])
                {
                    double temp = unsort[j];
                    unsort[j] = unsort[j+1];
                    unsort[j+1] = temp;
                }
            }
        }
    }

    public static int[] classifier( double[] distancefirstdata,double[] distanceseconddata,int k)
    {

        int m=0 ,n=0;
        for (int i=0; i<k;)
        {
            if( distancefirstdata[m] < distanceseconddata[n] )
            {
                m++;
                i++;
            }
            else if(distancefirstdata[m] > distanceseconddata[n])
            {
                n++;
                i++;
            }
            else
            {
                m++;
                n++;
                i+=2;
                if(i>k)
                {k+=2;}
            }
        }
        int[] output = {m,n,k};                                                                                                                             //
        return output;        
    }
        public static void classselect( int[] output,String firstclass,String secondclass)                                                                      
        {
            if(output[0]>output[1])
            {
                System.out.println("x = " + firstclass);
            }
            else
            {
                System.out.println("x = " + secondclass);
            }
        }
        
         public static double Classifieraccuracy(int[] data)                                                                                                    //added
        {
            double accuracy;
            if(data[0]>data[1])
            {
                accuracy = data[0]*100/data[2];
            }
            else
            {
                accuracy = data[1]*100/data[2];
            }
            return accuracy;
        }
        
        public static double Confusionmatrix(int[] data,int n1,int n2)                                                                                          //added
        {
            int n = n1 + n2;
            double accuracy;
            if(data[0]>data[1])
            {
                accuracy = (data[0] + n2 - data[1]) * 100 / n;
            }
            else
            {
                accuracy = (data[1] + n1 - data[0]) * 100 / n;
            }
            return accuracy;
        }
    
//KNN Regressor---------------------------------------------------------------------------------------------------------------

    public static double[] KnnRegressor(String[][] data, String header1, String header2,double unknown,int k)
    {
        double[][] coordinate = KnnRegressorgraph(data,header1,header2);
        double[] differencebaseddata = lineardistance(coordinate,unknown);
        sortdata(coordinate,differencebaseddata);
        double sum = 0,answer;
        for(int i=0;i<k;i++)
        {
            sum += coordinate[i][1];
        }
        answer = sum/k;
        double[] finalans = {unknown,answer};
        return finalans;
    }

    public static double[][] KnnRegressorgraph(String[][] data, String x, String y)
    {
        String[][] newtable = new String[data.length-1][2];
        for(int i=0; i<data[0].length;i++)
        {
            if(x.equalsIgnoreCase(data[0][i]))
            {
                for(int j=0; j<data.length-1; j++)
                {
                    newtable[j][0] = data[j+1][i];
                }
            }
            else if(y.equalsIgnoreCase(data[0][i]))
            {
                for(int j=0; j<data.length-1; j++)
                {
                    newtable[j][1] = data[j+1][i];
                }
            }
        }
        double[][] output = toDouble(newtable);
        return output;
    }

    public static double[] lineardistance(double[][] data, double unknown)
    {
        double[] newdistance = new double[data.length];
        for(int i=0; i<data.length; i++ )
        {
            double x1 = data[i][0];
            newdistance[i] = unknown - x1;
            if(newdistance[i]<0)
            {
                newdistance[i] = 0 - newdistance[i];
            }
        }
        return newdistance;
    }

    public static void sortdata(double[][] table,double[] unsort)
    {
        for(int i=1; i<unsort.length;i++)
        {
            for (int j=0; j<unsort.length-i;j++ )
            {
                if(unsort[j]>unsort[j+1])
                {
                    double temp1 = unsort[j];
                    unsort[j] = unsort[j+1];
                    unsort[j+1] = temp1;

                    for(int k=0; k<table[0].length;k++)
                    {
                        double temp2 = table[j][k];
                        table[j][k] = table[j+1][k];
                        table[j+1][k] = temp2;
                    }
                }
            }
        }
    }
//UpdateCSV---------------------------------------------------------------------------------------------
public static void save(String File,int TableNum,String[][] Data) {
    String TempFile="TableTempFile";
    try {
        PrintWriter outTemp = new PrintWriter(new FileOutputStream(TempFile));

        Scanner in = new Scanner(new FileInputStream(File));
        Scanner inTemp = new Scanner(new FileInputStream(TempFile));
        int TableCounter = 0;//
        int i=0;
        while (in.hasNextLine()) {
            String str = in.nextLine();
            if (str.equals("")) {
                TableCounter++;
                outTemp.println();
            }
            else if (TableCounter >= TableNum-1 && TableCounter < TableNum) {
                for (int j = 0; j < Data[i].length; j++) {
                    outTemp.print(Data[i][j]);
                    if(j!= Data[i].length-1){
                        outTemp.print(",");

                    }
                }
                outTemp.println();
                i++;
            }
            else {
                outTemp.println(str);
            }
        }
        outTemp.close();
        PrintWriter outFile = new PrintWriter(new FileOutputStream(File));
        while (inTemp.hasNextLine()) {
            outFile.println(inTemp.nextLine());
        }
        outFile.close();
        inTemp.close();
        in.close();
       /* File f3 = new File(TempFile);
        if (f3.exists()) {
            //System.out.println("Delete file"+TempFile);
            f3.delete();
        }*/
    }catch (FileNotFoundException e) {
        System.out.println("File was not found");
    }
    catch (IOException e) {
        System.out.println("Problem with file output");
    }
}





































}


















































































