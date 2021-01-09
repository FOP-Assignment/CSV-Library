import java.sql.SQLOutput;
import java.util.Arrays;

public class tets{

    public static void main(String[] args) {

        String[][] names = {{"Sam", "Carl", "Smith"}, {"1", "3", "9"}, {"7","2", "7"},{"2", "4", "6"}};
        System.out.println(ComputeStatistic(names,"Median","Sam",1,3));
        //String[] num = {"1","2","3","4","5","6","7"};
        double[] num1 = stdscale(names,"Sam",1,3);

        //System.out.println(Arrays.toString(num1));

       String[] sull= SelectRange(names,"Carl",2,2);
        System.out.println(Arrays.toString(sull));

        for (int row = 0; row <names.length; row++) {
            for (int col = 0; col < names[row].length; col++) {
                System.out.printf("%10s", names[row][col]);

            }
            System.out.println();
        }

       for (int i=0;i<num1.length;i++){
           System.out.println(num1[i]);

       }



    }


//---------------------------------------------------------------------------------------------------------------------------------

    public static String[] SelectRange(String[][] array,String  Header,int Min,int Max) {
        int row = array.length;
        int col = array[0].length;
        int RemoveNum = 0;

        String[] newArray = new String[Max-Min+1];
        int currRow=0;
        for (int i = 0; i < row; i++) {
            if (i == 0) {
                for (int j = 0; j < col; j++) {

                    if (Header.equalsIgnoreCase(array[i][j])) {
                        RemoveNum=j;

                    }

                }
            }
            if (i >= Min && i <= Max) {
                for (int j = 0; j < col; j++) {

                    if (j == RemoveNum) {
                        newArray[currRow++] = array[i][j];

                    }

                }
            }
        }
        return newArray;
    }

    public static double[] SarrayTodouble (String[] arrString) {
        double[] arrDouble = new double[arrString.length];
        for(int i=0; i<arrString.length; i++)
        {
            arrDouble[i] = Double.parseDouble(arrString[i]);
        }
        return arrDouble;
    }


    public static double ComputeStatistic (String[][] array,String Operation,String Header,int Min,int Max) {

        String[] range = SelectRange(array,Header,Min,Max);
        double[] Value =SarrayTodouble(range);
        double Answer =0;

        if(Operation.equalsIgnoreCase("Varience")){
            Answer=variance(Value);

        }
        else if(Operation.equalsIgnoreCase("StandardDeviation")){
            Answer=standardDeviation(Value);

        }
        else if(Operation.equalsIgnoreCase("Max")){
            Answer=max(Value);

        }
        else if(Operation.equalsIgnoreCase("Min")){
            Answer=min(Value);

        }
        else if(Operation.equalsIgnoreCase("Median")){
            Answer=median(Value);

        }
        else if(Operation.equalsIgnoreCase("Mode")){
            Answer=mode(Value);

        }
        else if(Operation.equalsIgnoreCase("Mean")){
            Answer=mean(Value);

        }
        return Answer;

    }


    // Method for getting the maximum value-----------------------------------------------------------------------------------------------------------
    public static double max(double[] inputArray){
        double maxValue = inputArray[0];
        for(int i=1;i < inputArray.length;i++){
            if(inputArray[i] > maxValue){
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }
    // Method for getting the minimum value----------------------------------------------------------------------------------------------------------
    public static double min(double[] inputArray){
        double minValue = inputArray[0];
        for(int i=1;i<inputArray.length;i++){
            if(inputArray[i] < minValue){
                minValue = inputArray[i];
            }
        }
        return minValue;
    }
//Compute variance-------------------------------------------------------------------------------------------------------------------------------
// Java program to find variance
// and standard deviation of
// given array.

    // Function for calculating
    // variance
    public static double variance(double a[])
    {
        // Compute mean (average
        // of elements)
        double sum = 0;

        for (int i = 0; i < a.length; i++)
            sum += a[i];
        double mean = (double)sum /
                (double)a.length;

        // Compute sum squared
        // differences with mean.
        double sqDiff = 0;
        for (int i = 0; i < a.length; i++)
            sqDiff += (a[i] - mean) *
                    (a[i] - mean);

        return (double)sqDiff / a.length;
    }

    public static double standardDeviation(double b[])
    {
        return Math.sqrt(variance(b));
    }

//MEAN------------------------------------------------------------------------------------------------------------------------------------------

    static double mean(double[] m) {
        double sum = 0;
        for (int i = 0; i < m.length; i++) {
            sum += m[i];
        }
        return (sum / m.length);
    }
//MEDIAN----------------------------------------------------------------------------------------------------------------------------------------

    // the array double[] m MUST BE SORTED
    public static double median(double[] m) {
        int middle = m.length/2;
        if (m.length%2 == 1) {
            return m[middle];
        } else {
            return (m[middle-1] + m[middle]) / 2.0;
        }
    }

//---------------------------------------------------------------------------------------------------------------------------------
public static double mode(double[]data) {
    if (data.length != 0) {

        double maxValue = -1;
        int maxCount = 0;
        for (int i = 0; i < data.length; i++) {
            int count = 0;
            for (int j = 0; j < data.length; j++) {
                if (data[j] == data[i]) {
                    count++;
                }
            }

            if (count > maxCount) {
                maxValue = (int) data[i];
                maxCount = count;
            }
        }
        return maxValue;

    } else {

        return Double.NaN;
    }
}
//StandardScaling-------------------------------------------------------------------------------------------------
    public static double[] stdscale(String[][] array,String Header,int Min,int Max){
        String[] range = SelectRange(array,Header,Min,Max);
        double [] inputArray=SarrayTodouble(range);
        double[] newarray = new double [inputArray.length];
        double MeanofArray =mean(inputArray);
        double SDofArray = standardDeviation(inputArray);


        for (int i=0;i< inputArray.length;i++){
            newarray[i] = ((inputArray [i] - MeanofArray)/SDofArray);
        }
        return newarray;
    }


    // Method to perform min max scale----------------------------------------------------------------------------------
    public static double[] minmaxscale (String[][] array,String Header,int Min,int Max) {
        String[] range = SelectRange(array,Header,Min,Max);
        double [] inputArray=SarrayTodouble(range);
        double[] newArray =new double[inputArray.length];
        double minOfArray=min(inputArray);
        double rangeOfArray=max(inputArray)-min(inputArray);
        for (int i=0;i< inputArray.length;i++){
            newArray[i] = (inputArray[i] - min(inputArray))/ rangeOfArray;
        }

        return newArray;
    }
































}
//-------------------------------------------------------------------------------------------------------------------------------------
