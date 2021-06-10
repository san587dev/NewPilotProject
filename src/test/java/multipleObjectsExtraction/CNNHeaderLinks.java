package multipleObjectsExtraction;

import base.TestBase;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CNNHeaderLinks extends TestBase {

    @Test
    public void cnnHeader() throws InterruptedException {
        launchBrowser("Mozilla");
        driver.get("https://www.cnn.com/");
        //Extracting all the links which are in a patten below is the example from CNN
        List<WebElement> topLinks = driver.findElements(By.xpath("//ul[@class='sc-cSHVUG bYPcOh']/li/a"));
        System.out.println(topLinks.size());

        for (int i = 0; i < topLinks.size(); i++) {
            System.out.println(topLinks.get(i).getText() + "  " + topLinks.get(i).isDisplayed());
            String url = topLinks.get(i).getAttribute("href");
            System.out.println(url);
            boolean result = validateResponceCode(url);
            //Assert.assertTrue(result,"Invalid respons code");
            softAssert.assertTrue(result, "Invalid respons code");
            /*topLinks.get(i).click();
            System.out.println(driver.getCurrentUrl());
            driver.get("https://www.cnn.com/");
            System.out.println("------------");
            Thread.sleep(3000);
            topLinks = driver.findElements(By.xpath("//ul[@class='sc-cSHVUG bYPcOh']/li/a"));*/
        }

        softAssert.assertAll();
    }


}
