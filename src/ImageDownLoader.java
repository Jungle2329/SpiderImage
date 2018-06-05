import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jungle on 2018/6/1 0001.
 * 把图片链接保存到本地
 * @desc TODO
 */

class ImageDownLoader {

    public static void saveInFile(String path, String baseUrl) {
        byte[] bs = new byte[1024 * 2];
        try {
            if (path.contains("http://") || path.contains("https://")) {

            } else if (path.startsWith("//")) {
                path = "https:" + path;
            } else if (path.startsWith("/")) {
                //截掉baseUrl最后的/
                String str = baseUrl.substring(0, baseUrl.length() - 1);
                path = str + path;
            } else {
                path = baseUrl + path;
            }
            if (path.endsWith(".png") || path.endsWith(".jpg")) {
                System.out.println(path);
                OutputStream os = new FileOutputStream("D:\\Jungle\\inputImage\\" + System.currentTimeMillis() + ".png");
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5 * 1000);
                InputStream is = conn.getInputStream();
                int len;
                while ((len = is.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }
                is.close();
                os.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
