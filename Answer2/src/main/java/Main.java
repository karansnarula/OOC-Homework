import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args){
        URLDownload downloader = new URLDownload();
        try {
            URL url = new URL("http://www.dustyfeet.com/");
            File file = new File("C:\\Users\\Admin\\Desktop\\OOC\\Homework1\\URLContents\\contents3.txt");
            downloader.HttpClientURLtoFile(url,file);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
