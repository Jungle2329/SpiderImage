import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

/**
 * Created by Jungle on 2018/6/1 0001.
 *
 * @desc TODO
 */

class Utils {

    /**
     * https://c.runo2ob.com/front-end/854
     * to
     * https://c.runo2ob.com/
     * 截取url的域名
     *
     * @param url 要获取的url
     * @return 获取不到为空
     */
    public static String getBaseUrl(String url) {
        Pattern mPattern = Pattern.compile("^[a-zA-z]+://[a-zA-Z0-9.-]+/");
        Matcher matcher = mPattern.matcher(url);
        while (matcher.find()) {
            return matcher.group();
        }
        return url + "/";
    }

    /**
     * https://c.runo2ob.com/front-end/854
     * to
     * https://c.runo2ob.com/front-end/
     * 截取url的域名
     *
     * @param url 要获取的url
     * @return 获取不到为空
     */
    public static String getUrlAbsolutePath(String url) {
        if (url.endsWith("/")) {
            url = url + "1";
        }
        String[] urls = url.split("/");
        url = url.replace(urls[urls.length - 1], "");
        return url;
    }

    /**
     * 计算字符串中重复字符的种类个数，大小写算同一字符
     *
     * @param text
     * @return
     */
    public static int duplicateCount(String text) {
        text = text.toLowerCase();
        int count = 0;
        while (text.length() > 0) {
            int length1 = text.length();
            char[] chars = text.toCharArray();
            text = text.replace(String.valueOf(chars[0]), "");
            int length2 = text.length();
            if (length1 - 1 > length2) {
                count++;
            }
        }
        return count;
    }

    /**
     * 返回时间戳当做文件名字
     *
     * @return
     */
    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
    }

    public static int[] getImageUrlSize(String url) throws IOException {
        InputStream murl = new URL(url).openStream();
        BufferedImage sourceImg = ImageIO.read(murl);
        return new int[]{sourceImg.getWidth(), sourceImg.getHeight()};
    }
}
