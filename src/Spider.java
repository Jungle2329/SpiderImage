import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jungle on 2018/6/1 0001.
 *
 * @desc TODO
 */

public class Spider implements Runnable {

    private String startUrl;
    private ArrayList<String> spiderPath = new ArrayList<>();

    /**
     * @param startUrl 爬虫开始的url
     */
    public Spider(String startUrl) {
        this.startUrl = startUrl;
    }

    /**
     * 开始爬虫
     */
    @Override
    public void run() {
        startClimb(startUrl);
    }

    /**
     * 开爬，先获取到html的源代码
     *
     * @param analysisUrl
     */
    private void startClimb(String analysisUrl) {
        System.out.println("开始爬:" + analysisUrl);
        spiderPath.add(analysisUrl);
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(analysisUrl);
            URLConnection conn = url.openConnection();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            //拿到所有的网页源代码
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            getHtmlSrc(analysisUrl, result.toString());
            getHtmlHref(analysisUrl, result.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 分析网页源代码，找到页内所有图片
     *
     * @param str
     * @return
     */
    private void getHtmlSrc(String analysisUrl, String str) {
        Pattern pattern = Pattern.compile("src=\\\"(.+?)\\\"");
        Matcher matcher = pattern.matcher(str);
        StringBuilder strs = new StringBuilder();
        while (matcher.find()) {
            String words = matcher.group();
            words = words.replace("src=", "");
            words = words.replace("\"", "");
            ImageDownLoader.saveInFile(words, Utils.getUrlAbsolutePath(analysisUrl));
            strs.append(words).append("\r\n");
        }
        System.out.println("-----------------下载完-------------------");
        System.out.println(strs);
    }

    /**
     * 分析网页源代码，找到所有链接
     *
     * @param str
     * @return
     */
    private void getHtmlHref(String analysisUrl, String str) {
        Pattern pattern = Pattern.compile("href=\\\"(.+?)\\\"");
        Matcher matcher = pattern.matcher(str);
        StringBuilder strs = new StringBuilder();
        String realUrl = "";
        String tempUrl = "";
        ArrayList<String> spiderTargat = new ArrayList<>();
        while (matcher.find()) {
            tempUrl = matcher.group();
            tempUrl = tempUrl.replace("href=", "");
            tempUrl = tempUrl.replace("\"", "");
            if (tempUrl.startsWith("http://") || tempUrl.startsWith("https://")) {
                realUrl = tempUrl;
            } else {
                realUrl = Utils.getUrlAbsolutePath(analysisUrl) + tempUrl;
            }
            if (!realUrl.endsWith(".css") && !realUrl.endsWith("ico")) {
                if (!spiderTargat.contains(realUrl) && !spiderPath.contains(realUrl)) {
                    strs.append(realUrl).append("\r\n");
                    spiderTargat.add(realUrl);
                }
            }
        }
        System.out.println("---------------------!!!!!!!!!!!-----------------------");
        System.out.println(spiderPath.toString());
        System.out.println("---------------------!!!!!!!!!!!-----------------------");
        System.out.println(spiderTargat.toString());
        System.out.println("---------------------!!!!!!!!!!!-----------------------");

        for (String path : spiderTargat) {
            startClimb(path);
        }

        System.out.println("---------------------搜索完该页面下的所有次级链接-----------------------");
        System.out.println(strs.toString());
    }


}
