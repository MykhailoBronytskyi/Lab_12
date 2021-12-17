package scraper;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DefaultScraper implements Scraper {
    private static final String PRICE_SELECTOR = ".detail__info-xlrg";
    private static final String BEDS_SELECTOR = ".nhs_BedsInfo";
    private static final String BATHS_SELECTOR = ".nhs_BathsInfo";
    private static final String GARAGE_SELECTOR = ".nhs_GarageInfo";
    private static final String SQUARE_FEET_SELECTOR = ".nhs_SqFtInfo";
    private static final String LAST_MODIFIED_SELECTOR = ".detail__modified";


    @Override @SneakyThrows
    public Home scrape(String url) {
        Document doc = Jsoup.connect(url).get();
        int price = getPrice(doc);
        double beds = getBeds(doc);
        double baths = getBaths(doc);
        double garages = getGarages(doc);
        double squareFeet = getSquareFeet(doc);
        double lastModified = getLastModified(doc);


//        System.out.println(price);
//        System.out.println(beds);
//        System.out.println(baths);
//
//        System.out.println(garages);
//
//        System.out.println(squareFeet);
//
//        System.out.println(lastModified);
//
////        System.out.println(doc);
        return new Home(price, beds, baths, garages,squareFeet,lastModified);
    }

    private int getPrice(Document doc) {
        String price = doc.selectFirst(PRICE_SELECTOR).text().replaceAll("[^0-9]", "");
        return Integer.parseInt(price);
    }

    private double getBeds(Document doc){
        String beds = doc.selectFirst(BEDS_SELECTOR).text().replaceAll("[^0-9.]","");
        return Double.parseDouble(beds);
    }

    private double getBaths(Document doc){
        String beds = doc.selectFirst(BATHS_SELECTOR).text().replaceAll("[^0-9.]","");
        return Double.parseDouble(beds);
    }

    private double getGarages(Document doc){
        String beds = doc.selectFirst(GARAGE_SELECTOR).text().replaceAll("[^0-9.]","");
        return Double.parseDouble(beds);
    }

    private double getSquareFeet(Document doc){
        String beds = doc.selectFirst(SQUARE_FEET_SELECTOR).text().replaceAll("[^0-9.]","");
        return Double.parseDouble(beds);
    }

    private double getLastModified(Document doc){
        String beds = doc.selectFirst(LAST_MODIFIED_SELECTOR).text().replaceAll("[^0-9.]","");
        return Double.parseDouble(beds);
    }
}
