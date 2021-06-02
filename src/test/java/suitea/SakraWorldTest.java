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

    public void waitForPageToLoad() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int i = 0;

        while (i != 10) {
            String state = (String) js.executeScript("return document.readyState;");
            System.out.println(state);

            if (state.equals("complete"))
                break;
            else
                wait(3);
            i++;
        }
        //Check for jquery status
        i = 0;
        while (i != 0) {
            Long d = (Long) js.executeScript("return jQuery.active;");
            System.out.println(d);
            if (d.longValue() == 0)
                break;
            else
                wait(3);
            i++;
        }

    }

    public void wait(int time){
        try {
            Thread.sleep(time*2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
