import java.io.*;
import java.util.Scanner;

public class hello {

    public static void main(String[] args) {
        String[][] names = {{"Sam", "Carl", "Smith"}, {"1", "3", "9"}, {"7","2", "7"},{"2", "4", "6"}};

        save("Type.csv",1,names);
    }


    public static void save(String File,int TableNum,String[][] Data) {
        String TempFile="TableTempFile";
        try {
            PrintWriter outTemp = new PrintWriter(new FileOutputStream(TempFile));

            Scanner in = new Scanner(new FileInputStream(File));
            Scanner inTemp = new Scanner(new FileInputStream(TempFile));
            int TableCounter = 0;//
            int i=0;
            while (in.hasNextLine()) {
                String str = in.nextLine();
                if (str.equals("")) {
                    TableCounter++;
                    outTemp.println();
                }
                else if (TableCounter >= TableNum-1 && TableCounter < TableNum) {
                        for (int j = 0; j < Data[i].length; j++) {
                           outTemp.print(Data[i][j]);
                            if(j!= Data.length-2){
                                outTemp.print(",");

                        }
                    }
                    outTemp.println();
                    i++;
                }
                else {
                    outTemp.println(str);
                }
            }
            outTemp.close();
            PrintWriter outFile = new PrintWriter(new FileOutputStream(File));
            while (inTemp.hasNextLine()) {
                outFile.println(inTemp.nextLine());
            }
            outFile.close();
            inTemp.close();
            in.close();
            File f3 = new File(TempFile);
            if (f3.exists()) {
                //System.out.println("Delete file"+TempFile);
                f3.delete();
            }
        }catch (FileNotFoundException e) {
                System.out.println("File was not found");
            }
         catch (IOException e) {
            System.out.println("Problem with file output");
        }
    }
}