package org.sandbox.utils;

import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class WeAreCaringScrapper {

    public String fetchEligibleHelpers(String countryCode, String maxSal) {
        Document doc = null;
        List<Map<String, String>> results = new ArrayList<>();
        try {
            String url = String.format("https://wearecaring.com/helper-list-2/?fwp_citizenship=%s&fwp_expected_salary=%%2C%s", countryCode.toLowerCase(), maxSal);
            System.out.println("URL : "+url);
            doc = Jsoup
                    .connect(url)
                    .get();
            Elements elements = doc.body().getElementsByAttributeValueContaining("class", "fwpl-col");
            for (Element element : elements) {
                Map<String, String> result = new HashMap<>();
                Document htmlText = Jsoup.parse(element.html());
                result.put("link", Jsoup.parse(htmlText.select("div").get(1).html()).select("a").attr("href"));
                result.put("name", htmlText.select("div").get(1).text());
                result.put("country", htmlText.select("div").get(2).text());
                result.put("availability", htmlText.select("div").get(3).text());
                results.add(result);
            }
            return parseData(results);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String parseData(List<Map<String, String>> results) {
        StringBuffer buffer = new StringBuffer();
        for(Map<String, String> entries : results) {
            buffer.append("Name     : ");
            buffer.append(entries.get("name"));
            buffer.append("\n");
            buffer.append("Link     : ");
            buffer.append(entries.get("link"));
            buffer.append("\n");
            buffer.append("Country  : ");
            buffer.append(entries.get("country"));
            buffer.append("\n");
            buffer.append(entries.get("availability"));
            buffer.append("\n");
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
