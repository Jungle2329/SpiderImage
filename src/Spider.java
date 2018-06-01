import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jungle on 2018/6/1 0001.
 *
 * @desc TODO
 */

class Spider {

    private String startUrl;

    /**
     * @param startUrl 爬虫开始的url
     */
    private Spider(String startUrl) {
        this.startUrl = startUrl;
    }

    /**
     * 开始爬虫
     */
    public void startSpider() {
        BufferedReader in;
        String result = "";
        try {
            URL url = new URL(startUrl);
            URLConnection conn = url.openConnection();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            //拿到所有的网页源代码
            while ((line = in.readLine()) != null) {
                result += line + "\r\n";
            }
            System.out.println(matchStr(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分析网页源代码，匹配想要的信息
     * @param str
     * @return
     */
    private String matchStr(String str) {
        Pattern pattern = Pattern.compile("src=\\\"(.+?)\\\"");
        Matcher matcher = pattern.matcher(str);
        String strs = "";
        while (matcher.find()) {
            String words = matcher.group();
            words = words.replace("src=", "");
            words = words.replace("\"", "");
            ImageDownLoader.saveInFile(words);
            strs += words + "\r\n";
        }
        System.out.println("-----------------------------------------------");
        return strs;
    }
}
