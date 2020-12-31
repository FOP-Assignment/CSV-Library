import java.io.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        String OutputPath ="CVSExample.csv";
        String[][] a = {{"1","2","3","4"},{"1","2","3","4"},{"1","2","3","4"}};
        String regex = ";";
        NewTable(OutputPath,a,regex);

    }

    public static void  NewTable(String Outputfile,String[][] InputTable,String regex) {

        try{
            PrintWriter out = new PrintWriter(new FileOutputStream(Outputfile,true));
            out.println();
            for (int row = 0; row < InputTable.length; row++)
            {
                for (int col = 0; col < InputTable[row].length; col++)
                {
                    out.print(InputTable[row][col]);
                    out.print(regex);
                }
                out.println();
            }
            out.close();
        } catch (IOException e) {
            System.out.println("Problem with file output");
        }

        try{
            Scanner in2 =new Scanner(new FileInputStream(Outputfile));
            int i=0;
            while (in2.hasNextLine()) {
                String[] current =((in2.nextLine()).split(","));


                if (current[0].equals("")){
                    i++;
                }
            }

            in2.close();

            //setTableNum(i);
            //System.out.println(getTableNum());
            //setData(GetArray(getPath(),getTableNum(),getRegex()));
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }

       // ViewTable();

    }





}
