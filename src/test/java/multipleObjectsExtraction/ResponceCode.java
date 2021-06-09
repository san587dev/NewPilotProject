package multipleObjectsExtraction;

import org.apache.http.client.fluent.Request;

public class ResponceCode{


    public static void main(String[] args) {
        /*launchBrowser("Mozilla");
        driver.get("https://www.cnn.com/business");*/
        String url = "https://www.cnn.com/business";
        int resp_code = 0;
        try {
            resp_code = Request.Get(url).execute().returnResponse().getStatusLine().getStatusCode();
            System.out.println("Respose Code for Given URL "+ url+ "is -> "+ resp_code);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*if (resp_code == 200)
            return true;
         else
            return false;
*/
    }

}
