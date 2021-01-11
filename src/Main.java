







public class Main {
    public static void main(String[] args) {
        Table a =new Table("CSVExample.csv",1,",");
        String[][] Array ={
                {"Nama","Umur","University"},
                {"Muneq","19","UM"},
                {"Kamal","22","UPSI"},
                {"Sam","25","UITM"}
        };
        a.AddTable(Array);
    }
}
