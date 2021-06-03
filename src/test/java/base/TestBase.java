package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import reports.ExtentManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public ExtentReports rep;
    public ExtentTest test;
    public SoftAssert softAssert;
    public String browser;
    public WebDriver driver;
    public Properties prop;

    @BeforeMethod(alwaysRun = true)
    public void init(ITestContext con, ITestResult result) {
        System.out.println(result.getMethod().getMethodName().toUpperCase());
        rep = ExtentManager.getReports();
        test = rep.createTest(result.getMethod().getMethodName().toUpperCase());
        result.setAttribute("reporter", test);
        softAssert = new SoftAssert();

        String groupName[] = con.getAllTestMethods()[0].getGroups();
        String browserGroup = "";
        for (String g : groupName) {
            if (g.startsWith("browsergroup")) {
                browserGroup = g;
                break;
            }
        }
        browser = con.getCurrentXmlTest().getParameter(browserGroup);
        System.out.println("Browser is" + browser);
    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        rep.flush();
    }

    public void log(String msg) {
        System.out.println(msg);
        test.log(Status.INFO, msg);
    }

    public void logFailure(String msg) {
        System.out.println(msg);
        test.log(Status.FAIL, msg);
    }

    public void failureAndStopTest(String msg) {
        softAssert(msg);
        softAssert.assertAll();
    }

    public void softAssert(String msg) {
        logFailure(msg);
        softAssert.fail(msg);
    }

    public void launchBrowser(String browserName) {
        if (browserName.equals("Mozilla")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//webDriverFile//geckodriver.exe");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "webDriverLogs//firefox.log");
            FirefoxOptions options = new FirefoxOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            FirefoxProfile prof = new FirefoxProfile();// new profile
            prof.setPreference("dom.webnotifications.enabled", false);
            options.setProfile(prof);
            driver = new FirefoxDriver(options);
        } else if (browserName.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//webDriverFile//chromedriver.exe");
            //System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "webDriverLogs//chrome.log");
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
            ChromeOptions ops = new ChromeOptions();
            //ops.setBinary("");
            ops.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            ops.addArguments("--disable-notifications");
            ops.addArguments("--start-maximized");
            driver = new ChromeDriver(ops);
        } else if (browserName.equals("Edge")) {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "//webDriverFile//msedgedriver.exe");
            //System.setProperty(EdgeDriverService.EDGE_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
            EdgeOptions options = new EdgeOptions();
            //options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            //options.setBinary(new File(""));
            options.addArguments("--disable-notifications");
            options.addArguments("--start-maximized");
            driver = new EdgeDriver(options);
        }

        driver.manage().window().maximize();

        try {
            prop = new Properties();
            FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "//src/test//resources//project.properties");
            prop.load(fs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void takeScreenShot(String filePath) {
        // take screenshot
        // save screenshot in reports screenshot folder
        // add the screenshots in the reports
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // get the dynamic folder name
            FileUtils.copyFile(srcFile, new File(filePath));
            //test.addScreenCaptureFromPath("path of image", "xxxx");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void getElementScreenshot(WebElement ele, String filePath) {
        // Get entire page screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg;
        try {
            fullImg = ImageIO.read(screenshot);
            // Get the location of element on the page
            Point point = ele.getLocation();

            // Get width and height of the element
            int eleWidth = ele.getSize().getWidth();
            int eleHeight = ele.getSize().getHeight();

            // Crop the entire page screenshot to get only element screenshot
            BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(),
                    eleWidth, eleHeight);
            ImageIO.write(eleScreenshot, "png", screenshot);

            // Copy the element screenshot to disk
            File screenshotLocation = new File(filePath);
            FileUtils.copyFile(screenshot, screenshotLocation);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    /*This method is used creating a wait time so that the Java Script & JQuery pages*/
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

    public void selectDate(String dateValue){
        //dynamic date selection
        String monthYearDisplayed = driver.findElement(By.cssSelector(prop.getProperty("monthYear"))).getText();
        System.out.println("Month Year Displayed " + monthYearDisplayed);
        SimpleDateFormat sd = new SimpleDateFormat("MM-dd-yyyy");
        try {
            Date dateToBeSelected = sd.parse(dateValue);
            Date currentDate = new Date();
            String day = new SimpleDateFormat("d").format(dateToBeSelected);
            System.out.println(day);
            String month = new SimpleDateFormat("MMMM").format(dateToBeSelected);
            System.out.println(month.toString());
            String year = new SimpleDateFormat("yyyy").format(dateToBeSelected);
            System.out.println(year);
            String monthYearToBeSelected = month + " " + year;
            System.out.println("Month Year to be selected " + monthYearToBeSelected);

            while (!monthYearToBeSelected.equals(monthYearDisplayed)) {
                //Click on forward or back icon
                if (dateToBeSelected.compareTo(currentDate) == 1) {
                    //forward icon
                    driver.findElement(By.xpath(prop.getProperty("calender_forward"))).click();
                } else if (dateToBeSelected.compareTo(currentDate) == -1) {
                    //back icon
                    driver.findElement(By.xpath(prop.getProperty("calender_back"))).click();
                }
                monthYearDisplayed = driver.findElement(By.cssSelector(prop.getProperty("monthYear"))).getText();
                System.out.println("Month Year Displayed " + monthYearDisplayed);

            }
            //Dat Selection
            driver.findElement(By.xpath("//a[text()='" + day + "']")).click();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
