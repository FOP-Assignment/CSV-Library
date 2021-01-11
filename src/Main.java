import java.io.*;
import java.util.Arrays;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {

        Table a =new Table("CSVExample.csv",5,",");
        //Table b =new Table("CSVExample.csv",3,",");
        //Table c =new Table("CSVExample.csv",1,",");


         double[] abcd = {7,6};
         a.KNNClassifier(0, 1, 2, 3, abcd, "A1", "B2", 5);
    }










































}
