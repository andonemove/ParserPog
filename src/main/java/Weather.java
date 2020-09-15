import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Weather {
    private static Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");

    private static Document getPage() throws IOException {
        String url = "https://www.pogoda.spb.ru/";
        Document page = Jsoup.parse(new URL(url), 9000);
        return page;
    }

    private static String getDateFromString(String stringDate) throws Exception {
        Matcher matcher = pattern.matcher(stringDate);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new Exception("Cant't find match");
    }

    private static void printFourValues(Elements values, int index) {
        if (index == 0) {
            Element valueLn = values.get(3);
            boolean isMorningText = valueLn.text().contains("Утро");
            int iterationCount = 4;
            if (isMorningText) {
                iterationCount = 3;
            }
            for (int i = 0; i < iterationCount; i++) {
                Element valueLine = values.get(index + 1);
                for (Element td : valueLine.select("td")) {
                    System.out.print(td.text() + "    ");
                }
                System.out.println();
            }


        }
            for (int i = 0; i < 4; i++) {
                Element valueLine = values.get(index);
                for (Element td : valueLine.select("td")) {
                    System.out.print(td.text() + "    ");
                }
                System.out.println();
            }
        }



    public static void main(String[] args) throws Exception {
        Document page = getPage();
        Elements tableWeather = page.select("table[class=wt]");
        Elements names = tableWeather.select("tr[class=wth]");
        Elements values = tableWeather.select("tr[valign=top]");
        int index = 0;// index
        for (Element name : names) {
            String dateString = name.select("th[id=dt]").text();
            String date = getDateFromString(dateString);
            System.out.println(date + "    Явление   Темп    Давл    Влажность   Ветер");
            printFourValues(values, index);
        }


    }
}
