import java.io.*;
import java.util.Arrays;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {

        Table a =new Table("CSVExample.csv",1,",");
        //Table b =new Table("CSVExample.csv",3,",");
        //Table c =new Table("CSVExample.csv",1,",");

         a.ViewTable();
         a.SorterSet("origin");
         a.UpdateCSV();
         a.ViewTable();
    }










































}
