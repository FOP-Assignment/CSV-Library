import java.io.*;
import java.util.Arrays;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {

        Table a =new Table("CSVExample.csv",4,",");
        //Table b =new Table("CSVExample.csv",3,",");
        //Table c =new Table("CSVExample.csv",1,",");

        double[] sample = {2,3,4,5};

         a.allpredictedpoints(a.getData(),"Saya","Kamu",sample,5);
    }










































}
