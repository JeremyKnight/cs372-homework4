/**
 * This class runs the program
 */
public class TestSpider {
    
    public static void main(String[] args) {
        //String site = "https://stackoverflow.com/questions/43015098/how-to-iterate-through-a-map-in-java/43015629";
        String site = "https://www.whitworth.edu/cms/";
        //String site = "https://www.whitworth.edu/cms/administration/university-communications/staff/";
        //String site = "https://www.procore.com/";

        Spider spider = new Spider(site);

        Thread spiderThread = new Thread(spider);
        spiderThread.start();

        try {
            spiderThread.sleep(100);
        } catch(Exception ex) {
            System.out.println("this failed " + ex.getMessage());
        }
    }
}