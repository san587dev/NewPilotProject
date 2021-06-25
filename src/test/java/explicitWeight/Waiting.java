package explicitWeight;

import base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Waiting extends TestBase {

    @Test
    public void waitTest() {
        launchBrowser("Mozilla");
        getURL("https://www.awwwards.com/sites/khoa-le");
        driver.findElement(By.xpath("//*[@id='header']/div/div[2]/div[2]/strong/a")).click();


    }
}
