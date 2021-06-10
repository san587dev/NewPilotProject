package multipleObjectsExtraction;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class CnnNewsLinks extends TestBase {
    /*This is by using web element box
     * */
    @Test
    public void testNewsLinks() {
        launchBrowser("Mozilla");
        getURL("https://www.cnn.com/");
        WebElement box =(WebElement) driver.findElements(By.xpath("//*[@id='homepage2-zone-1']/div[2]/div/div[1]/ul"));
        List<WebElement> list = box.findElements(By.tagName("a"));
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getText() + "  " + list.get(i).isDisplayed());
            /*String url = list.get(i).getAttribute("href");
            System.out.println(url);
            boolean result = validateResponceCode(url);
            //Assert.assertTrue(result,"Invalid respons code");
            softAssert.assertTrue(result, "Invalid respons code");
            *//*topLinks.get(i).click();
            System.out.println(driver.getCurrentUrl());
            driver.get("https://www.cnn.com/");
            System.out.println("------------");
            Thread.sleep(3000);
            topLinks = driver.findElements(By.xpath("//ul[@class='sc-cSHVUG bYPcOh']/li/a"));*/
        }

        softAssert.assertAll();
    }
}
