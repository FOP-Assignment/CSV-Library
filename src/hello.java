import java.io.*;
import java.util.Scanner;

public class hello {
    public static void main(String[] args) {
        Save("Type.csv");

    }

    public static void Save(String File) {
        try {
            Scanner in = new Scanner(new FileInputStream(File));
            PrintWriter out = new PrintWriter(new FileOutputStream(File));
            int i=0;
            out.println("Hello");
            while (in.hasNext()) {
                /*String str = in.nextLine();
                i++;
                if (i!=4) {
                    out.println(str);
                }*/
                out.println("Hello");
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        } catch (IOException e) {
            System.out.println("Problem with file output");
        }


    }
}