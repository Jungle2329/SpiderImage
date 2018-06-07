public class Main {

    public static void main(String[] args) {
//        String urlStr = "http://phideon.svw-volkswagen.com/";
        String urlStr = "http://www.ldcang.com/";
        Spider mSpider = new Spider(urlStr);
        new Thread(mSpider).start();
//        System.out.println(Utils.getUrlAbsolutePath(urlStr));

    }


}
