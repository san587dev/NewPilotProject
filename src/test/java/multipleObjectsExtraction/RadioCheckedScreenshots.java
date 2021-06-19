package multipleObjectsExtraction;

import base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RadioCheckedScreenshots extends TestBase {
    /*Selecting a radio button
     * And see which is selected*/

    @Test
    public void radio() {
        launchBrowser("Chrome");
        getURL("https://www.keynotesupport.com/internet/web-contact-form-example-radio-buttons.shtml");
        List<WebElement> radiobutton = driver.findElements(By.name("software"));
        System.out.println("Total radio buttons " + radiobutton.size());
        System.out.println(radiobutton.get(0).getAttribute("checked"));
        System.out.println(radiobutton.get(1).getAttribute("checked"));
        System.out.println(radiobutton.get(2).getAttribute("checked"));
        System.out.println(radiobutton.get(3).getAttribute("checked"));
        /*for (int i = 0; i < radiobutton.size(); i++) {
            System.out.println(i);
        }*/
        radiobutton.get(0).click();
        /*wait(10);
        System.out.println(radiobutton.get(1).getText());*/
        System.out.println(radiobutton.get(0).getAttribute("checked"));
        System.out.println(radiobutton.get(1).getAttribute("checked"));
        System.out.println(radiobutton.get(2).getAttribute("checked"));
        System.out.println(radiobutton.get(3).getAttribute("checked"));
        wait(10);
        driver.quit();
    }

    @Test
    public void radioButtonLabCorp() {
        launchBrowser("Chrome");
        getURL("https://patient.labcorp.com/registration");
        driver.findElement(By.xpath("//label[text()='Male']/preceding-sibling::input")).click();
        System.out.println(driver.findElement(By.xpath("//label[text()='Male']/preceding-sibling::input")).getAttribute("checked"));
        wait(20);
        driver.quit();
    }


    @Test
    public void screenShots() {

    }

    /*Below code is used for taking a screenshot of the window*/
    public void takingScreenShot(String filePath) {
        /* take screenshot Java Object
         * save screenshot in the reports screenshot folder
         * add the screenshot in the reports
         */
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            //get the dynamic folder name
            FileUtils.copyFile(srcFile, new File(filePath));
            //test.addScreenCaptureFromPath("path of image", xxxx);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


}
