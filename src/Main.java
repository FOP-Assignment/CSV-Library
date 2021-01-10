import java.io.*;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {

        Table a =new Table("CSVExample.csv",2,",");
        Table b =new Table("CSVExample.csv",3,",");
        Table c =new Table("CSVExample.csv",1,",");
        double[] Sample={7,6};
        a.KNNClassifier(0,1,2,3,Sample,"A1","B2",3);


    }










































}
