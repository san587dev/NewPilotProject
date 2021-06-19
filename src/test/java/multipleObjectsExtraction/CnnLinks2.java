package multipleObjectsExtraction;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CnnLinks2 extends CNNHeaderLinks {

    //*[@id="header-nav-container"]/div/div[1]/div/div[2]/nav/ul/li/a
    @Test
    public void test() {
        String part1 = "//*[@id='header-nav-container']/div/div[1]/div/div[2]/nav/ul/li[";
        String part2 = "]/a";

        launchBrowser("Mozilla");
        getURL("https://www.cnn.com/");

        for (int i=0; i<11; i++){
            String text = driver.findElement(By.xpath(part1+i+part2)).getText();
            System.out.println(text);
            String url = driver.findElement(By.xpath(part1+i+part2)).getAttribute("href");
            System.out.println(url);
            validateResponceCode(url);
        }
    }

}
