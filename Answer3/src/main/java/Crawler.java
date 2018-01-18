import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;

public class Crawler {

    public static HashSet<String> links;

    public Crawler(){
        links = new HashSet<String>();
    }

    public void getLinks(String URL) {
        if (!links.contains(URL) && URL.contains("cs.muic.mahidol.ac.th")) {
            try {
                Document document = Jsoup.connect(URL).validateTLSCertificates(false).ignoreHttpErrors(true).timeout(5000).get();
                Elements otherLinks = document.select("a[href]");
                for (Element page : otherLinks) {
                    links.add(URL);
                    if(links.size() > 2500){
                        return;
                    }
                    getLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void ApacheURLtoFile(URL url, File file){
        try {
            FileUtils.copyURLToFile(url, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File file = new File(":\\Users\\Admin\\Desktop\\OOC\\Homework1\\URLContents\\JavaDoc");
        HttpURLConnection huc;
        URL newurl;
        Crawler crawler = new Crawler();
        crawler.getLinks("https://cs.muic.mahidol.ac.th/courses/ooc/docs/");
        for(String url : links){
            try {
                newurl = new URL(url);
                huc =  (HttpURLConnection)  newurl.openConnection();
                huc.setRequestMethod ("GET");
                huc.connect();
                if(huc.getResponseCode() == 200){
                    ApacheURLtoFile(newurl,file);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }

}
