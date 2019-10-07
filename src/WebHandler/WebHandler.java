package WebHandler;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class WebHandler {

    public static void openURI(String URLParam){
        try {
            URL url = new URL(URLParam);
            URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
            Desktop.getDesktop().browse(uri);

        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

}
