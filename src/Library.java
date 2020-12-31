
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Library {
    public static void main(String[] args) {

        String file ="CSVExample.csv";
        int tablenum=1;
        String Table= (getElement(file,tablenum)).toString();
        System.out.println(Table);


    }


    public static int[] GetTableDetail(String filepath,int tableNum) {
        int tablecounter=0;
        int linecounter=0;
        int headerlength=0;


        try {

            Scanner in = new Scanner(new FileInputStream(filepath));
            //  nextInt, nextDouble, nextLine



            while (in.hasNextLine()&& tablecounter<=tableNum) {


                if (in.nextLine() == "") {
                    tablecounter++;
                }
                while (tablecounter >= tableNum - 1 && tablecounter <= tableNum) {
                    linecounter++;
                    String stringcurrent = in.nextLine();
                    String[] current = stringcurrent.split(",");
                    headerlength = current.length;


                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        int[] arravl = new int[]{linecounter,headerlength};
        return arravl;

    }









    public static String[][] getElement(String filepath,int tableNum) {
        int tablecounter=0;
        int linecounter=GetTableDetail(filepath,tableNum)[0];
        int headerlength =GetTableDetail(filepath,tableNum)[1];
        String [][] element = new String [linecounter][headerlength];


        try {
            Scanner in = new Scanner(new FileInputStream(filepath));
            //  nextInt, nextDouble, nextLine



            while (in.hasNextLine()&& tablecounter<=tableNum) {

                if (in.nextLine() == "") {
                    tablecounter++;
                }
                while (tablecounter >= tableNum - 1 && tablecounter <= tableNum) {
                    linecounter++;
                    String stringcurrent = in.nextLine();
                    String[] current=stringcurrent.split(",");
                    headerlength= current.length;


                }
            }


            int tablecounter2=0;
            tablecounter=0;


            int j=0;

             //element = new String [linecounter][headerlength];

            while (in.hasNextLine()&& tablecounter2<=tableNum) {

                if (tablecounter >= tableNum - 1) {

                    String stringcurrent = in.nextLine();
                    String[] current=stringcurrent.split(",");
                    for(int i=0 ;i<= current.length;) {
                        element[j][i] =current[i];
                        i++;
                    }
                    j++;
                }

            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        return element;
    }


}
