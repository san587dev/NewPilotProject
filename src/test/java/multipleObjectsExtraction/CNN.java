package multipleObjectsExtraction;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CNN extends TestBase {

    @Test
    public void test() {
        launchBrowser("Edge");
        driver.get("https://www.flipkart.com/clothing-and-accessories/topwear/tshirt/men-tshirt/pr?sid=clo,ash,ank,edy&otracker=categorytree&otracker=nmenu_sub_Men_0_T-Shirts");
        /*
        //By.xpath("//a") can also be used instead tagName
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        System.out.println("The total number of links are " + allLinks.size());

        for (int i = 0; i < allLinks.size(); i++) {
            WebElement links = allLinks.get(i);
            System.out.println(links.getText()+" ------ "+links.isDisplayed());

        }
*/
        //a.IRpwTa is the css selector name of the shirt
        //(//a[@class='IRpwTa']) is the xpath name of the shirt
        //div._30jeq3 price.
        //*[@id="container"]/div/div[3]/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/a[2]/div[1]/div[1]
        List<WebElement> listShirts = driver.findElements(By.xpath("//a[@class='IRpwTa']"));
        System.out.println(listShirts.size());
        for (WebElement e : listShirts) {
            //System.out.println(e.getText() + "    " + e.isDisplayed());
        }
        System.out.println("----------------------");
        List<WebElement> priceList = driver.findElements(By.xpath("//div[@class='_30jeq3']"));
        System.out.println(priceList.size());
        for (WebElement e : priceList){
            //System.out.println(e.getText());
        }

        for (int i=0; i<listShirts.size();i++){
            System.out.println(listShirts.get(i).getText()+" "+priceList.get(i).getText());
        }
    }
}
