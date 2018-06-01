import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * @param url 要获取的url
     * @return 获取不到为空
     */
    public static String getBaseUrl(String url) {
        Pattern mPattern = Pattern.compile("^[a-zA-z]+://[a-zA-Z0-9.]+/");
        Matcher matcher = mPattern.matcher(url);
        while(matcher.find()) {
            return matcher.group();
        }
        return url + "/";
    }
}
