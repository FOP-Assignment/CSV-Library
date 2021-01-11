public class Main {
    public static void main(String[] args) {
        Table a =new Table("CSVExample.csv",3,",");
        double[] SampleCoordinate = {7,6};
        a.KNNClassifier(0,1,2,3, SampleCoordinate,"A1","B2",5);
    }
}
