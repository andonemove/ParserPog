import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Parser {


    public static Document getPage() throws IOException {
        String url = "https://www.banki.ru/products/currency/cb/";
        Document page = Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
        return page;
    }

    public static List<String> getRowsFromTabel() throws IOException {
        List<String> list = new ArrayList<>();
        Element table = getPage().select("table").get(0);
        Elements rows = table.select("tr");
        String[] strarr = new String[10];
        strarr = table.text().split(" ");
        for (int i = 0; i < strarr.length; i++) {
            if (strarr[i].equals("Код") ||
                    strarr[i].equals("Единиц") ||
                    strarr[i].equals("Валюта") ||
                    strarr[i].equals("Курс") ||
                    strarr[i].equals("Изменение")) {
                list.add(strarr[i]);
            }
        }
        //list.forEach(numb -> System.out.print(numb +" "));
        return list;
    }

    public static List<String> getLines() throws IOException {
        List<String> lines = new ArrayList<>();
        Element allDate = getPage().select("table").get(0);
        Element heap = allDate.select("tbody").get(0);
        Elements listOfLInes = heap.select("tr");
        for (int i = 0; i < listOfLInes.size(); i++) {
            if (!listOfLInes.get(i).text().contains("ПОКУПКА ПРОДАЖА")) {
                lines.add(listOfLInes.get(i).text());
            }
        }
       // lines.forEach(numb-> System.out.println(numb));
        return lines;
    }

    /*@Override
    public String toString() {
        String rowBuilder="";
        try {
           for(int i=0;i<getRowsFromTabel().size();i++){
               rowBuilder=(getRowsFromTabel().get(i)+" ");
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowBuilder;
    }*/
}


