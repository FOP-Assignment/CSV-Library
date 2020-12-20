import java.io.*;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {

        Scanner promptScanner = new Scanner(System.in);
        System.out.println("hello");
        String outputfile=selectOutputFile();
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(outputfile,true));
            out.close();



        } catch (IOException e) {
            System.out.println("Problem writing to text file");
        }

        System.out.println("Choose setup \n1.Edit within single file \n2.Edit between mutiple file");
        int EditSetup = promptScanner.nextInt();
        //Edit singlefile
        if (EditSetup==1){


            //readand class data
            try {
                Scanner in = new Scanner(new FileInputStream(outputfile));
                //  nextInt, nextDouble, nextLine

                int linecounter=0;
                int headerlength = 0;
                while (in.hasNextLine()) {
                   linecounter++;
                   if(linecounter==0){
                       String str =in.nextLine();
                       String[] header = str.split(",");
                       headerlength= header.length;

                   }
                }
                int line=0;
                String[][] Tablearray = new String[linecounter][headerlength];

                while (in.hasNextLine()) {
                    String CurrentLine =in.nextLine();
                    String[] CurrentLinearray  = CurrentLine.split(",");
                    Tablearray[line]=CurrentLinearray;
                    System.out.println(Tablearray[line]);
                    System.out.println("hello");

                    line++;
                }



               /* while(in.hasNextLine()){
                    for (int i=0;i<=linecounter;i++ ){
                        int headerlength;
                        if(i==1){
                            String str =in.nextLine();
                            String[] header = str.split(",");
                            headerlength= header.length;

                        }
                        String[][] Tablearray = new String[linecounter][headerlength];
                        String CurrentLine =in.nextLine();
                        String[] CurrentLinearray  = CurrentLine.split(",");


                    }*/






                    in.close();
            } catch (FileNotFoundException e) {
                System.out.println("File was not found");
            }


            System.out.println(Tablearray[line]);
            System.out.println("hello");













            //Print to outputfile-----------------------------------------------------------------------------
            try {
                PrintWriter out = new PrintWriter(new FileOutputStream(outputfile));
                out.close();



            } catch (IOException e) {
                System.out.println("Problem writing to text file");
            }
            //-------------------------------------------------------------------------------------------------








        }
        //edit multiple file
        else if(EditSetup ==2 ){



            //Print to outputfile-----------------------------------------------------------------------------
            try {
                PrintWriter out = new PrintWriter(new FileOutputStream(outputfile));
                //out.println()






                out.close();



            } catch (IOException e) {
                System.out.println("Problem writing to text file");
            }
            //-------------------------------------------------------------------------------------------------


        }
        else{
            System.out.println("invalid input");
        }
        //
        //



    }




    public static String selectOutputFile() {

        Scanner promptScanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Please select output file with directory path");
        String OutputFile = promptScanner.next();
        return OutputFile;



    }








































}
