package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.util.Date;

public class ExtentManager {
    static ExtentReports reports;

    public static ExtentReports getReports() {
        if (reports == null) {
            reports = new ExtentReports();

            Date date = new Date();
            System.out.println(date.toString().replaceAll(":", "-"));
            String reportsFolder = date.toString().replace(":", "-") + "//screenshots";

            String screenshotFolderPath = System.getProperty("user.dir") + "//reports" + reportsFolder;
            String reportFolderPath = System.getProperty("user.dir") + "//reports//" + date.toString().replaceAll(":", "-");

            System.out.println(screenshotFolderPath);
            File file = new File(screenshotFolderPath);
            file.mkdir();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFolderPath);
            sparkReporter.config().setReportName("Production Test Reports");
            sparkReporter.config().setDocumentTitle("Automation Test Reports");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setEncoding("utf-8");

            reports.attachReporter(sparkReporter);
        }
        return reports;
    }
}
