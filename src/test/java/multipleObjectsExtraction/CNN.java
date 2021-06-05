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
        driver.get("https://www.cnn.com/");

        //By.xpath("//a") can also be used instead tagName
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        System.out.println("The total number of links are " + allLinks.size());

        for (int i = 0; i < allLinks.size(); i++) {
            WebElement links = allLinks.get(i);
            System.out.println(links.getText());
        }

    }
}
