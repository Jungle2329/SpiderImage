public class Main {

    public static void main(String[] args) {
        String urlStr = "https://tieba.baidu.com/p/5700853245";
//        String urlStr = "http://www.ldcang.com/";
        Spider mSpider = new Spider(urlStr);
        new Thread(mSpider).start();

    }


}
