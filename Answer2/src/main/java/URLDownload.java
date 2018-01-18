
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;


public class URLDownload {

    FileWriter fileWrite;

    public void ApacheURLtoFile(URL url, File file){
        try {
            FileUtils.copyURLToFile(url, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void HttpClientURLtoFile(URL url, File file){
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            CloseableHttpResponse response = client.execute(new HttpGet(url.toString()));
            HttpEntity entity = response.getEntity();
            if(entity != null){
                entity.writeTo(new FileOutputStream(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void StandardURLtoFile(URL url, File file){
        try {
            URLConnection UrlConnection = url.openConnection();
            BufferedReader stream = new BufferedReader(new InputStreamReader(UrlConnection.getInputStream()));
            fileWrite = new FileWriter(file);
            String data;
            while ((data = stream.readLine()) != null) {
                fileWrite.write(data);
            }
            fileWrite.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
