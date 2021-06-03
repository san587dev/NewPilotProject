package suitea;

import base.TestBase;
import org.apache.poi.hssf.record.formula.functions.If;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        Thread.sleep(20000);
        //Explicit wait **************************
        //the below line is validating if the element is present or not.

        /*if (!isElementPresent(prop.getProperty("name")))
            failureAndStopTest("Name field is not present");
*/
        driver.findElement(By.id(prop.getProperty("name"))).sendKeys(prop.getProperty("first_name"));
        log("Entering Name in the name field");
        driver.findElement(By.id(prop.getProperty("email"))).sendKeys(prop.getProperty("email_id"));
        log("Entering Email address of the user");
        driver.findElement(By.id(prop.getProperty("mobile"))).sendKeys(prop.getProperty("phoneNo"));
        log("Entering the mobile phone no");
/*******************validate the fields ******************************/
        /*Selecting the gender with select class*/
        WebElement gender = driver.findElement(By.id(prop.getProperty("genderSelection")));
        Select selectGender = new Select(gender);
        selectGender.selectByVisibleText(prop.getProperty("male"));
/*
        if(! isElementPresent(prop.getProperty("dob")))
            failureAndStopTest("DOB field is not present/Visible");*/
        /*Selecting the date*/
        driver.findElement(By.id(prop.getProperty("dob"))).click();
        selectDate(prop.getProperty("dov_val"));

        //Selecting pref date 1
        driver.findElement(By.id(prop.getProperty("prefdate_1"))).click();
        selectDate(prop.getProperty("prefdate_1_value"));

//Selecting pref date 2
        driver.findElement(By.id(prop.getProperty("prefdate_2"))).click();
        selectDate(prop.getProperty("prefdate_2_value"));

        if (driver.findElement(By.id(prop.getProperty("UHID"))).isDisplayed())
            failureAndStopTest("UHID is displayed");
        driver.findElement(By.cssSelector("yes_radio")).click();

        if (!driver.findElement(By.id(prop.getProperty("UHID"))).isDisplayed())
            failureAndStopTest("UHID is not displayed");

        driver.findElement(By.id("UHID")).sendKeys(prop.getProperty("UHID_value"));
        driver.findElement(By.cssSelector("no_radio")).click();
        if (driver.findElement(By.id(prop.getProperty("UHID"))).isDisplayed())
            failureAndStopTest("UHID is displayed");




    }

    /* If the element is (present and not hidden) -true
     * If the element is (not present or hidden ) -false
     */

    public boolean isElementPresent(String locator) {
        WebElement e = null;
        //Presences of the element
        try {
            driver.findElement(By.id(locator));
        } catch (Exception ex) {
            log("Exception while extracting Object" + ex.getMessage());
            return false;
        }
        //Visibility
        if (!e.isDisplayed())
            return false;

        //reaches here -- present and not hidden
        return true;
    }

}
