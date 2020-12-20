import jdk.swing.interop.SwingInterOpUtils;

import java.sql.SQLOutput;

public class Library {
    public static void main(String[] args) {
        System.out.println("Hi Welcome to CSV Library");
        System.out.println("This are the list of fuction");
        System.out.println();
        System.out.println("1.VIEW(FILE,TABLE)");                                            //CSV TO DATAFRAME
        System.out.println("2.NEW TABLE(FILE)");                                             //DATAFRAME TO CSV
        System.out.println("1.APPEND/EDIT TABLE(FILE,TABLE,ROW,COLUMN)");                    //ADD NEW ELEMENT TO TABLE
        System.out.println("1.CONCATENATECOLOUMN(FILE1,TABLE1,FILE2,TABLE2,OUTPUT FILE)");    //JOIN TWO TABLE AND OUTPUT INTO NEW TABLE
        System.out.println("1.CONCATENATEROW(FILE1,TABLE1,FILE2,TABLE2,OUTPUT FILE)");        //JOINT TWO TABLE& THROW ERROR NOT MATCHING COLUMN
        System.out.println("1.OBTAIN(ROW/COLUMN,START,END)");                                 //get all data from corresponding row and column
        System.out.println("1.GETCOLUMN(\"COLUMN\",int START,int END)");                      //get data in column in range
        System.out.println("1.GETROW(\"ROW\",int START,int END)");                            //get data in row in range
        System.out.println("1.SORT(\"COLUMN\")");
        System.out.println();


    }
}
