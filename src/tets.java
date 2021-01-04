import java.sql.SQLOutput;
import java.util.Arrays;


public class tets{
    public static void main(String[] args) {
        String[][] c = {{"Sam", "Carl", "Smith"}, {"stra", "Robert", "Smith"}, {"james", "tapok", "Smith"}, {"kate", "Robert", "Smith"}};
        String[][] d = {{"Sam", "Carl", "Smith"}, { "aya", "Robert", "Smith"}, {"james", "cindy","Smith"}, {"kate", "Robert", "Smith",}};
        String [][] data = Concatenate(c,d,'k');

        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                System.out.printf("%10s", data[row][col]);

            }
            System.out.println();
        }



       /* //convert from object array to string array, maybe?
        String[][] namesStrings = new String[names.length][names[0].length];


        System.out.println(Arrays.toString(namesStrings));
        */





    }

    public static String[][] Concatenate(String[][] FirstArray,String[][] SecondArray,char Type_H_or_V) {
        byte Type;
        if(Type_H_or_V=='H' || Type_H_or_V=='h' ){
            Type=1;
        }
        else{
            Type=0;
        }

        String [][] data = Two_d_OtoS(arrayConcat(FirstArray,SecondArray,Type));
        return data;

    }

    public static Object[][] Two_d_StoO(String[][] Data) {
        Object[][] OData = new Object[Data.length][Data[0].length];
        for (int row = 0; row < Data.length; row++) {
            for (int col = 0; col < Data[row].length; col++) {
                OData[row][col]=Data[row][col].toString();

            }
        }
        return OData;
    }




    public static String[][] Two_d_OtoS(Object[][] Data) {
        String[][] SData = new String[Data.length][Data[0].length];
        for (int row = 0; row < Data.length; row++) {
            for (int col = 0; col < Data[row].length; col++) {
                SData[row][col]=Data[row][col].toString();

            }
        }
        return SData;


    }






    public static final byte ARRAY_CONCAT_HORIZ = 0, ARRAY_CONCAT_VERT = 1;
    public static Object[][] arrayConcat (String[][] SDataA, String[][] SDataB,byte concatDirection)
    {

        Object[][]a = Two_d_StoO(SDataA);
        Object[][]b = Two_d_StoO(SDataB);


        if (concatDirection == ARRAY_CONCAT_HORIZ && a[0].length == b[0].length) {
            return Arrays.stream(arrayConcat(a, b)).map(Object[].class::cast)
                    .toArray(Object[][]::new);
        } else if (concatDirection == ARRAY_CONCAT_VERT && a.length == b.length) {
            Object[][] arr = new Object[a.length][a[0].length + b[0].length];
            for (int i = 0; i < a.length; i++) {
                arr[i] = arrayConcat(a[i], b[i]);
            }

            return arr;
        } else
            throw new RuntimeException("Attempted to concatenate arrays of incompatible sizes.");
    }

    /*
     * Concatenates 2 1D arrays
     */
    public static Object[] arrayConcat (Object[]a, Object[]b)
    {
        Object[] arr = new Object[a.length + b.length];
        System.arraycopy(a, 0, arr, 0, a.length);
        System.arraycopy(b, 0, arr, a.length, b.length);
        return arr;
    }


}