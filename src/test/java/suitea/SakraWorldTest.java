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
        log("opened the URL");
        waitForPageToLoad();
        driver.findElement(By.linkText(prop.getProperty("doctor_name"))).click();
        log("clicked on the doctor name");
        /*driver.findElement(By.xpath("//div[3]/a[text()='Dr. Rani Premkumar']")).click();*/
        waitForPageToLoad();
        //driver.findElement(By.xpath(prop.getProperty("Consultation_Button"))).click();
        driver.findElement(By.xpath("//*[@id=\"hv-vc-appointment-pop-up23\"]/div/div/div[2]/div/p[2]/button[1]")).click();
        log("User clicked Consulting");

    }


}
