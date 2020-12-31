
import java.io.*;
import java.util.Scanner;

public class SimpleCSVreader {
    public static void main(String[] args) {
        int i=0;
        int Tablenum=1;
        String file="CSVExample.csv";

        int Headerlength= GetTableDetail(file,Tablenum)[1];
        int Tableline=GetTableDetail(file,Tablenum)[0];
        int l=0;
        String[][] Element= new String[Tableline][Headerlength];

        try{
            Scanner in2 =new Scanner(new FileInputStream("CSVExample.csv"));

            while (in2.hasNextLine()) {
                String[] current =((in2.nextLine()).split(","));
                System.out.println("["+l+"]");

               if(i==Tablenum-1 && l<Tableline){

                       for(int k=0;k<Headerlength;){
                           Element[l][k]=current[k];
                           System.out.print(k);
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
            System.out.println("File was not found");
        }
        System.out.println(Element[0][1]);
    }



    public static int[] GetTableDetail(String file,int TableNum) {
        int i=0;
        int j=0;
        int Tablenum=TableNum;
        int headerlength=0;
        int tableline=0;

        try {

            Scanner in = new Scanner(new FileInputStream(file));

            while(in.hasNextLine()){
                String[] current =((in.nextLine()).split(","));

                if(i==Tablenum-1){
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
    public void NewTable(String Outputfile,int tablenum,String InputTable[][],String regex) {
        try {

            PrintWriter out = new PrintWriter(new FileOutputStream(Outputfile, true));
            Scanner in = new Scanner(new FileInputStream(Outputfile));
            int i = 0;
            boolean p = true;

            while (in.hasNextLine()) {
                String[] current = ((in.nextLine()).split(regex));

                while (i == tablenum - 1 && p) {
                    out.println();
                    for (int row = 0; row < InputTable.length; row++) {
                        for (int col = 0; col < InputTable[row].length; col++) {
                            out.print(InputTable[row][col]);
                            out.print(regex);
                        }

                    }
                    p = false;

                }


                if (current[0].equals("")) {
                    i++;
                }
            }

            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        } catch (IOException e) {
            System.out.println("Problem with file output");
        }
    }

}
