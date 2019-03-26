import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        String urlStr = "http://phideon.svw-volkswagen.com/";
        Spider mSpider = new Spider(urlStr);
        new Thread(mSpider).start();
        System.out.println(Utils.getUrlAbsolutePath(urlStr));
//        "fdfd".codePoints().map()
    }

    public static String longestConsec(String[] strarr, int k) {
        // your code
        ArrayList<String> list = new ArrayList<>(Arrays.asList(strarr));
        list.sort(Comparator.comparingInt(String::length));
        Collections.reverse(list);
        StringBuffer sb = new StringBuffer();
        if (list.size() >= k) {
            for (int i = 0; i < k; i++) {
                sb.append(list.get(i));
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    private static void a() {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL("http://kakaluyi.iteye.com/blog/185255").openConnection();
            System.out.println(conn.getResponseCode());
            System.out.println(conn.getResponseMessage());
            System.out.println(conn.getURL().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
