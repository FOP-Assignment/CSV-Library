import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Table {
    private String Path,Regex;
    private int TableNum;
    private String[][] data;


    public Table(String path, int tableNum,String regex) {
        File f = new File(path);
        if(f.exists()){
            Path = path;
        }
        else{
            try {
                PrintWriter newfile = new PrintWriter(new FileOutputStream(path));
                newfile.println("hello");
                newfile.println("");
                Path = path;
            }catch (IOException e) {
                System.out.println("Problem with file output");
            }

        }
        TableNum = tableNum;
        Regex=regex;
        data=GetArray(getPath(),getTableNum(),getRegex());

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
    //1)To Add new Table
    public void AddTable(String[][] Input) {
        NewTable(getPath(),Input ,getRegex());

    }


    //
//---------------------------------------------------------------------------------------------------------------------------------------------------

    public String[][] GetArray(String file, int TableNum, String regex) {
            int i=0;
            int tablenum=TableNum;
            //for new table
             File newFile = new File(file);
             if (newFile.length() == 0) {
                 //String[][] Element={};
                // return Element;
                 return null;
              }

            int Headerlength= GetTableDetail(file, tablenum,regex)[1];
            int Tableline=GetTableDetail(file, tablenum,regex)[0];
            int l=0;
            String[][] Element= new String[Tableline][Headerlength];


            try{
                Scanner in2 =new Scanner(new FileInputStream(file));

                while (in2.hasNextLine()) {
                    String[] current =((in2.nextLine()).split(regex));
                    //System.out.println("["+l+"]");

                    if(i==tablenum-1 && l<Tableline){

                        for(int k=0;k<Headerlength;){
                            Element[l][k]=current[k];
                            //System.out.print(k);
                            k++;
                        }
                        l++;

                    }

                    if (current[0].equals("")){
                        i++;
                    }
                }

                in2.close();
            } catch (FileNotFoundException e) {


            }
            return Element;

        }



        public int[] GetTableDetail(String file,int TableNum,String regex) {
            int i=0;
            int j=0;
            int tablenum=TableNum;
            int headerlength=0;
            int tableline=0;

            try {



                Scanner in = new Scanner(new FileInputStream(file));

                while(in.hasNextLine()){
                    String[] current =((in.nextLine()).split(regex));




                    if(i==tablenum-1){
                        if(j==0){
                            headerlength= current.length;
                            j=1;

                        }
                        tableline++;
                    }

                    if (current[0].equals("")){
                        i++;
                    }
                }

                in.close();
            } catch (FileNotFoundException e) {
                System.out.println("File was not found");
            }
            tableline-=1;//To eliminate empty line count
            int[] Details= {tableline,headerlength};
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
    public void NewTable(String Outputfile,String[][] InputTable,String regex) {

        try{
            PrintWriter out = new PrintWriter(new FileOutputStream(Outputfile,true));
            for (int row = 0; row < InputTable.length; row++)
            {
                for (int col = 0; col < InputTable[row].length; col++)
                {
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

        try{
            Scanner in2 =new Scanner(new FileInputStream(Outputfile));
            int i=0;
            if(in2.hasNextLine()==false){
                in2.close();
            }
            while (in2.hasNextLine()) {

                String[] current =((in2.nextLine()).split(","));


                if (current[0].equals("")){
                    i++;
                }
            }

            in2.close();

            setTableNum(i);
            System.out.println(getTableNum());
            setData(GetArray(getPath(),getTableNum(),getRegex()));
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }

        ViewTable();

    }


    }





































































































