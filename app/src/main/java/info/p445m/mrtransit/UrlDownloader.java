package info.p445m.mrtransit;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pmg on 8/4/2017.
 */

public class UrlDownloader {
    public static String vrrring (String destination, String data)  {
        URL url;
        HttpURLConnection conn;
        try {
            url = new URL(destination);
            conn= (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            return "bad url: " + destination;
        } catch (IOException e) {
            return e.getMessage();
        }

        //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        // url-encoding the data seems to make the server send the web page
        // instead of the proper xmlhttprequest response
        if (data != null ) {
            conn.setDoOutput(true);
            try {
                OutputStream out = conn.getOutputStream();
                out.write(data.getBytes());
            } catch (IOException e) {
                return "can not write POST data: " + e.getMessage();
            }
        }
        byte []ray = new byte[500];
        int i;
        ByteArrayOutputStream bray = new ByteArrayOutputStream();
        try {
            InputStream in = new BufferedInputStream(conn.getInputStream());
            while ((i = in.read(ray)) != -1) {
                bray.write(ray, 0, i);
            }
            return bray.toString();
        } catch (IOException e) {
            return " could not read inputstream: " + e.getMessage();
        }
    }
}
