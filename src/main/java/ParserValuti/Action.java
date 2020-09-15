package ParserValuti;

import ParserValuti.AbstractAction;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Action extends AbstractAction {

    @Override
    public void getChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter what action you want to do");
        System.out.println("Enter 1 if you want to get actual inf about Valute");
        System.out.println("Enter 2 if you want to calculate rub to Valute ");
        switch (sc.nextInt()) {
            case 1:
                System.out.println("Enter interested Valute:");
                getInfo(sc.nextLine());
                break;
            case 2:
                System.out.println("Enter Valute name you want calculate to:");
                calculate(sc.nextLine());
                break;
            default:
                System.out.println("Cant found mach");

        }

    }

    @Override
    public void getInfo(String valuteName) {
        List<String> infoList;
        try {
            infoList = Parser.getLines();
            for (int i = 0; i < infoList.size(); i++) {
                if (infoList.get(i).contains(valuteName)) {
                    Parser.getRowsFromTabel();
                    System.out.println(infoList.get(i));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double calculate(String valuateName)  {
        Scanner globalScanner;
        double result=getValue(valuateName);
        System.out.println("Enter mount Rub convert to "+valuateName );
        globalScanner= new Scanner(System.in);
         double input=globalScanner.nextDouble();
        return input*result;


    }
    public static double getValue(String valuteName){
       double result;
        try {
            List<String> list= Parser.getLines();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).contains(valuteName)) {
                    Parser.getRowsFromTabel();
                    Pattern pattern = Pattern.compile("(\\d+.?\\d{4})");
                    Matcher matcher=pattern.matcher(list.get(i));
                    if (matcher.find()) {
                     result=Double.parseDouble(matcher.group());
                         return  result;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  0.0;
    }
}
