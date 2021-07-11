package explicitWeight;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Waiting extends TestBase {

    @Test
    public void waitTest() {
        launchBrowser("Mozilla");
        getURL("https://www.awwwards.com/sites/khoa-le");
        driver.findElement(By.xpath("//*[@id='header']/div/div[2]/div[2]/strong/a")).click();
        /*The below line is used if the element is present on the page*/

        boolean b = driver.findElement(By.name("_username")).isDisplayed();
        System.out.println(b);

        /*The below function is explesit wait for the elements which are not present*/
        /*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("_username")));
        wait.until(ExpectedConditions.elementToBeClickable(By.name("_username")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("_username")));*/
        if (isElementPresent("//input[@name='_username']"))
            driver.findElement(By.name("_username")).sendKeys("John");

    }

    public boolean isElementPresent(String elementXpath) {

        try {
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
            wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
            wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
