package multipleObjectsExtraction;

import base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
        takingScreenShot("C:\\Users\\santo\\SeleniumProject\\PilotProject\\reports\\Screenshots\\sample.jpg");
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


    /*Below is the code were you can take the Screenshot by using Web element*/

    public void getElementScreenshot(WebElement ele, String filePath) {
        /*Getting an entire page screenshot
         * */
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg;
        try {
            fullImg = ImageIO.read(screenshot);
            //Get the location of element on the page
            Point point = ele.getLocation();

            //Get the width and height of the element
            int eleWidth = ele.getSize().getWidth();
            int eleHeight = ele.getSize().getHeight();

            //Crop the entire page screenshot to get only the element screenshot
            BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
            ImageIO.write(eleScreenshot, "png", screenshot);

            //Copy the element screenshot to the disk
            File screenshotLocation = new File(filePath);
            FileUtils.copyFile(screenshot, screenshotLocation);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}