public class hello{
    public static void main(String[] args) {


    }

    public static void KnnClassifier(int X1,int Y1,int X2,int Y2,double[] Sample,String firstClassname,String secondClassname) {
        double[][] firstArray=KnnClassifiergetfirstarray(X1,Y1);
        double[][] secondArray=KnnClassifiergetfirstarray(X2,Y2);
    }

    public static double[][] KnnClassifiergetfirstarray(int X,int Y) {

        String[][] arraytest=new String[4][5];
        double[][] FirstClass=toDouble(arraytest);
        double[][] SampleArray= new double[FirstClass.length][2];
        int k=0;

        for(int i=0;i< FirstClass.length;i++){
            for(int j=0;j<FirstClass[0].length;j++){

                if(j==X){
                    SampleArray[k][0]=FirstClass[i][j];
                    k++;
                }
                else if(j==Y){
                    SampleArray[k][1]=FirstClass[i][j];
                    k++;

                }

            }
        }
        return SampleArray;
    }

    public static double[][] toDouble(String[][] array) {
        double[][] newAray=new double[array.length][array[0].length];
        for(int i =0;i< array.length;i++){
            for (int j=0;j< array[0].length;j++){
                newAray[i][j]=Double.parseDouble(array[i][j]);
            }
        }

        return newAray;

    }
}