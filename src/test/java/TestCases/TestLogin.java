package TestCases;
// Test Case 1: Test login with valid url, valid username & valid password
// URL & Browser name are read from config.properties file
// login name & password are read from Data Provider LoginDP
import Commons.Driver;
import DataProviders.ConfigFileReader;
import DataProviders.LoginDP;
import PageObjects.CheckHomePage;
import PageObjects.LoginPage;
import PageObjects.PasswordPage;
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

public class TestLogin {
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

    // username and password from LoginDP
    @Test(dataProvider = "LoginData",dataProviderClass = LoginDP.class)
    public void LoginTest(String username,String password) throws IOException, InterruptedException {
        String path=System.getProperty("user.dir");
        reports=new ExtentReports();
        extentSparkReporter=new ExtentSparkReporter(path+"\\report\\LoginReport.html");
        reports.setSystemInfo("Machine Name", InetAddress.getLocalHost().getHostName());
        reports.attachReporter(extentSparkReporter);
        extentTest=reports.createTest("LoginTest");

        LoginPage loginPage=new LoginPage(driver);
        loginPage.Login(username);
        PasswordPage passwordPage=new PasswordPage(driver);
        passwordPage.PasswordPage(password);
        //Check for a Welcome Message to confirm successful login
        CheckHomePage checkHomePage=new CheckHomePage(driver);
        String status= checkHomePage.isWelcomeMessagePresent();
        extentTest.log(Status.INFO,status);
        extentTest.addScreenCaptureFromPath(Driver.takeScreenshot());
        reports.flush();
    }
    @AfterTest
    public void teardown(){
        //closes browser after test execution
        Driver.closeBrowser();
    }
}
