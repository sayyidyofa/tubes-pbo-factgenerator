import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;

class FactScraper {

    private final String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.85 Safari/537.36";
    private Element rawFact = initializeHTML().select("div.td-item").first();

    private Document initializeHTML(){
        Document returnHTML = null;
        try{
            returnHTML = Jsoup
                    .connect("http://factrepublic.com/random-facts-generator")
                    .userAgent(userAgent)
                    .get();
        }
        catch (IOException ignored){

        }

        return returnHTML;
    }

    private BufferedImage getImage(String imageURLString) throws IOException { //contains MalformedURLException and IOException
        URL imageURL = new URL(imageURLString);
        HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
        BufferedImage image;

        connection.setRequestProperty("User-Agent", this.userAgent);
        image = ImageIO.read(connection.getInputStream());

        return image;
    }



    BufferedImage getFactImage() throws IOException {
        String imgURL = this.rawFact.getElementsByTag("img").first().attr("src");
        return getImage(imgURL);
    }

    String getFactTitle(){
        Element rawFactTitle = this.rawFact.getElementsByClass("td-sml-current-item-title").first();
        return rawFactTitle.text();
    }

    String getFactContent(){
        Element rawFactString = this.rawFact.getElementsByTag("p").first();
        return rawFactString.text();
    }

    String getFactSauce(){
        Element rawSauce = this.rawFact.getElementsByClass("button source").first();
        return rawSauce.attr("href");
    }

}