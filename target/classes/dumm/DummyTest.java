package dumm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DummyTest {

    @Test
    public void test(){
       /* //System.setProperty("webdriver.gecko.driver","E://WebDrivers//geckodriver.exe");
        //WebDriver driver = new FirefoxDriver();
        //System.setProperty("webdriver.chrome.driver","E://WebDrivers//chromedriver.exe");
        //ChromeDriver driver = new ChromeDriver();
        System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"//webDriverFile//msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.yahoo.com/");*/
        System.out.println(System.getProperty("user.dir")+"//webDriverFile");
        System.out.println(System.getProperty("user.dir") + "//src/test//resources//project.properties");
    }
}
