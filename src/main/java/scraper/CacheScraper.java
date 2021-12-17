package scraper;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CacheScraper implements Scraper {

//    private Scraper scraper = new DefaultScraper();
    @Override @SneakyThrows
    public Home scrape(String url) {
        // Created connection to DB
        Connection connection = DriverManager.getConnection("jdbc:sqlite:my_new.sqlite");
        Statement statement = connection.createStatement();

        // Execute query
        String query = String.format("select count(*) as count from homes where url='%s'", url);
        ResultSet rs = statement.executeQuery("select count(*) as count from home where url=url");
        System.out.println(rs.getInt("count"));
//        return null;
//    }

//        Connection connection = DriverManager.getConnection("jdbc:sqlite:db.sqlite");
//        Statement statement = connection.createStatement();


//        String query = String.format("select count(*) as count from homes where url='%s'", url);
//        ResultSet rs = statement.executeQuery(query);

        // Extract result
        if (rs.getInt("count") > 0) {
            query = String.format("select * from homes where url='%s'", url);
            rs = statement.executeQuery(query);
            return new Home(rs.getInt("price"), rs.getDouble("beds"), rs.getDouble("bathes"), rs.getDouble("garages"));

            // Extract from DB
        } else {
            Home home = scraper.scrape(url);

            // Call old scraper
        }
        return null;
    }
}

