package TestCases;
//Test Case 2: Test the Search Functionality
// URL & Browser name are read from config.properties file
// login name, password & name of item to be searched are read from Data Provider SearchDP
import Commons.Driver;
import DataProviders.ConfigFileReader;
import DataProviders.SearchDP;
import PageObjects.LoginPage;
import PageObjects.PasswordPage;
import PageObjects.SearchForProduct;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.InetAddress;

public class TestSearchFunction{
    ExtentReports reports;
    ExtentSparkReporter extentSparkReporter;
    ExtentTest extentTest;
    WebDriver driver;
    @BeforeTest
    public void setUp(){
        //Get browser name and url from config.properties file
        ConfigFileReader configFileReader= new ConfigFileReader();
        driver = Driver.openBrowser(configFileReader.getBrowser(),configFileReader.getApplicationUrl());
    }

    @Test(dataProvider = "SearchProduct",dataProviderClass = SearchDP.class)
    public void SearchProduct(String username,String password,String searchWord) throws IOException, InterruptedException {

        String path=System.getProperty("user.dir");
        reports=new ExtentReports();
        extentSparkReporter=new ExtentSparkReporter(path+"\\report\\SearchReport.html");
        reports.setSystemInfo("Machine Name", InetAddress.getLocalHost().getHostName());
        reports.attachReporter(extentSparkReporter);
        extentTest=reports.createTest("SearchTest");

        LoginPage loginPage=new LoginPage(driver);
        loginPage.Login(username);
        PasswordPage passwordPage=new PasswordPage(driver);
        passwordPage.PasswordPage(password);
        extentTest.log(Status.INFO,"Logged In");
        // search for a product
        SearchForProduct search=new SearchForProduct(driver);
        search.SearchForProduct(searchWord);
        extentTest.log(Status.INFO,"Search Completed");
        extentTest.addScreenCaptureFromPath(Driver.takeScreenshot());
        reports.flush();
    }
    @AfterTest
    public void teardown(){
        //closes browser after test execution
        Driver.closeBrowser();
    }

}
