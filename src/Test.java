import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


public class Test {
    public static void main(String[] args) {
        Object[][] a = {{1,2,3},{1,2,3},{1,2,3},{1,2,3},{1,2,3}};
        Object[][] b = {{1,2,3},{1,2,3},{1,2,3},{1,2,3},{1,2,3}};
        tableWithLines(arrayConcat(a,b,(byte)1));
    }

    public static void tableWithLines(Object[][] table) {
        /*
         * leftJustifiedRows - If true, it will add "-" as a flag to format string to
         * make it left justified. Otherwise right justified.
         */
        boolean leftJustifiedRows = false;

        /*
         * Table to print in console in 2-dimensional array. Each sub-array is a row.
         */


        /*
         * Calculate appropriate Length of each column by looking at width of data in
         * each column.
         *
         * Map columnLengths is <column_number, column_length>
         */
        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            if (columnLengths.get(i) == null) {
                columnLengths.put(i, 0);
            }
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));
        //System.out.println("columnLengths = " + columnLengths);

        /*
         * Prepare format String
         */
        final StringBuilder formatString = new StringBuilder("");
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
        formatString.append("|\n");
        // System.out.println("formatString = " + formatString.toString());

        /*
         * Prepare line for top, bottom & below header row.
         */
        String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
            String templn = "+-";
            templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
                    (a1, b1) -> a1 + b1);
            templn = templn + "-";
            return ln + templn;
        }, (a, b) -> a + b);
        line = line + "+\n";
        //System.out.println("Line = " + line);

        /*
         * Print table
         */
        System.out.print(line);
        Arrays.stream(table).limit(1).forEach(a -> System.out.printf(formatString.toString(), a));
        System.out.print(line);

        Stream.iterate(1, (i -> i < table.length), (i -> ++i))
                .forEach(a -> System.out.printf(formatString.toString(), table[a]));
        System.out.print(line);
    }




    /*
     * Define directions for array concatenation
     */
    public static final byte ARRAY_CONCAT_HORIZ = 0, ARRAY_CONCAT_VERT = 1;

    /*
     * Concatenates 2 2D arrays
     */
    public static String[][] arrayConcat(String[][] a, String[][] b, byte concatDirection)
    {
        if(concatDirection == ARRAY_CONCAT_HORIZ && a[0].length == b[0].length)
        {
            return Arrays.stream(arrayConcat(a, b)).map(Object[].class::cast)
                    .toArray(Object[][]::new);
        }
        else if(concatDirection == ARRAY_CONCAT_VERT && a.length == b.length)
        {
            String[][] arr = new String[a.length][a[0].length + b[0].length];
            for(int i=0; i<a.length; i++)
            {
                arr[i] = arrayConcat(a[i], b[i]);
            }
            return arr;
        }
        else
            throw new RuntimeException("Attempted to concatenate arrays of incompatible sizes.");
    }

    /*
     * Concatenates 2 1D arrays
     */
    public static String[] arrayConcat(String[][] a, String[][] b)
    {
        String[] arr = new String[a.length + b.length];
        System.arraycopy(a, 0, arr, 0, a.length);
        System.arraycopy(b, 0, arr, a.length, b.length);
        return arr;
    }



}
