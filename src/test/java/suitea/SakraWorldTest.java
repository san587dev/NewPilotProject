package suitea;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SakraWorldTest extends TestBase {

    @Test
    public void appointmentTest() throws InterruptedException {
        launchBrowser("Chrome");
        log("Opening the browsers");
        driver.get(prop.getProperty("url"));
        log("Navigated to the given URL");
        waitForPageToLoad();
        driver.findElement(By.linkText(prop.getProperty("doctor_name"))).click();
        log("Clicked on the provided doctor given name");
        /*driver.findElement(By.xpath("//div[3]/a[text()='Dr. Rani Premkumar']")).click();*/
        waitForPageToLoad();
        Thread.sleep(10000);
        //driver.findElement(By.xpath(prop.getProperty("Consultation_Button"))).click();
        driver.findElement(By.xpath("//*[@id='hv-vc-appointment-pop-up23']/div/div/div[2]/div/p[2]/button[1]")).click();
        log("User clicked on Consulting");
        /*waitForPageToLoad();*/
        Thread.sleep(10000);
        driver.findElement(By.id(prop.getProperty("name"))).sendKeys(prop.getProperty("first_name"));
        log("Entering Name in the name field");
        driver.findElement(By.id(prop.getProperty("email"))).sendKeys(prop.getProperty("email_id"));
        log("Entering Email address of the user");
        driver.findElement(By.id(prop.getProperty("mobile"))).sendKeys(prop.getProperty("phoneNo"));
        log("Entering the mobile phone no");
    }


}
