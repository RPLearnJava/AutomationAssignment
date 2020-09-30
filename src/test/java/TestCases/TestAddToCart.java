package TestCases;
//Test Case 3: Select an item and add to cart
// URL & Browser name are read from config.properties file
// login name, password & name of item to be searched are read from Data Provider SearchDP
import Commons.Driver;
import DataProviders.ConfigFileReader;
import DataProviders.SearchDP;
import PageObjects.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.io.IOException;
import java.net.InetAddress;

public class TestAddToCart {
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
        extentSparkReporter=new ExtentSparkReporter(path+"\\report\\AddToCartReport.html");
        reports.setSystemInfo("Machine Name", InetAddress.getLocalHost().getHostName());
        reports.attachReporter(extentSparkReporter);
        extentTest=reports.createTest("AddToCartTest");

        LoginPage loginPage=new LoginPage(driver);
        loginPage.Login(username);
        PasswordPage passwordPage=new PasswordPage(driver);
        passwordPage.PasswordPage(password);
        extentTest.log(Status.INFO,"Logged In");
        // search for a product
        SearchForProduct search=new SearchForProduct(driver);
        search.SearchForProduct(searchWord);
        extentTest.log(Status.INFO,"Search Completed");
        ProductPage product=new ProductPage(driver);
        product.addProduct();
        extentTest.log(Status.INFO,"Product added to cart");
        ViewCart viewCart = new ViewCart(driver);
        viewCart.ViewCart();
        extentTest.addScreenCaptureFromPath(Driver.takeScreenshot());
        reports.flush();
    }

    @AfterTest
    public void teardown(){
        //closes browser after test execution
        Driver.closeBrowser();
    }
}
