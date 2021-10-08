import java.util.Scanner;

public class Manager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Methods app = new Methods();
        app.createData();

        boolean stop =true;

        while (stop) {
            System.out.println("enter name and days");
            String selection = sc.nextLine();
            String[] input = selection.split(" ");
            String name = input[0];
            String[] days = input[1].split(",");

            if(name == "stop"){
                stop = false;
            }else{
                app.addRequest(name, days);
            }
        }
    }

}