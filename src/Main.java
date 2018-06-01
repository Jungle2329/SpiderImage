public class Main {

    public static void main(String[] args) {
//        String urlStr = "https://tieba.baidu.com/p/5700853245";
        String urlStr = "https://yulinapp.com/index.php?app=wap&mod=Weiba&act=postDetail&id=1500632";
        Spider mSpider = new Spider(urlStr);
        mSpider.startSpider();

    }
}
