package info.p445m.libHTTP;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pmg on 1/17/2017.
 */
public abstract class MyHTTP {
    /*  this does a post and returns the result as a string
         */
    public abstract String runit();

    public static String postURL(String destination, String data) throws IOException {
        URL url = new URL(destination);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        // url-encoding the data seems to make the server send the web page
        // instead of the proper xmlhttprequest response
        conn.setDoOutput(true);

        OutputStream out = conn.getOutputStream();
        out.write(data.getBytes());
        byte[] ray = new byte[500];
        int i;
        ByteArrayOutputStream bray = new ByteArrayOutputStream();
        InputStream in = new BufferedInputStream(conn.getInputStream());
        while ((i = in.read(ray)) != -1) {
            bray.write(ray, 0, i);
        }
        return bray.toString();
    }

    // Formats a date, given the time in microseconds
    public static String parseDate(String udate) {
        if (!udate.startsWith("/Date") || (!udate.endsWith(")/"))) {
            return ("invalid date: " + udate);
        }
        return " finish function parsedate before calling it";
    }

    public static String dFormat(String micros) {
        long time;
        Pattern p = Pattern.compile("/Date\\(([-0-9]+)\\)/");
        Matcher m = p.matcher(micros);
        return m.group(1);



    }
}
