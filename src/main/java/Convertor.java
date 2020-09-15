import java.util.NoSuchElementException;
import java.util.Scanner;

public class Convertor {
    public static double convert() {
        Scanner inputValue = new Scanner(System.in);
        System.out.println("Enter name of value...");
        String firstValue = inputValue.nextLine();
        System.out.println("Enter name of value you want convert to...");
        String secondValue = inputValue.nextLine();
        System.out.println("Enter amount yoy want to convert...");
        double summary=0.0;
        double amount = inputValue.nextDouble();
        try {
             summary = Action.getValue(firstValue) * amount / Action.getValue(secondValue);
            return summary;
        }
        catch (NoSuchElementException e){
            System.out.println("Cant find valute");
        }
        //подумат на д тем, как реализорвать и как расположить свои методы, сделать ли их
        // статическими или паблик
        //почитать библиотеку Math, рассмотреть как там устроены методы
        //посмотреть архитектуру самого кода, подумать над тем, как расположить классы, подумать на абстракцией


        return summary;

    }


}
